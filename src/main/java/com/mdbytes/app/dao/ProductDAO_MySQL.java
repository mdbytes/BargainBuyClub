package com.mdbytes.app.dao;

import com.mdbytes.app.model.Product;
import com.mdbytes.app.model.Store;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO_MySQL extends DAO_MySQL implements DAO<Product> {

    public ProductDAO_MySQL() {
        super();
    }

    /**
     * Adds a Product object to the database
     *
     * @param product an object of class Product
     * @return a saved Product object
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    @Override
    public Product add(Product product) throws SQLException, IOException {
        DAO storeDao = new StoreDAO_MySQL();

        if (get(product.getProductUrl()).getProductID() == 0) {
            Product savedProduct = new Product();
            Date today = Date.valueOf(LocalDate.now());
            connection = makeConnection();
            callableStatement = connection.prepareCall("CALL add_product(?,?,?,?,?)");
            callableStatement.setInt(1, product.getStore().getStoreID());
            callableStatement.setString(2, product.getProductUrl());
            callableStatement.setString(3, product.getProductName());
            callableStatement.setDouble(4, product.getProductPrice());
            callableStatement.setDate(5, today);
            int count = callableStatement.executeUpdate();
            savedProduct = get(product.getProductUrl());
            closeConnections();
            return savedProduct;
        } else {
            return get(product.getProductUrl());
        }
    }

    /**
     * Gets a Product object
     *
     * @param id a unique identifier, an integer.
     * @return a Product object.
     * @throws SQLException if one occurs.
     * @throws IOException  if one occurs.
     */
    @Override
    public Product get(int id) throws SQLException, IOException {
        Product product = new Product();
        DAO storeDao = new StoreDAO_MySQL();
        connection = makeConnection();
        callableStatement = connection.prepareCall("CALL get_product_by_id(?)");
        callableStatement.setInt(1, id);
        resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            product.setProductID(id);
            Store store = (Store) storeDao.get((int) resultSet.getObject("store_id"));
            product.setStore(store);
            product.setProductUrl(resultSet.getString("product_url"));
            product.setProductName(resultSet.getString("product_name"));
            product.setProductPrice(resultSet.getDouble("recent_price"));
            product.setLastUpdated(resultSet.getDate("last_updated"));
            break;
        }
        closeConnections();
        return product;
    }

    /**
     * Retrieves a product object
     *
     * @param productURL the products url
     * @return a Product object
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    public Product get(String productURL) throws SQLException, IOException {
        Product product = new Product();
        DAO storeDao = new StoreDAO_MySQL();
        connection = makeConnection();
        callableStatement = connection.prepareCall("CALL get_product_by_url(?)");
        callableStatement.setString(1, productURL);
        resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            product.setProductID((int) resultSet.getObject("product_id"));
            Store store = (Store) storeDao.get((int) resultSet.getObject("store_id"));
            product.setStore(store);
            product.setProductUrl(productURL);
            product.setProductName(resultSet.getString("product_name"));
            product.setProductPrice(resultSet.getDouble("recent_price"));
            product.setLastUpdated(resultSet.getDate("last_updated"));
            break;
        }
        closeConnections();
        return product;
    }

    /**
     * Updates a Product object in the database
     *
     * @param product an object of class Product.
     * @return a saved product object.
     * @throws SQLException if one occurs.
     * @throws IOException  if one occurs.
     */
    @Override
    public Product update(Product product) throws SQLException, IOException {
        Product savedProduct = new Product();
        Date today = Date.valueOf(LocalDate.now());
        connection = makeConnection();
        callableStatement = connection.prepareCall("CALL update_product(?,?,?,?,?,?)");
        callableStatement.setInt(1, product.getProductID());
        callableStatement.setInt(2, product.getStore().getStoreID());
        callableStatement.setString(3, product.getProductUrl());
        callableStatement.setString(4, product.getProductName());
        callableStatement.setDouble(5, product.getProductPrice());
        callableStatement.setDate(6, today);
        int count = callableStatement.executeUpdate();
        savedProduct = get(product.getProductUrl());
        closeConnections();
        return savedProduct;
    }

    /**
     * Deletes a product object
     *
     * @param id a unique identifier, an integer.
     * @throws SQLException if one occurs
     */
    @Override
    public void delete(int id) throws SQLException {
        connection = makeConnection();
        callableStatement = connection.prepareCall("CALL delete_product_by_id(?)");
        callableStatement.setInt(1, id);
        int count = callableStatement.executeUpdate();
        closeConnections();
    }

    /**
     * Gets all products
     *
     * @return a list of Product objects
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    @Override
    public List<Product> getAll() throws SQLException, IOException {

        connection = makeConnection();
        callableStatement = connection.prepareCall("{CALL get_products()}");
        resultSet = callableStatement.executeQuery();

        ArrayList<Product> products = new ArrayList<Product>();
        DAO storeDao = new StoreDAO_MySQL();

        while (resultSet.next()) {
            Product product = new Product();
            product.setProductID((int) resultSet.getObject("product_id"));
            product.setStore((Store) storeDao.get((int) resultSet.getObject("store_id")));
            product.setProductUrl(resultSet.getString("product_url"));
            product.setProductName(resultSet.getString("product_name"));
            product.setProductPrice(resultSet.getDouble("recent_price"));
            product.setLastUpdated(resultSet.getDate("last_updated"));
            products.add(product);
        }
        closeConnections();
        return products;
    }
}
