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
    private static Database db = new Database();
    
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

    private void setProductID(int productID) {
        this.productID = productID;
    }

    private void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    private void setStore(Store store) {
        this.store = store;
    }

    private void setProductName(String productName) {
        this.productName = productName;
    }

    private void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productUrl=" + productUrl + ", store=" + store + ", productName=" + productName + ", productPrice=" + productPrice + '}';
    }
    
    /**
     * Method adds a product object to the database. 
     * 
     * @param storeID a unique identifier for each store, an integer
     * @param productURL the URL address where the product page is located
     * 
     * @return product, a Product object
     */
    public static Product addProduct(int storeID, String productURL) {
        if (getProductByURL(productURL).getProductID() == 0) {
            Store store = Store.getStoreByID(storeID);
            Product product = new Product(productURL, store);
            String query = "INSERT INTO products "
                    + "(store_id,product_url) "
                    + "VALUES (" + storeID + ",'" + productURL + "');";
            db.executeUpdate(query);
            product = getProductByURL(productURL);
            return product;
        } else {
            return getProductByURL(productURL);
        }
    }
    
    /**
     * Method retrieves product object from the database given the product ID
     * 
     * @param productID a unique identifier for the product, an integer
     * 
     * @return product, a Product object
     */
    public static Product getProductByID(int productID) {
        Product product = new Product();
        String query = "SELECT * FROM products "
                + "WHERE product_id = " + productID + ";";
        try {
            ResultSet rs = db.getResultSet(query);
            while (rs.next()) {
                product.setProductID(productID);
                Store store = Store.getStoreByID((int) rs.getObject("store_id"));
                product.setStore(store);
                product.setProductUrl(rs.getString("product_url"));
                try {
                    product.setProductName(store.getProductName(rs.getString("product_url")));
                    product.setProductPrice(store.getProductPrice(rs.getString("product_url")));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
    
    /**
     * Method retrieves product object from database give the product URL address
     * 
     * @param productURL the URL address where the product page is located
     * 
     * @return product, a Product object
     */
    public static Product getProductByURL(String productURL) {
        Product product = new Product();
        String query = "SELECT * FROM products "
                + "WHERE product_url = '" + productURL + "';";
        try {
            ResultSet rs = db.getResultSet(query);
            while (rs.next()) {
                product.setProductID((int) rs.getObject("product_id"));
                Store store = Store.getStoreByID((int) rs.getObject("store_id"));
                product.setStore(store);
                product.setProductUrl(productURL);
                try {
                    product.setProductName(store.getProductName(rs.getString("product_url")));
                    product.setProductPrice(store.getProductPrice(rs.getString("product_url")));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
    
}
