/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdowebtech.bargainbuyclub.model;

import com.bdowebtech.bargainbuyclub.model.Data.Database;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
 *
 * @author marti
 */
public class Product {
    
    public String productID;
    public String productUrl;
    public Store store;
    public String productName;
    public double productPrice;
       
    public Product(String productUrl, Store store) {
        this.productUrl = productUrl;
        this.store = store;
        this.productID = UUID.randomUUID().toString();
        try {
            this.productName = this.store.getProductName(productUrl, store.getProductNameQuery());
        } catch (IOException e) {
            System.err.println("Cannot retrieve data from url.  Application terminating");
            System.exit(0);
        }
       try {
            this.productPrice = this.store.getProductPrice(productUrl,store.getPriceQuery());
        } catch (IOException e) {
            System.err.println("Cannot retrieve data from url.  Application terminating");
            System.exit(0);
        }
    }
    
}
