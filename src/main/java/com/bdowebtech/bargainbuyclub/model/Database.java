/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 */
package com.bdowebtech.bargainbuyclub.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database class contains database credentials, connection, and methods to
 * add, edit, and delete records in the application database. 
 * 
 * @author Martin Dwyer
 */
public class Database extends Environment {
    
    private String dbUrl;
    private String username;
    private String password;
    
    /**
     *  Default constructor activates JDBC connection and defines credentials.
     * 
     */
    public Database() {
        super();
        dbUrl = this.DBURL;
        username = this.USERNAME;
        password = this.PASSWORD;
    }
    
    /**
     * Method executes updates to records in the database, including deletion.
     * 
     * @param query an SQL query beginning with UPDATE or DELETE
     */
    public void executeUpdate(String query) {
        try {
                Connection connection = DriverManager.getConnection(dbUrl, username, password);
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(query);
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
    
    /**
     * Method retrieves records from the database based on selection critea. 
     * 
     * @param query and SQL query beginning with SELECT 
     * 
     * @return results, a ResultSet object
     */
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
