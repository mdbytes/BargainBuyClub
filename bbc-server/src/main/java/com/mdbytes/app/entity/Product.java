package com.mdbytes.app.entity;

import com.mdbytes.app.rest.service.ProductScraper;
import jakarta.persistence.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "product_url", nullable = false, length = 10000)
    private String productUrl;

    @Basic
    @Column(name = "product_name")
    private String productName;
    @Basic
    @Column(name = "recent_price")
    private Double recentPrice;
    @Basic
    @Column(name = "last_updated")
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "product")
    private List<Alert> alerts;

    public Product() {
    }

    public Product(String productUrl, String productName, Double recentPrice, Date lastUpdated, Store store) {
        this.productUrl = productUrl;
        this.lastUpdated = lastUpdated;
        this.store = store;
        this.productName = productName;
        this.recentPrice = recentPrice;
        this.lastUpdated = lastUpdated;

    }

    private Double setupProductPrice(String productUrl) {
        ProductScraper ps = new ProductScraper(productUrl, store.getPriceQuery(), store.getProductNameQuery());
        double productPrice = 0.0;
        try {
            productPrice = ps.getProductPrice(productUrl);
        } catch (IOException e) {
            System.out.println("Problem getting product price");
        }
        return productPrice;
    }

    private String setupProductName(String productUrl) {
        ProductScraper ps = new ProductScraper(productUrl, store.getPriceQuery(), store.getProductNameQuery());
        String productName = "";
        try {
            productName = ps.getProductName(productUrl);
        } catch (IOException e) {
            System.out.println("Problem getting product name.");
        }
        return productName;
    }


    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getRecentPrice() {
        return recentPrice;
    }

    public void setRecentPrice(Double recentPrice) {
        this.recentPrice = recentPrice;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productUrl='" + productUrl + '\'' +
                ", productName='" + productName + '\'' +
                ", recentPrice=" + recentPrice +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}