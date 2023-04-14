package com.mdbytes.app.dao;

import com.mdbytes.app.model.Alert;
import com.mdbytes.app.model.Product;
import com.mdbytes.app.model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertDAO_MySQL extends DAO_MySQL implements DAO<Alert> {

    public AlertDAO_MySQL() {
        super();
    }

    @Override
    public Alert add(String[] args) throws SQLException, IOException {
        return addAlert(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]));
    }

    /**
     * Method to add alerts into application database.
     *
     * @param productID  the product ID for the alert Product object.
     * @param userID     the user ID for the alert User object.
     * @param alertPrice the alert price, a double.
     * @return Alert.  An alert object.
     */
    public Alert addAlert(int productID, int userID, double alertPrice) throws SQLException, IOException {
        if (getAlertByAttributes(productID, userID, alertPrice).getAlertID() == 0) {
            Alert alert = new Alert();

            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            CallableStatement statement = connection.prepareCall("CALL add_alert(?,?,?)");
            statement.setInt(1, productID);
            statement.setInt(2, userID);
            statement.setDouble(3, alertPrice);
            int count = statement.executeUpdate();

            alert = getAlertByAttributes(productID, userID, alertPrice);
            return alert;
        } else {
            return getAlertByAttributes(productID, userID, alertPrice);
        }
    }

    /**
     * Method retrieves alert with given id
     *
     * @param id an integer.
     * @return Alert object with alert_id matching id
     */
    @Override
    public Alert get(int id) throws SQLException, IOException {
        Alert alert = new Alert();
        DAO productDao = new ProductDAO_MySQL();
        DAO userDao = new UserDAO_MySQL();

        String query = "SELECT * FROM alerts "
                + "WHERE alert_id = " + id + ";";

        ResultSet rs = null;

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Statement statement = connection.createStatement();
        rs = statement.executeQuery(query);
        while (rs.next()) {
            alert.setAlertID((int) rs.getObject("alert_id"));
            alert.setProduct((Product) productDao.get((int) rs.getObject("product_id")));
            alert.setUser((User) userDao.get((int) rs.getObject("user_id")));
            alert.setAlertPrice((double) rs.getObject("alert_price"));
            break;
        }

        return alert;
    }

    @Override
    public Alert update(Alert alert) {
        return null;
    }

    /**
     * Method to retrieve alert from database with the unique alert ID.
     *
     * @param id the unique identifier for the alert object, an integer
     */
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM alerts "
                + "WHERE alert_id = " + id + ";";

        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Statement statement = connection.createStatement();
        int count = statement.executeUpdate(query);


    }

    /**
     * Method to retrieve all alerts in the application database.
     *
     * @return alerts, a list of Alert objects
     */
    @Override
    public List<Alert> getAll() throws SQLException, IOException {
        Alert alert = new Alert();
        ArrayList<Alert> alerts = new ArrayList<Alert>();
        DAO userDao = new UserDAO_MySQL();
        DAO productDao = new ProductDAO_MySQL();
        String query = "SELECT * FROM alerts;";
        ResultSet rs = null;
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        CallableStatement statement = connection.prepareCall("{CALL get_alerts()}");
        rs = statement.executeQuery(query);
        while (rs.next()) {
            alerts.add(new Alert((int) rs.getObject("alert_id"),
                    (User) userDao.get((int) rs.getObject("user_id")),
                    (Product) productDao.get((int) rs.getObject("product_id")),
                    (double) rs.getObject("alert_price")));
        }

        return alerts;

    }

    /**
     * Method to retrieve alert object from database based on attributes.
     *
     * @param productID  the product ID for the alert Product object
     * @param userID     the user ID for the alert User object
     * @param alertPrice the alert price, a double
     * @return Alert object.
     */
    public Alert getAlertByAttributes(int productID, int userID, double alertPrice) throws SQLException, IOException {
        Alert alert = new Alert();
        DAO productDao = new ProductDAO_MySQL();
        DAO userDao = new UserDAO_MySQL();

        String query = "SELECT * FROM alerts "
                + "WHERE product_id = '" + productID + "'"
                + "AND user_id = " + userID + " "
                + "AND alert_price = " + alertPrice + ";";
        ResultSet rs = null;
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Statement statement = connection.createStatement();
        rs = statement.executeQuery(query);
        while (rs.next()) {
            alert.setAlertID((int) rs.getObject("alert_id"));
            alert.setProduct((Product) productDao.get((int) rs.getObject("alert_id")));
            alert.setUser((User) userDao.get((int) rs.getObject("user_id")));
            alert.setAlertPrice(alertPrice);
            break;
        }

        return alert;
    }

    /**
     * Method to update the alert price for an existing alert.
     *
     * @param alertID    the unique identifier for an alert, an integer
     * @param alertPrice the alert price for the alert, a double
     */
    public void updateAlertPrice(int alertID, double alertPrice) throws SQLException {
        String query = "UPDATE alerts "
                + "SET alert_price = " + alertPrice +
                " WHERE alert_id = " + alertID + ";";

        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        Statement statement = connection.createStatement();
        int count = statement.executeUpdate(query);


    }

}
