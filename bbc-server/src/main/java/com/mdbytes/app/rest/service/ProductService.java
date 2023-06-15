package com.mdbytes.app.rest.service;

import com.mdbytes.app.entity.Alert;
import com.mdbytes.app.entity.Product;
import com.mdbytes.app.repository.AlertRepository;
import com.mdbytes.app.repository.ProductRepository;
import com.mdbytes.app.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private StoreRepository storeRepository;

    private AlertRepository alertRepository;

    public ProductService() {
    }

    @Autowired
    public ProductService(ProductRepository productRepository, StoreRepository storeRepository, AlertRepository alertRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.alertRepository = alertRepository;
    }

    public Integer updateAlertProductPrices() throws IOException {
        List<Alert> alerts = alertRepository.findAll();
        Integer updates = 0;
        for (Alert alert : alerts) {
            Product product = alert.getProduct();
            ProductScraper scraper = new ProductScraper(product.getProductUrl(), product.getStore().getPriceQuery(), product.getStore().getProductNameQuery());
            Double newPrice = scraper.getProductPrice(product.getProductUrl());
            product.setRecentPrice(newPrice);
            product.setLastUpdated(Date.valueOf(LocalDate.now()));
            productRepository.save(product);
            updates++;
        }
        return updates;
    }
}
