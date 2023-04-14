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
 * Store class defines stores where products are sold along with the characteristic
 * methods to retrieve prices and product names from that store's website pages.
 *
 * @author Martin Dwyer
 */
public class Store {

    private int storeID;
    private String storeName;
    private String storeRootUrl;
    private String priceQuery;
    private String productNameQuery;

    /**
     * Default constructor creates null object.
     */
    public Store() {
        this.storeID = 0;
        this.storeName = null;
        this.storeRootUrl = null;
        this.priceQuery = null;
        this.productNameQuery = null;
    }

    /**
     * Store objects are defined by their name, root URL address, and the query parameters
     * to find the price and product name on their website pages.
     *
     * @param storeID          a unique identifier for Store objects, an integer
     * @param storeName        the name of the store, a String
     * @param storeRootUrl     the root directory for the store website, a String
     * @param priceQuery       the JSOUP query string to return the product price
     * @param productNameQuery the JSOUP query string to return the product name
     */
    public Store(int storeID, String storeName, String storeRootUrl, String priceQuery, String productNameQuery) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeRootUrl = storeRootUrl;
        this.priceQuery = priceQuery;
        this.productNameQuery = productNameQuery;
    }

    /**
     * Method to retrieve the store's unique ID.
     *
     * @return store ID, an integer
     */
    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    /**
     * Method to retrieve a store's name.
     *
     * @return store name, a String
     */
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * Method to retrieve a store's root URL.
     *
     * @return store root url, a String
     */
    public String getStoreRootUrl() {
        return storeRootUrl;
    }

    public void setStoreRootUrl(String storeRootUrl) {
        this.storeRootUrl = storeRootUrl;
    }

    /**
     * Method to retrieve JSOUP price query for store object.
     *
     * @return price query, a String
     */
    public String getPriceQuery() {
        return priceQuery;
    }

    public void setPriceQuery(String priceQuery) {
        this.priceQuery = priceQuery;
    }

    /**
     * Method to retrieve JSOUP name query for store object.
     *
     * @return product name, a String
     */
    public String getProductNameQuery() {
        return productNameQuery;
    }

    public void setProductNameQuery(String productNameQuery) {
        this.productNameQuery = productNameQuery;
    }

    @Override
    public String toString() {
        return "Store{" + "storeID=" + storeID + ", storeName=" + storeName + ", storeRootUrl=" + storeRootUrl + ", priceQuery=" + priceQuery + ", productNameQuery=" + productNameQuery + '}';
    }

}
