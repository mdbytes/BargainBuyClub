/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 */
package com.bdowebtech.bargainbuyclub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Alert class provides the basic structure for product alerts.
 * 
 * @author Martin Dwyer
 */
public class Alert {

    private int alertID;
    private Product product;
    private double alertPrice;
    private User user;
    private static Database db = new Database();
    
    /**
     *  Default constructor creates null object.
     */
    public Alert() {
        this.alertID = 0;
        this.product = null;
        this.alertPrice = 0.0;
        this.user = null;
    }

    /**
     * Constructor for alert objects.
     * 
     * @param alertID a unique integer
     * @param user a User object
     * @param product a Product object
     * @param alertPrice the product alert price, a double
     */
    public Alert(int alertID, User user, Product product, double alertPrice) {
        this.alertID = alertID;
        this.product = product;
        this.alertPrice = alertPrice;
        this.user = user;
    }

    /**
     * Method to retrieve the user associated with an alert object.
     * 
     * @return user, a User object
     */
    public User getUser() {
        return user;
    }

    /**
     * Method to return unique ID for alert object.
     * 
     * @return alert ID, an integer
     */
    public int getAlertID() {
        return alertID;
    }

    /**
     * Method to retrieve product associated with an alert object.
     * 
     * @return product, a Product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Method retrieves the alert price for an alert object.
     * 
     * @return alert price, a double
     */
    public double getAlertPrice() {
        return alertPrice;
    }

    private void setAlertID(int alertID) {
        this.alertID = alertID;
    }

    private void setProduct(Product product) {
        this.product = product;
    }

    private void setAlertPrice(double alertPrice) {
        this.alertPrice = alertPrice;
    }

    private void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Alert{" + "alertID=" + alertID + "\n" + ", product=" + product + "\n" +  ", alertPrice=" + alertPrice + "\n" + ", user=" + user + "\n" + '}';
    }
    
    /**
     * Method to add alerts into application database. 
     * 
     * @param productID the product ID for the alert Product object
     * @param userID the user ID for the alert User object
     * @param alertPrice the alert price, a double
     * 
     * @return alert, an Alert object
     */
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
    
    /**
     * Method to retrieve alert object from database based on attributes. 
     * 
     * @param productID the product ID for the alert Product object
     * @param userID the user ID for the alert User object
     * @param alertPrice the alert price, a double
     * 
     * @return alert, an Alert object
     */
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
    
    /**
     * Method to retrieve alert from database with the unique alert ID.
     * 
     * @param alertID the unique identifier for the alert object, an integer
     * 
     * @return alert, an Alert object
     */
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
    
    /**
     * Method to retrieve all alerts in the application database. 
     * 
     * @return alerts, a list of Alert objects
     */
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
    
    /**
     * Method to update the alert price for an existing alert. 
     * 
     * @param alertID the unique identifier for an alert, an integer
     * @param alertPrice the alert price for the alert, a double
     */
    public static void updateAlertPrice(int alertID, double alertPrice) {
        String query = "UPDATE alerts "
                + "SET alert_price = " + alertPrice + ""
                + "WHERE alert_id = " + alertID + ";";
        db.executeUpdate(query);
        
    }

    /**
     * Method to delete an alert from the application database. 
     * 
     * @param alertID the unique identifier for an alert, an integer
     */
    public static void deleteAlert(int alertID) {
        String query = "DELETE FROM alerts "
                + "WHERE alert_id = " + alertID + ";";
        db.executeUpdate(query);
        
    }    

}