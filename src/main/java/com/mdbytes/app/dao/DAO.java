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
     * @param t an object of class T
     * @return a T class object
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    T add(T t) throws SQLException, IOException;

    /**
     * Retrieves an object of class T from the database given a unique identifier.
     *
     * @param id a unique identifier, an integer.
     * @return an object of class T.
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    T get(int id) throws SQLException, IOException;

    /**
     * Updates the database for an object of class T.
     *
     * @param t an object of class T.
     * @return the updated object from the database.
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    T update(T t) throws SQLException, IOException;

    /**
     * Deletes an object of class T.
     *
     * @param id a unique identifier, an integer.
     * @throws SQLException if one occurs
     */
    void delete(int id) throws SQLException;

    /**
     * Retrieves all objects of class T from the database.
     *
     * @return a list of objects of class T.
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    List<T> getAll() throws SQLException, IOException;

}
