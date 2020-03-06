/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdowebtech.bargainbuyclub.model.Data;

import com.bdowebtech.bargainbuyclub.model.Alert;
import com.bdowebtech.bargainbuyclub.model.Product;
import com.bdowebtech.bargainbuyclub.model.Store;
import com.bdowebtech.bargainbuyclub.model.User;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author marti
 */
public class Database {
    
    public ArrayList<Store> stores;
    public ArrayList<User> users;
    public ArrayList<Alert> alerts;
    

    public Database() {
        this.users = new ArrayList<User>();
        this.alerts = new ArrayList<Alert>();
        this.stores = new ArrayList<Store>();
    }
    
    public Store getStoreByProductUrl(String productUrl) {
        Store thisStore = new Store();
        for(Store store: stores) {
            if(productUrl.contains(store.getStoreRootUrl()));
            thisStore = store;
        }
        
        return thisStore;
    }


    public User getUser(String email) {
        User theUser = null;
        for (User user : this.users) {
            if (user.emailAddress.equals(email)) {
                theUser = user;
            } else {
                theUser = null;
            }
        }
        return theUser;
    }

    public static ArrayList<String[]> alertRequests() {
        
        ArrayList<String[]> testAlerts = new ArrayList<>();
        String[] productOne = new String[]{"https://www.bestbuy.com/site/westinghouse-49-class-led-1080p-hdtv/6295040.p?skuId=6295040","100.00"};
        testAlerts.add(productOne);
        String[] productTwo = new String[]{"https://www.bestbuy.com/site/hp-omen-gaming-intel-core-i7-9700-16gb-memory-nvidia-geforce-gtx-1660-ti-1tb-hard-drive-256gb-ssd-shadow-black-front-bezel-dark-chrome-logo/6349466.p?skuId=6349466","1000.00"};
        testAlerts.add(productTwo);
        String[] productThree = new String[]{"https://www.bestbuy.com/site/ibuypower-gaming-desktop-intel-core-i7-9700f-16gb-memory-nvidia-geforce-rtx-2070-super-1tb-hdd-480gb-solid-state-drive-black/6362981.p?skuId=6362981", "1000.00"};
        testAlerts.add(productThree);

        return testAlerts;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<Store> getStores() {
        return this.stores;
    }

    public ArrayList<Alert> getAlerts() {
        return this.alerts;
    }
    
        
    public void setUpDatabase() {
        ArrayList<String[]> alertRequests = alertRequests();
        Store bestBuy = new Store("Best Buy","bestbuy.com","div.priceView-hero-price.priceView-customer-price","div.shop-product-title");
        User user = new User("Martin","Dwyer","martin.dwyer@outlook.com","admin");
        
        for(String[] alertRequest: alertRequests) {
            Product product = new Product(alertRequest[0],bestBuy);
            Alert alert = new Alert(user,product,Double.parseDouble(alertRequest[1]));
            this.alerts.add(alert);
        }
        
    }
   
    
}
