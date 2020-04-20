/*
 * Author: Martin Dwyer
 * Date: April 17, 2020
 * Description: This file is part of the BargainBuyClub application written by developer Martin Dwyer.
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
public class HomePage {
    
    private User user;
    private Store store;
    private ArrayList<String[]> alertData;
    private ArrayList<Alert> alerts;
    

    public HomePage() {
        this.user = setUpUser();
        this.store = setUpStore();
        this.alertData = getAlertData();
        this.alerts = setUpAlerts();
        
    }
    
    private User setUpUser() {
        User user = new User(0,"Home","Page","home@bargainbuyclub.com","1234",false);
        return user;
        
    }
    
    private Store setUpStore() {
        Store bestBuy = new Store(0,"Best Buy","bestbuy.com","div.priceView-hero-price.priceView-customer-price","div.shop-product-title");
        return bestBuy;
        
    }

    private ArrayList<String[]> getAlertData() {
        
        ArrayList<String[]> testAlerts = new ArrayList<>();
        String[] productOne = new String[]{"https://www.bestbuy.com/site/westinghouse-49-class-led-1080p-hdtv/6295040.p?skuId=6295040","100.00"};
        testAlerts.add(productOne);
        String[] productTwo = new String[]{"https://www.bestbuy.com/site/hp-omen-gaming-intel-core-i7-9700-16gb-memory-nvidia-geforce-gtx-1660-ti-1tb-hard-drive-256gb-ssd-shadow-black-front-bezel-dark-chrome-logo/6349466.p?skuId=6349466","1000.00"};
        testAlerts.add(productTwo);
        String[] productThree = new String[]{"https://www.bestbuy.com/site/ibuypower-gaming-desktop-intel-core-i7-9700f-16gb-memory-nvidia-geforce-rtx-2070-super-1tb-hdd-480gb-solid-state-drive-black/6362981.p?skuId=6362981", "1000.00"};
        testAlerts.add(productThree);

        return testAlerts;
    }
    
    private ArrayList<Alert> setUpAlerts() {
        ArrayList<String[]> alertData = this.alertData;
        ArrayList<Alert> frontPageAlerts = new ArrayList<>();
        for(String[] alertSpecs: alertData) {
            Product product = new Product(alertSpecs[0],this.store);
            Alert alert = new Alert(0,user,product,Double.parseDouble(alertSpecs[1]));
            frontPageAlerts.add(alert);
        }
        return frontPageAlerts;
    }

    public ArrayList<Alert> getAlerts() {
        return alerts;
    }
    
    
    
    
    
    
}
