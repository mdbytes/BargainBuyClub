/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 */
package com.bdowebtech.bargainbuyclub.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Alert {

    private int alertID;
    private Product product;
    private double alertPrice;
    private User user;
    private static Database db = new Database();
    
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
    
    public static Alert addAlert(int productID, int userID, double alertPrice) {
        if (getAlertByAttributes(productID, userID, alertPrice).getAlertID() == 0) {
            Alert alert = new Alert();
            String query = "INSERT INTO alerts "
                    + "(product_id,user_id,alert_price) "
                    + "VALUES (" + productID + "," + userID + "," + alertPrice + ");";
            db.executeUpdate(query);
            alert = getAlertByAttributes(productID, userID, alertPrice);
            return alert;
        } else {
            return getAlertByAttributes(productID, userID, alertPrice);
        }
    }
    
    public static Alert getAlertByAttributes(int productID, int userID, double alertPrice) {
        Alert alert = new Alert();
        String query = "SELECT * FROM alerts "
                + "WHERE product_id = '" + productID + "'"
                + "AND user_id = " + userID + " "
                + "AND alert_price = " + alertPrice + ";";
        try {
            ResultSet rs = db.getResultSet(query);
            while (rs.next()) {
                alert.setAlertID((int) rs.getObject("alert_id"));
                alert.setProduct(Product.getProductByID((int) rs.getObject("alert_id")));
                alert.setUser(User.getUserByID((int) rs.getObject("user_id")));
                alert.setAlertPrice(alertPrice);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return alert;
    }
    
    public static Alert getAlertByID(int alertID) {
        Alert alert = new Alert();
        String query = "SELECT * FROM alerts "
                + "WHERE alert_id = " + alertID + ";";
        try {
            ResultSet rs = db.getResultSet(query);
            while (rs.next()) {
                alert.setAlertID((int) rs.getObject("alert_id"));
                alert.setProduct(Product.getProductByID((int) rs.getObject("alert_id")));
                alert.setUser(User.getUserByID((int) rs.getObject("user_id")));
                alert.setAlertPrice((double)rs.getObject("alert_price"));
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return alert;
    }
    
    public static ArrayList<Alert> getAllAlerts() {
        Alert alert = new Alert();
        ArrayList<Alert> alerts = new ArrayList<Alert>();
        String query = "SELECT * FROM alerts;";
        try {
            ResultSet rs = db.getResultSet(query);
            while (rs.next()) {
                alerts.add(new Alert((int) rs.getObject("alert_id"),
                                      User.getUserByID((int) rs.getObject("user_id")),
                                      Product.getProductByID((int) rs.getObject("product_id")),
                                      (double)rs.getObject("alert_price")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return alerts;
    }
    
    public static void updateAlertPrice(int alertID, double alertPrice) {
        String query = "UPDATE alerts "
                + "SET alert_price = " + alertPrice + ""
                + "WHERE alert_id = " + alertID + ";";
        db.executeUpdate(query);
        
    }

    public static void deleteAlert(int alertID) {
        String query = "DELETE FROM alerts "
                + "WHERE alert_id = " + alertID + ";";
        db.executeUpdate(query);
        
    }    

}