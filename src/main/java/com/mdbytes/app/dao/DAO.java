package com.mdbytes.app.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Data interface specifying data access methods required for data implementations.
 *
 * @param <T> the class of Object for which data operations are to be performed.
 */
public interface DAO<T> {

    /**
     * Adds object of class T to database.
     *
     * @param args a String array of object attributes.
     * @return an object of class T.
     */
    T add(String[] args) throws SQLException, IOException;

    /**
     * Retrieves an object of class T from the database given a unique identifier.
     *
     * @param id a unique identifier, an integer.
     * @return an object of class T.
     */
    T get(int id) throws SQLException, IOException;

    /**
     * Updates the database for an object of class T.
     *
     * @param t an object of class T.
     * @return the updated object from the database.
     */
    T update(T t);

    /**
     * Deletes an object of class T.
     *
     * @param id a unique identifier, an integer.
     */
    void delete(int id) throws SQLException;

    /**
     * Retrieves all objects of class T from the database.
     *
     * @return a list of objects of class T.
     */
    List<T> getAll() throws SQLException, IOException;


}
