/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public
 *              License, a free copyleft license for software.  A copy of this
 *              license has been provided in the root folder of this application.
 */
package com.mdbytes.app.model;

import java.sql.Date;
import java.time.LocalDate;


/**
 * Product class defines product structure and methods to retrieve product price
 * from the product page website.
 *
 * @author Martin Dwyer
 */
public class Product {

    private int productID;
    private String productUrl;
    private Store store;
    private String productName;
    private double productPrice;
    private Date lastUpdated;

    /**
     * Default constructor creates a null object.
     */
    public Product() {
        this.productID = 0;
        this.productName = null;
        this.productUrl = null;
        this.store = null;
        this.productPrice = 0.0;
        this.lastUpdated = Date.valueOf(LocalDate.now());
    }

    /**
     * Constructor to create product from the product page URL address and the
     * store which the product came from.
     *
     * @param productUrl   the URL address where the product page is located
     * @param store        a Store object representing where the product seller
     * @param productName  the product name
     * @param productPrice the most recent price of the product
     * @param lastUpdated  the date the product was last updated.
     */

    public Product(String productUrl, Store store, String productName, double productPrice, Date lastUpdated) {
        this.productID = 0;
        this.productUrl = productUrl;
        this.store = store;
        this.productName = productName;
        this.productPrice = productPrice;
        this.lastUpdated = lastUpdated;
    }

    /**
     * Method to retrieve unique ID given to product object.
     *
     * @return ID, an integer
     */
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Method retrieves the URL address associated with existing product object.
     *
     * @return URL address, a String
     */
    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    /**
     * Method to retrieve store associated with product object.
     *
     * @return store, a Store object
     */
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Method to retrieve name of product object.
     *
     * @return product name, a String
     */
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Method to retrieve existing price for product object.
     *
     * @return product price, a double
     */
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productUrl=" + productUrl + ", store=" + store + ", productName=" + productName + ", productPrice=" + productPrice + '}';
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
