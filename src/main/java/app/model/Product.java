/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 */
package app.model;

import java.io.IOException;

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
    
    /**
     * Default constructor creates a null object.
     */
    public Product() {
        this.productID = 0;
        this.productName= null;
        this.productUrl= null;
        this.store= null;
        this.productPrice= 0.0;
    }

    /**
     * Constructor to create product from the product page URL address and the
     * store which the product came from. 
     * 
     * @param productUrl the URL address where the product page is located
     * @param store a Store object representing where the product seller
     */
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

    /**
     * Method to retrieve unique ID given to product object.  
     * 
     * @return ID, an integer
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Method retrieves the URL address associated with existing product object. 
     * 
     * @return URL address, a String
     */
    public String getProductUrl() {
        return productUrl;
    }

    /**
     * Method to retrieve store associated with product object.
     * 
     * @return store, a Store object
     */
    public Store getStore() {
        return store;
    }

    /**
     * Method to retrieve name of product object. 
     * 
     * @return product name, a String
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Method to retrieve existing price for product object.
     * 
     * @return product price, a double
     */
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
