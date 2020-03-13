package com.bdowebtech.bargainbuyclub.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store {

    private int storeID;
    private String storeName;
    private String storeRootUrl;
    private String priceQuery;
    private String productNameQuery;

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
    
    

}
