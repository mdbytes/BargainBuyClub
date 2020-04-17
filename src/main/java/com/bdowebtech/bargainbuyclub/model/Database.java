/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 */
package com.bdowebtech.bargainbuyclub.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author marti
 */
public class Database {
    
    private String dbUrl = "jdbc:mysql://localhost:3306/bargainbuyclub";
    private String username = "root";
    private String password = "Be225Again!";
    
    public Database() {
        
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
