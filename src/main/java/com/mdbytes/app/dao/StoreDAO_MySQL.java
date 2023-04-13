package com.mdbytes.app.dao;

import com.mdbytes.app.model.Store;

import java.sql.*;
import java.util.List;

public class StoreDAO_MySQL extends DAO_MySQL implements DAO<Store> {

    @Override
    public Store add(String[] args) {
        String storeName = args[0];
        String storeUrl = args[1];
        String priceQuery = args[2];
        String productNameQuery = args[3];
        if (getStoreByName(storeName).getStoreID() == 0) {
            Store store = new Store();
            String query = "INSERT INTO stores "
                    + "(store_name,store_url,price_query,product_name_query) "
                    + "VALUES ('" + storeName + "','" + storeUrl + "','" + priceQuery + "','" + productNameQuery + "')";
            try {
                Connection connection = DriverManager.getConnection(dbUrl, username, password);
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(query);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            store = getStoreByName(storeName);
            return store;
        } else {
            return getStoreByName(storeName);
        }
    }

    @Override
    public Store get(int id) {
        Store store = new Store();
        String query = "SELECT * FROM stores "
                + "WHERE store_id = " + id + ";";
        ResultSet rs = this.getResultSet(query);
        try {
            while (rs.next()) {
                store.setStoreID(id);
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

    @Override
    public Store update(Store store) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Store> getAll() {
        return null;
    }

    public Store getStoreByName(String storeName) {
        String dbUrl = "jdbc:mysql://localhost:3306/bargainbuyclub";
        String username = "root";
        String password = "Be225Again!";
        Store store = new Store();
        String query = "SELECT * FROM stores "
                + "WHERE store_name = '" + storeName + "';";
        ResultSet rs = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(this.dbUrl, this.username, this.password);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public void executeUpdate(String query) {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getResultSet(String query) {
        ResultSet rs = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
