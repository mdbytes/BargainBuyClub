package com.mdbytes.app.dao;

import com.mdbytes.app.config.Env;

import java.sql.*;

public class DAO_MySQL {

    protected String dbUrl;
    protected String username;
    protected String password;
    protected Env env = new Env();

    public DAO_MySQL() {
        dbUrl = env.DBURL;
        username = env.USERNAME;
        password = env.PASSWORD;
    }

    public Connection makeConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        return connection;
    }

    public void closeConnections(Connection connection, CallableStatement callableStatement, ResultSet rs) throws SQLException {
        connection.close();
        callableStatement.close();
        if (rs != null) rs.close();
    }

}
