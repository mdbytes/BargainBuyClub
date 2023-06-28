package com.mdbytes.app.rest;

import com.mdbytes.app.entity.Alert;
import com.mdbytes.app.entity.Product;
import com.mdbytes.app.entity.Store;
import com.mdbytes.app.entity.User;
import com.mdbytes.app.repository.AlertRepository;
import com.mdbytes.app.repository.ProductRepository;
import com.mdbytes.app.repository.StoreRepository;
import com.mdbytes.app.repository.UserRepository;
import com.mdbytes.app.rest.requests.AddAlertRequest;
import com.mdbytes.app.rest.requests.AddAlertResponse;
import com.mdbytes.app.rest.requests.AddProductRequest;
import com.mdbytes.app.rest.requests.GetProductByUrlRequest;
import com.mdbytes.app.rest.service.AlertService;
import com.mdbytes.app.rest.service.ProductScraper;
import com.mdbytes.app.rest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppController {
    private ProductRepository productRepository;
    private StoreRepository storeRepository;
    private UserRepository userRepository;
    private AlertRepository alertRepository;
    private AlertService alertService;
    private ProductService productService;

    @Autowired
    public AppController(ProductRepository productRepository,
                         StoreRepository storeRepository,
                         UserRepository userRepository,
                         AlertRepository alertRepository,
                         AlertService alertService,
                         ProductService productService) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
        this.alertRepository = alertRepository;
        this.productService = productService;
        this.alertService = alertService;
    }

    // add product
    @PostMapping("/admin/products/add")
    public Product makeNewProduct(@RequestBody AddProductRequest addProductRequest) {
        Product product = new Product();
        Store store = null;
        String productUrl = addProductRequest.getProductUrl();
        int storeId = addProductRequest.getStoreId();
        Optional result = storeRepository.findById(storeId);
        if (result != null) {
            store = (Store) result.get();
        } else {
            product.setProductName("Could not retrieve that store Id");
            return product;
        }
        ProductScraper ps = new ProductScraper(product.getProductUrl(),
                store.getPriceQuery(),
                store.getProductNameQuery());
        try {
            product.setProductUrl(productUrl);
            product.setStore(store);
            product.setProductName(ps.getProductName(product.getProductUrl()));
            product.setRecentPrice(ps.getProductPrice(product.getProductUrl()));
            product.setLastUpdated(Date.valueOf(LocalDate.now()));
            product = productRepository.save(product);
        } catch (IOException e) {
            product.setProductName("Product could not be added.");
            product.setProductUrl("Check url and contact admin for error log.");
            e.printStackTrace();
        }
        return product;
    }

    // update all product prices


    @PostMapping("/admin/alerts/add")
    public AddAlertResponse makeNewAlert(@RequestBody AddAlertRequest addAlertRequest) {
        AddAlertResponse addAlertResponse = new AddAlertResponse();
        Alert alert = new Alert();
        System.out.println("alert constructed.  getting user...");
        Optional result = userRepository.findById(addAlertRequest.getUserId());
        User user = null;
        if (result != null) {
            user = (User) result.get();
            alert.setUser(user);
            System.out.println(user.toString());
        } else {
            addAlertResponse.setMessage("Error.  Could not find user id in database");
            return addAlertResponse;
        }
        Optional productResult = productRepository.findById(addAlertRequest.getProductId());
        if (productResult != null) {
            Product product = (Product) productResult.get();
            alert.setProduct(product);
            System.out.println(product.toString());
        } else {
            addAlertResponse.setMessage("Error. Could not find product in database");
            return addAlertResponse;
        }
        alert.setAlertPrice(addAlertRequest.getAlertPrice());
        alert = alertRepository.save(alert);
        System.out.println(alert.toString());
        System.out.println("alert saved...");
        addAlertResponse.setMessage("Alert added successfully");
        System.out.println("All done...");
        System.out.println(addAlertResponse.toString());
        return addAlertResponse;
    }

    @GetMapping("/admin/alerts/home")
    public List<Alert> getHomeAlerts(@RequestBody AddAlertRequest addAlertRequest) {
        User user = userRepository.findById(1).get();
        List<Alert> alerts = alertRepository.findByUser(user);
        return alerts;
    }

    @PostMapping("/admin/products/url")
    public Product getProductByUrl(@RequestBody GetProductByUrlRequest getRequest) {
        List<Product> products = productRepository.findAll();
        products.removeIf(p -> !p.getProductUrl().equals(getRequest.getProductUrl()));
        if (products.size() > 0) return products.get(0);
        return null;
    }

    @GetMapping("/admin/products/update")
    public Integer updateProductPrices() throws IOException {
        return productService.updateAlertProductPrices();
    }

    @GetMapping("/admin/notifications/send")
    public Integer sendAlertNotifications() throws IOException {
        return alertService.sendPriceAlerts();
    }


    @GetMapping("/admin/users")
    public User getUserByEmailAddress(@RequestParam String email) throws IOException {
        User user = userRepository.findByEmail(email).get();
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }


}
