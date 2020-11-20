/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 */
package com.bdowebtech.bargainbuyclub.model.Data;

import com.bdowebtech.bargainbuyclub.model.Alert;
import com.bdowebtech.bargainbuyclub.model.Product;
import com.bdowebtech.bargainbuyclub.model.Store;
import com.bdowebtech.bargainbuyclub.model.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * HomePage provides site-level access to data to be used as sample alerts on the home page. 
 * 
 * @author Martin Dwyer
 */
public class HomePage {
    
    private User user;
    private Store store;
    private ArrayList<String[]> alertData;
    private ArrayList<Alert> alerts;
    
    /**
     * Constructor populates an ArrayList of alerts.  
     */
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
        Store bestBuy = new Store(0,"Disney","shopdisney.com","span.sales span.value","h1.product-name");
        return bestBuy;
        
    }

    private ArrayList<String[]> getAlertData() {
        
        
        ArrayList<String[]> testAlerts = new ArrayList<>();
        String[] productOne = new String[]{"https://www.shopdisney.com/minnie-mouse-long-sleeve-baseball-t-shirt-for-girls-5626047577216M.html?isProductSearch=0&plpPosition=1&guestFacing=Sale-Clothing-T_Shirts%2520%2526%2520Tops","10.00"};
        testAlerts.add(productOne);
        String[] productTwo = new String[]{"https://www.shopdisney.com/minnie-mouse-zip-hoodie-for-girls-5626057397226M.html?isProductSearch=0&plpPosition=2","15.00"};
        testAlerts.add(productTwo);
        String[] productThree = new String[]{"https://www.shopdisney.com/mickey-mouse-zip-hoodie-for-boys-5626057397229M.html?isProductSearch=0&plpPosition=1","10.00"};
        testAlerts.add(productThree);
        String[] productFour = new String[]{"https://www.shopdisney.com/lightning-mcqueen-zip-hoodie-for-boys-cars-5626057817230M.html?isProductSearch=0&plpPosition=3","18.00"};
        testAlerts.add(productFour);

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

    /**
     * Method to retrieve home page alerts.
     * 
     * @return alerts, an ArrayList of Alert objects
     */
    public ArrayList<Alert> getAlerts() {
        return alerts;
    }
    
//    public static void main(String[] args) {
//        HomePage front = new HomePage();
//        ArrayList<Alert> alerts = front.getAlerts();
//        
//        for (Alert alert: alerts) {
//            System.out.println(alert.getProduct().getProductPrice());
//            System.out.println(alert.getAlertPrice());
//            
//            System.out.println(alert.getProduct().getProductName());
////        }
////        
//        
//    }
}
