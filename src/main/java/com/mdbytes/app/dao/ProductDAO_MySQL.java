package com.mdbytes.app.dao;

import com.mdbytes.app.model.Product;
import com.mdbytes.app.model.Store;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class ProductDAO_MySQL extends DAO_MySQL implements DAO<Product> {

    public ProductDAO_MySQL() {
        super();
    }

    public Product add(Product product) throws SQLException, IOException {
        DAO storeDao = new StoreDAO_MySQL();

        if (getProductByURL(product.getProductUrl()).getProductID() == 0) {
            Product savedProduct = new Product();
            Date today = Date.valueOf(LocalDate.now());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);

            CallableStatement callableStatement = connection.prepareCall("CALL add_product(?,?,?,?,?)");
            callableStatement.setInt(1, product.getStore().getStoreID());
            callableStatement.setString(2, product.getProductUrl());
            callableStatement.setString(3, product.getProductName());
            callableStatement.setDouble(4, product.getProductPrice());
            callableStatement.setDate(5, today);
            int count = callableStatement.executeUpdate();
            savedProduct = getProductByURL(product.getProductUrl());
            return savedProduct;
        } else {
            return getProductByURL(product.getProductUrl());
        }
    }

    @Override
    public Product get(int id) throws SQLException, IOException {
        Product product = new Product();
        DAO storeDao = new StoreDAO_MySQL();
        String query = "SELECT * FROM products "
                + "WHERE product_id = " + id + ";";
        ResultSet rs = null;
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Statement statement = connection.createStatement();
        rs = statement.executeQuery(query);
        while (rs.next()) {
            product.setProductID(id);
            Store store = (Store) storeDao.get((int) rs.getObject("store_id"));
            product.setStore(store);
            product.setProductUrl(rs.getString("product_url"));
            product.setProductName(rs.getString("product_name"));
            product.setProductPrice(rs.getDouble("recent_price"));
            product.setLastUpdated(rs.getDate("last_updated"));
            break;
        }

        return product;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    public Product getProductByURL(String productURL) throws SQLException, IOException {
        Product product = new Product();
        DAO storeDao = new StoreDAO_MySQL();

        String query = "SELECT * FROM products "
                + "WHERE product_url = '" + productURL + "';";
        ResultSet rs = null;

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Statement statement = connection.createStatement();
        rs = statement.executeQuery(query);

        while (rs.next()) {
            product.setProductID((int) rs.getObject("product_id"));
            Store store = (Store) storeDao.get((int) rs.getObject("store_id"));
            product.setStore(store);
            product.setProductUrl(productURL);
            product.setProductName(rs.getString("product_name"));
            product.setProductPrice(rs.getDouble("recent_price"));
            product.setLastUpdated(rs.getDate("last_updated"));
            break;
        }

        return product;
    }
}
