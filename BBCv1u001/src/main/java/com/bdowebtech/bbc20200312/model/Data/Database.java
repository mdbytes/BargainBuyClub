/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdowebtech.bbc20200312.model.Data;

import com.bdowebtech.bbc20200312.model.Alert;
import com.bdowebtech.bbc20200312.model.Product;
import com.bdowebtech.bbc20200312.model.Store;
import com.bdowebtech.bbc20200312.model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author marti
 */
public class Database {
    
    private String dbUrl = "jdbc:mysql://localhost:3306/bargainbuyclub";
    private String username = "root";
    private String password = "Be225Again!";
    
    public User addUser(String firstName, String lastName, String emailAddress, String userPassword, boolean isAdmin) {
        if (getUserByEmailAddress(emailAddress).getUserID() == 0) {
            User user = new User();
            String query = "INSERT INTO users "
                    + "(first_name,last_name,email_address,password,is_admin) "
                    + "VALUES ("
                    + "'" + firstName + "',"
                    + "'" + lastName + "',"
                    + "'" + emailAddress + "',"
                    + "'" + DigestUtils.sha256Hex(userPassword) + "',"
                    + "'" + isAdmin + "')";
            try {
                Connection connection = DriverManager.getConnection(dbUrl, username, password);
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(query);
                user = getUserByEmailAddress(emailAddress);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return user;
        } else {
            return getUserByEmailAddress(emailAddress);
        }
        
    }
    
    public ArrayList<Alert> getUserAlerts(String emailAddress) {
        User user = getUserByEmailAddress(emailAddress);
        int userID = user.getUserID();
                ArrayList<Alert> userAlerts = new ArrayList<Alert>();
        Alert alert = new Alert();
        String query = "SELECT * FROM alerts "
                + "WHERE user_id = '" + userID + "';";
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                alert.setAlertID((int) rs.getObject("alert_id"));
                alert.setProduct(getProductByID((int) rs.getObject("alert_id")));
                alert.setUser(getUserByID((int) rs.getObject("user_id")));
                alert.setAlertPrice((double)rs.getObject("alert_price"));
                userAlerts.add(alert);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userAlerts;
    }
       
    public User getUserByEmailAddress(String emailAddress) {
        User user = new User();
        String query = "SELECT * FROM users "
                + "WHERE email_address = '" + emailAddress + "';";
        
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int userID = (int) rs.getObject("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String userName = rs.getString("email_address");
                String passWord = rs.getString("password");
                boolean isAdmin = Boolean.valueOf(rs.getString("is_admin"));
                user = new User(userID, firstName, lastName, userName, passWord, isAdmin);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    
    

    
    public User getUserByID(int userID) {
        User user = new User();
        String query = "SELECT * FROM users "
                + "WHERE user_id = " + userID + ";";
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String userName = rs.getString("email_address");
                String passWord = rs.getString("password");
                boolean isAdmin = Boolean.valueOf(rs.getString("is_admin"));
                user = new User(userID, firstName, lastName, userName, passWord, isAdmin);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    
    public boolean validateUser(String username, String userPassword) {
        boolean isValid = false;
        User user = getUserByEmailAddress(username);
        System.out.println(user.toString());
        if (user.getPassword().equals(DigestUtils.sha256Hex(userPassword))) {
            isValid = true;
        }
        return isValid;
    }
    
    public Store addStore(String storeName, String storeURL, String priceQuery, String productNameQuery) {
        if (getStoreByName(storeName).getStoreID() == 0) {
            Store store = new Store();
            String query = "INSERT INTO stores "
                    + "(store_name,store_url,price_query,product_name_query) "
                    + "VALUES ('" + storeName + "','" + storeURL + "','" + priceQuery + "','" + productNameQuery + "')";
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                Connection connection = DriverManager.getConnection(dbUrl, username, password);
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(query);
                store = getStoreByName(storeName);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return store;
        } else {
            return getStoreByName(storeName);
        }
        
    }
    
    public Store getStoreByName(String storeName) {
        String dbUrl = "jdbc:mysql://localhost:3306/bargainbuyclub";
        String username = "root";
        String password = "Be225Again!";
        Store store = new Store();
        String query = "SELECT * FROM stores "
                + "WHERE store_name = '" + storeName + "';";
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
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
    
    public Store getStoreByID(int storeID) {
        Store store = new Store();
        String query = "SELECT * FROM stores "
                + "WHERE store_id = " + storeID + ";";
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
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
    
    public Product addProduct(int storeID, String productURL) {
        if (getProductByURL(productURL).getProductID() == 0) {
            Store store = getStoreByID(storeID);
            Product product = new Product(productURL, store);
            String query = "INSERT INTO products "
                    + "(store_id,product_url) "
                    + "VALUES (" + storeID + ",'" + productURL + "');";
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                Connection connection = DriverManager.getConnection(dbUrl, username, password);
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(query);
                product = getProductByURL(productURL);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return product;
        } else {
            return getProductByURL(productURL);
        }
        
    }
    
    public Product getProductByID(int productID) {
        Product product = new Product();
        String query = "SELECT * FROM products "
                + "WHERE product_id = " + productID + ";";
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                product.setProductID(productID);
                Store store = getStoreByID((int) rs.getObject("store_id"));
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
    
    public Product getProductByURL(String productURL) {
        Product product = new Product();
        String query = "SELECT * FROM products "
                + "WHERE product_url = '" + productURL + "';";
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                product.setProductID((int) rs.getObject("product_id"));
                Store store = getStoreByID((int) rs.getObject("store_id"));
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
    
    public Alert addAlert(int productID, int userID, double alertPrice) {
        if (getAlertByAttributes(productID, userID, alertPrice).getAlertID() == 0) {
            Alert alert = new Alert();
            String query = "INSERT INTO alerts "
                    + "(product_id,user_id,alert_price) "
                    + "VALUES (" + productID + "," + userID + "," + alertPrice + ");";
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                Connection connection = DriverManager.getConnection(dbUrl, username, password);
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(query);
                alert = getAlertByAttributes(productID, userID, alertPrice);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return alert;
        } else {
            return getAlertByAttributes(productID, userID, alertPrice);
        }
        
    }
    
    public Alert getAlertByAttributes(int productID, int userID, double alertPrice) {
        Alert alert = new Alert();
        String query = "SELECT * FROM alerts "
                + "WHERE product_id = '" + productID + "'"
                + "AND user_id = " + userID + " "
                + "AND alert_price = " + alertPrice + ";";
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                alert.setAlertID((int) rs.getObject("alert_id"));
                alert.setProduct(getProductByID((int) rs.getObject("alert_id")));
                alert.setUser(getUserByID((int) rs.getObject("user_id")));
                alert.setAlertPrice(alertPrice);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return alert;
    }
    
    
    
    
    
    
}
