/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdowebtech.bbc20200312.model;

import java.io.IOException;

/**
 *
 * @author marti
 */
public class Product {
    
    private int productID;
    private String productUrl;
    private Store store;
    private String productName;
    private double productPrice;
    
    public Product() {
        this.productID = 0;
        this.productName= null;
        this.productUrl= null;
        this.store= null;
        this.productPrice= 0.0;
    }

    public Product(String productUrl, Store store) {
        this.productID = 0;
        this.productUrl = productUrl;
        this.store = store;
        try {
            this.productName = this.store.getProductName(productUrl);
        } catch (IOException e) {
            System.err.println("Cannot retrieve data from url.  Application terminating");
            System.exit(0);
        }
        try {
            this.productPrice = this.store.getProductPrice(productUrl);
        } catch (IOException e) {
            System.err.println("Cannot retrieve data from url.  Application terminating");
            System.exit(0);
        }
    }

    public int getProductID() {
        return productID;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public Store getStore() {
        return store;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productUrl=" + productUrl + ", store=" + store + ", productName=" + productName + ", productPrice=" + productPrice + '}';
    }
    
    
    
}
