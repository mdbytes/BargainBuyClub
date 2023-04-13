/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public
 *              License, a free copyleft license for software.  A copy of this
 *              license has been provided in the root folder of this application.
 */
package com.mdbytes.app.model;

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

    /**
     * Default constructor creates null object.
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
     * @param alertID    a unique integer
     * @param user       a User object
     * @param product    a Product object
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

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Method to return unique ID for alert object.
     *
     * @return alert ID, an integer
     */
    public int getAlertID() {
        return alertID;
    }

    public void setAlertID(int alertID) {
        this.alertID = alertID;
    }

    /**
     * Method to retrieve product associated with an alert object.
     *
     * @return product, a Product
     */
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Method retrieves the alert price for an alert object.
     *
     * @return alert price, a double
     */
    public double getAlertPrice() {
        return alertPrice;
    }

    public void setAlertPrice(double alertPrice) {
        this.alertPrice = alertPrice;
    }

    @Override
    public String toString() {
        return "Alert{" + "alertID=" + alertID + "\n" + ", product=" + product + "\n" + ", alertPrice=" + alertPrice + "\n" + ", user=" + user + "\n" + '}';
    }


}