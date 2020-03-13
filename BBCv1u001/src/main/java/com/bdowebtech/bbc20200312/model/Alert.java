package com.bdowebtech.bbc20200312.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Alert {

    private int alertID;
    private Product product;
    private double alertPrice;
    private User user;
    
    public Alert() {
        this.alertID = 0;
        this.product = null;
        this.alertPrice = 0.0;
        this.user = null;
    }

    public Alert(int alertID, User user, Product product, double alertPrice) {
        this.alertID = alertID;
        this.product = product;
        this.alertPrice = alertPrice;
        this.user = user;
    }

    public int getAlertID() {
        return alertID;
    }

    public void setAlertID(int alertID) {
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