package com.bdowebtech.bargainbuyclub.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store {

    private String storeID;
    private String storeName;
    private String storeRootUrl;
    private String priceQuery;
    private String productNameQuery;

    public Store() {
        this.storeID = null;
        this.storeName = null;
        this.storeRootUrl = null;
        this.priceQuery = null;
        this.productNameQuery = null;
    }

    public Store(String storeName, String storeRootUrl, String priceQuery, String productNameQuery) {
        this.storeID = UUID.randomUUID().toString();
        this.storeName = storeName;
        this.storeRootUrl = storeRootUrl;
        this.priceQuery = priceQuery;
        this.productNameQuery = productNameQuery;
    }

    public double getProductPrice(String productURL, String priceQuery) throws IOException {
        double price = 0.0;
        Document doc = Jsoup.connect(productURL).get();
        Elements htmlElements = doc.select(priceQuery);
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

    public String getProductName(String productURL, String productNameQuery) throws IOException {

        Document doc = Jsoup.connect(productURL).get();
        Element htmlElement = doc.select(productNameQuery).first();
        String productName = htmlElement.text();

        return productName;
    }

    public String getStoreID() {
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

}
