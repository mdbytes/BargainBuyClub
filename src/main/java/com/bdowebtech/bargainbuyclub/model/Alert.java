package com.bdowebtech.bargainbuyclub.model;

import com.bdowebtech.bargainbuyclub.model.Data.Database;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Alert {

    public String alertID;
    public Product product;
    public double alertPrice;
    private User user;

    public Alert(User user, Product product, double alertPrice) {
        this.alertID = UUID.randomUUID().toString();
        this.product = product;
        this.alertPrice = alertPrice;
        this.user = user;
    }

    public String getAlertID() {
        return alertID;
    }

    public void setAlertID(String alertID) {
        this.alertID = alertID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getAlertPrice() {
        return alertPrice;
    }

    public void setAlertPrice(double alertPrice) {
        this.alertPrice = alertPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Alert{" + "alertID=" + alertID + "\n" + ", product=" + product + "\n" +  ", alertPrice=" + alertPrice + "\n" + ", user=" + user + "\n" + '}';
    }

    
    
    

    
}