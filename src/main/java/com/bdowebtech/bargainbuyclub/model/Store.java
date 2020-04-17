/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 */
package com.bdowebtech.bargainbuyclub.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store {

    private int storeID;
    private String storeName;
    private String storeRootUrl;
    private String priceQuery;
    private String productNameQuery;
    
    private static Database db = new Database();

    public Store() {
        this.storeID = 0;
        this.storeName = null;
        this.storeRootUrl = null;
        this.priceQuery = null;
        this.productNameQuery = null;
    }

    public Store(int storeID, String storeName, String storeRootUrl, String priceQuery, String productNameQuery) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeRootUrl = storeRootUrl;
        this.priceQuery = priceQuery;
        this.productNameQuery = productNameQuery;
    }

    public double getProductPrice(String productURL) throws IOException {
        double price = 0.0;
        Document doc = Jsoup.connect(productURL).get();
        Elements htmlElements = doc.select(this.priceQuery);
        String stringPrice = "";
        Pattern howToFindPrice = Pattern.compile("(\\d+.\\d+)");
        Matcher findingPrice = howToFindPrice.matcher(htmlElements.get(0).toString());
        while (findingPrice.find()) {
            stringPrice = findingPrice.group(1);
        }
        try {
            price = Double.parseDouble(stringPrice.replace(",", ""));
        } catch (NumberFormatException e) {
            System.err.println("Error in price extraction.");
            price = 0.0;
        }

        return price;

    }

    public String getProductName(String productURL) throws IOException {

        Document doc = Jsoup.connect(productURL).get();
        Element htmlElement = doc.select(this.productNameQuery).first();
        String productName = htmlElement.text();

        return productName;
    }

    public int getStoreID() {
        return storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreRootUrl() {
        return storeRootUrl;
    }

    public String getPriceQuery() {
        return priceQuery;
    }

    public String getProductNameQuery() {
        return productNameQuery;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreRootUrl(String storeRootUrl) {
        this.storeRootUrl = storeRootUrl;
    }

    public void setPriceQuery(String priceQuery) {
        this.priceQuery = priceQuery;
    }

    public void setProductNameQuery(String productNameQuery) {
        this.productNameQuery = productNameQuery;
    }
    
    

    @Override
    public String toString() {
        return "Store{" + "storeID=" + storeID + ", storeName=" + storeName + ", storeRootUrl=" + storeRootUrl + ", priceQuery=" + priceQuery + ", productNameQuery=" + productNameQuery + '}';
    }
    
    public static Store addStore(String storeName, String storeURL, String priceQuery, String productNameQuery) {
        if (getStoreByName(storeName).getStoreID() == 0) {
            Store store = new Store();
            String query = "INSERT INTO stores "
                    + "(store_name,store_url,price_query,product_name_query) "
                    + "VALUES ('" + storeName + "','" + storeURL + "','" + priceQuery + "','" + productNameQuery + "')";
            db.executeUpdate(query);
            store = getStoreByName(storeName);
            return store;
        } else {
            return getStoreByName(storeName);
        }
    }
    
    public static Store getStoreByName(String storeName) {
        String dbUrl = "jdbc:mysql://localhost:3306/bargainbuyclub";
        String username = "root";
        String password = "Be225Again!";
        Store store = new Store();
        String query = "SELECT * FROM stores "
                + "WHERE store_name = '" + storeName + "';";
        ResultSet rs = db.getResultSet(query);
        try {
            while (rs.next()) {
                store.setStoreName(storeName);
                store.setStoreID((int) rs.getObject("store_id"));
                store.setStoreRootUrl(rs.getString("store_url"));
                store.setPriceQuery(rs.getString("price_query"));
                store.setProductNameQuery(rs.getString("product_name_query"));
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return store;
    }
    
    public static Store getStoreByID(int storeID) {
        Store store = new Store();
        String query = "SELECT * FROM stores "
                + "WHERE store_id = " + storeID + ";";
        ResultSet rs = db.getResultSet(query);
        try {
            while (rs.next()) {
                store.setStoreID(storeID);
                store.setStoreName(rs.getString("store_name"));
                store.setStoreRootUrl(rs.getString("store_url"));
                store.setPriceQuery(rs.getString("price_query"));
                store.setProductNameQuery(rs.getString("product_name_query"));
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return store;
    }
}
