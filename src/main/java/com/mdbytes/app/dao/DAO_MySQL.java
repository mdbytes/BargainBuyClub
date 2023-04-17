package com.mdbytes.app.dao;

import com.mdbytes.app.config.Env;

import java.sql.*;

public class DAO_MySQL {

    protected String dbUrl;
    protected String username;
    protected String password;
    protected Env env = new Env();
    protected Connection connection;
    protected ResultSet resultSet;
    protected CallableStatement callableStatement;

    public DAO_MySQL() {
        dbUrl = env.DBURL;
        username = env.USERNAME;
        password = env.PASSWORD;
    }

    public Connection makeConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        connection = DriverManager.getConnection(dbUrl, username, password);
        return connection;
    }

    public void closeConnections() throws SQLException {
        this.connection.close();
        this.callableStatement.close();
        if (resultSet != null) this.resultSet.close();
    }

}
