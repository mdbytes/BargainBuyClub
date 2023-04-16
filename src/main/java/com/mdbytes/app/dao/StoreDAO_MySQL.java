package com.mdbytes.app.dao;

import com.mdbytes.app.model.Store;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO_MySQL extends DAO_MySQL implements DAO<Store> {

    /**
     * Add store object to database
     *
     * @param store an object of class Store
     * @return a saved store object
     * @throws SQLException if one occurs
     */
    @Override
    public Store add(Store store) throws SQLException {
        if (get(store.getStoreName()).getStoreID() == 0) {
            Store savedStore = new Store();
            Connection connection = makeConnection();
            CallableStatement callableStatement = connection.prepareCall("CALL add_store(?,?,?,?)");
            callableStatement.setString(1, store.getStoreName());
            callableStatement.setString(2, store.getStoreRootUrl());
            callableStatement.setString(3, store.getPriceQuery());
            callableStatement.setString(4, store.getProductNameQuery());
            int count = callableStatement.executeUpdate();
            savedStore = get(store.getStoreName());
            closeConnections(connection, callableStatement, null);
            return savedStore;
        } else {
            return get(store.getStoreName());
        }
    }

    /**
     * @param id a unique identifier, an integer.
     * @return
     * @throws SQLException
     */
    @Override
    public Store get(int id) throws SQLException {
        Connection connection = makeConnection();
        CallableStatement callableStatement = connection.prepareCall("CALL get_store_by_id(?)");
        callableStatement.setInt(1, id);
        ResultSet rs = callableStatement.executeQuery();
        Store store = new Store();
        while (rs.next()) {
            store.setStoreID(id);
            store.setStoreName(rs.getString("store_name"));
            store.setStoreRootUrl(rs.getString("store_url"));
            store.setPriceQuery(rs.getString("price_query"));
            store.setProductNameQuery(rs.getString("product_name_query"));
            break;
        }
        closeConnections(connection, callableStatement, rs);
        return store;
    }

    /**
     * Retrieves a store with the store name
     *
     * @param storeName the name of the store
     * @return a Store object
     * @throws SQLException if one occurs
     */
    public Store get(String storeName) throws SQLException {
        Connection connection = makeConnection();
        CallableStatement callableStatement = connection.prepareCall("CALL get_store_by_name(?)");
        callableStatement.setString(1, storeName);
        ResultSet rs = callableStatement.executeQuery();
        Store store = new Store();
        while (rs.next()) {
            store.setStoreName(storeName);
            store.setStoreID((int) rs.getObject("store_id"));
            store.setStoreRootUrl(rs.getString("store_url"));
            store.setPriceQuery(rs.getString("price_query"));
            store.setProductNameQuery(rs.getString("product_name_query"));
            break;
        }
        closeConnections(connection, callableStatement, rs);
        return store;
    }


    /**
     * Updates store object in the database
     *
     * @param store an object of class Store.
     * @return the updated store object.
     * @throws SQLException if one occurs.
     */
    @Override
    public Store update(Store store) throws SQLException {
        Connection connection = makeConnection();
        CallableStatement callableStatement = connection.prepareCall("CALL update_store(?,?,?,?,?)");
        callableStatement.setInt(1, store.getStoreID());
        callableStatement.setString(2, store.getStoreName());
        callableStatement.setString(3, store.getStoreRootUrl());
        callableStatement.setString(4, store.getPriceQuery());
        callableStatement.setString(5, store.getProductNameQuery());
        int count = callableStatement.executeUpdate();
        closeConnections(connection, callableStatement, null);
        return store;
    }

    /**
     * Deletes store object in the database
     *
     * @param id a unique identifier, an integer.
     * @throws SQLException if one occurs
     */
    @Override
    public void delete(int id) throws SQLException {
        Connection connection = makeConnection();
        CallableStatement callableStatement = connection.prepareCall("CALL delete_store_by_id(?)");
        callableStatement.setInt(1, id);
        int count = callableStatement.executeUpdate();
        closeConnections(connection, callableStatement, null);
    }

    /**
     * Retrievs all store objects from the database
     *
     * @return a list of Store objects
     * @throws SQLException
     */
    @Override
    public List<Store> getAll() throws SQLException {
        ArrayList<Store> stores = new ArrayList<Store>();
        Connection connection = makeConnection();
        CallableStatement callableStatement = connection.prepareCall("{CALL get_stores()}");
        ResultSet rs = callableStatement.executeQuery();
        while (rs.next()) {
            Store store = new Store();
            store.setStoreID(rs.getInt("store_id"));
            store.setStoreName(rs.getString("store_name"));
            store.setStoreRootUrl(rs.getString("store_url"));
            store.setPriceQuery(rs.getString("price_query"));
            store.setProductNameQuery(rs.getString("product_name_query"));
            stores.add(store);
        }
        closeConnections(connection, callableStatement, rs);
        return stores;
    }
}



