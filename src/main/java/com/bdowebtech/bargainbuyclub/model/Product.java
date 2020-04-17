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
 *
 * @author marti
 */
public class Product {
    
    private int productID;
    private String productUrl;
    private Store store;
    private String productName;
    private double productPrice;
    
    public static Database db = new Database();
    
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
