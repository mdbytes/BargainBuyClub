package com.mdbytes.app.dao;

import com.mdbytes.app.model.Alert;
import com.mdbytes.app.model.Product;
import com.mdbytes.app.model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertDAO_MySQL extends DAO_MySQL implements DAO<Alert> {


    public AlertDAO_MySQL() throws SQLException {
        super();
    }

    /**
     * Method to add alerts into application database.
     *
     * @param alert an Alert object
     * @return a saved Alert object
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    @Override
    public Alert add(Alert alert) throws SQLException, IOException {
        int productID = alert.getProduct().getProductID();
        int userID = alert.getUser().getUserID();
        double alertPrice = alert.getAlertPrice();
        if (get(productID, userID, alertPrice).getAlertID() == 0) {
            Alert savedAlert = new Alert();
            Connection connection = makeConnection();
            CallableStatement callableStatement = connection.prepareCall("CALL add_alert(?,?,?)");
            callableStatement.setInt(1, productID);
            callableStatement.setInt(2, userID);
            callableStatement.setDouble(3, alertPrice);
            int count = callableStatement.executeUpdate();
            savedAlert = get(productID, userID, alertPrice);
            closeConnections(connection, callableStatement, null);
            return savedAlert;
        } else {
            return get(productID, userID, alertPrice);
        }
    }

    /**
     * Method retrieves alert with given id
     *
     * @param id a unique identifier, an integer.
     * @return an Alert object
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    @Override
    public Alert get(int id) throws SQLException, IOException {
        Alert alert = new Alert();
        DAO productDao = new ProductDAO_MySQL();
        DAO userDao = new UserDAO_MySQL();
        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        CallableStatement callableStatement = connection.prepareCall("CALL get_alert_by_id(?)");
        callableStatement.setInt(1, id);
        ResultSet rs = callableStatement.executeQuery();
        while (rs.next()) {
            alert.setAlertID((int) rs.getObject("alert_id"));
            alert.setProduct((Product) productDao.get((int) rs.getObject("product_id")));
            alert.setUser((User) userDao.get((int) rs.getObject("user_id")));
            alert.setAlertPrice((double) rs.getObject("alert_price"));
            break;
        }
        closeConnections(connection, callableStatement, rs);
        return alert;
    }

    /**
     * Method to retrieve alert object from database based on attributes.
     *
     * @param productID  the product ID for the alert Product object
     * @param userID     the user ID for the alert User object
     * @param alertPrice the alert price, a double
     * @return an Alert object
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    public Alert get(int productID, int userID, double alertPrice) throws SQLException, IOException {
        Alert alert = new Alert();
        DAO productDao = new ProductDAO_MySQL();
        DAO userDao = new UserDAO_MySQL();
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        CallableStatement callableStatement = connection.prepareCall("CALL get_alert_by_attributes(?,?,?)");
        callableStatement.setInt(1, productID);
        callableStatement.setInt(2, userID);
        callableStatement.setDouble(3, alertPrice);
        ResultSet rs = callableStatement.executeQuery();
        while (rs.next()) {
            alert.setAlertID((int) rs.getObject("alert_id"));
            alert.setProduct((Product) productDao.get((int) rs.getObject("product_id")));
            alert.setUser((User) userDao.get((int) rs.getObject("user_id")));
            alert.setAlertPrice(alertPrice);
            break;
        }
        closeConnections(connection, callableStatement, rs);
        return alert;
    }

    /***
     * Updates alert object in database
     *
     * @param alert an object of class Alert.
     * @return a saved Alert object
     * @throws SQLException if one occurs
     */
    @Override
    public Alert update(Alert alert) throws SQLException {
        Connection connection = makeConnection();
        CallableStatement callableStatement = connection.prepareCall("CALL update_alert(?,?,?,?)");
        callableStatement.setInt(1, alert.getAlertID());
        callableStatement.setInt(2, alert.getProduct().getProductID());
        callableStatement.setInt(3, alert.getUser().getUserID());
        callableStatement.setDouble(4, alert.getAlertPrice());
        int count = callableStatement.executeUpdate();
        closeConnections(connection, callableStatement, null);
        if (count == 1) return alert;
        return null;
    }

    /**
     * Method to retrieve alert from database with the unique alert ID.
     *
     * @param id a unique identifier, an integer.
     * @throws SQLException if one occurs
     */
    @Override
    public void delete(int id) throws SQLException {
        Connection connection = makeConnection();
        CallableStatement callableStatement = connection.prepareCall("CALL delete_alert_by_id(?)");
        callableStatement.setInt(1, id);
        int count = callableStatement.executeUpdate();
        closeConnections(connection, callableStatement, null);
    }

    /**
     * Method to retrieve all alerts in the application database.
     *
     * @return alerts, a list of Alert objects
     * @throws SQLException if one occurs
     * @throws IOException  if one occurs
     */
    @Override
    public List<Alert> getAll() throws SQLException, IOException {
        Alert alert = new Alert();
        ArrayList<Alert> alerts = new ArrayList<Alert>();
        DAO userDao = new UserDAO_MySQL();
        DAO productDao = new ProductDAO_MySQL();
        Connection connection = makeConnection();
        CallableStatement callableStatement = connection.prepareCall("{CALL get_alerts()}");
        ResultSet rs = callableStatement.executeQuery();
        while (rs.next()) {
            alerts.add(new Alert((int) rs.getObject("alert_id"),
                    (User) userDao.get((int) rs.getObject("user_id")),
                    (Product) productDao.get((int) rs.getObject("product_id")),
                    (double) rs.getObject("alert_price")));
        }
        closeConnections(connection, callableStatement, rs);
        return alerts;
    }

    /**
     * Retrieves user alerts from the database
     *
     * @param userId an integer
     * @return a list of alert objects.
     */
    public List<Alert> getAll(int userId) throws SQLException, IOException {
        List<Alert> userAlerts = new ArrayList<Alert>();
        DAO productDao = new ProductDAO_MySQL();
        DAO userDao = new UserDAO_MySQL();

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        CallableStatement statement = connection.prepareCall("{call get_user_alerts(?)}");
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();
        Alert alert = null;
        while (rs.next()) {
            alert = new Alert(
                    (int) rs.getObject("alert_id"),
                    (User) userDao.get((int) rs.getObject("user_id")),
                    (Product) productDao.get((int) rs.getObject("product_id")),
                    (double) rs.getObject("alert_price")
            );
            userAlerts.add(alert);
        }
        connection.close();
        statement.close();
        rs.close();

        return userAlerts;
    }

    /**
     * Method to update the alert price for an existing alert.
     *
     * @param alertID    the unique identifier for an alert, an integer
     * @param alertPrice the alert price for the alert, a double
     * @throws SQLException if one occurs
     */
    public void update(int alertID, double alertPrice) throws SQLException {
        Connection connection = makeConnection();
        CallableStatement callableStatement = connection.prepareCall("CALL update_alert_price(?,?)");
        callableStatement.setInt(1, alertID);
        callableStatement.setDouble(2, alertPrice);
        int count = callableStatement.executeUpdate();
        closeConnections(connection, callableStatement, null);
    }
}
