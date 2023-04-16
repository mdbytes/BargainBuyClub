package com.mdbytes.app.dao;

import com.mdbytes.app.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_MySQL extends DAO_MySQL implements DAO<User> {

    /**
     * Method to add user which takes a single param, args, a string array
     * in form of [firstName,lastName,emailAddress,password,isAdmin]
     *
     * @param user a User object.
     * @return User object.
     * @throws SQLException if one occurs.
     */
    @Override
    public User add(User user) throws SQLException {
        if (get(user.getEmailAddress()).getUserID() == 0) {
            Connection connection = makeConnection();
            CallableStatement callableStatement = connection.prepareCall("{call add_user(?,?,?,?,?)}");
            callableStatement.setString(1, user.getFirstName());
            callableStatement.setString(2, user.getLastName());
            callableStatement.setString(3, user.getEmailAddress());
            callableStatement.setString(4, DigestUtils.sha256Hex(user.getPassword()));
            callableStatement.setString(5, String.valueOf(user.isIsAdmin()));
            int count = callableStatement.executeUpdate();
            User savedUser = get(user.getEmailAddress());
            closeConnections(connection, callableStatement, null);
            return savedUser;
        } else {
            return get(user.getEmailAddress());
        }
    }

    /**
     * Retrieves user object from database
     *
     * @param id an integer.
     * @return User object.
     * @throws SQLException if one occurs.
     */
    @Override
    public User get(int id) throws SQLException {
        User user = new User();
        Connection connection = makeConnection();
        CallableStatement statement = connection.prepareCall("{call get_user_by_id(?)}");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String userName = rs.getString("email_address");
            String passWord = rs.getString("password");
            boolean isAdmin = Boolean.valueOf(rs.getString("is_admin"));
            user = new User(id, firstName, lastName, userName, passWord, isAdmin);
            break;
        }
        closeConnections(connection, statement, rs);
        return user;
    }

    /**
     * Retrieves user from database
     *
     * @param emailAddress a String
     * @return User object.
     * @throws SQLException if one occurs.
     */
    public User get(String emailAddress) throws SQLException {
        User user = new User();
        Connection connection = makeConnection();
        CallableStatement statement = connection.prepareCall("{CALL get_user_by_email(?)}");
        statement.setString(1, emailAddress);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int userID = (int) rs.getObject("user_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String userName = rs.getString("email_address");
            String passWord = rs.getString("password");
            boolean isAdmin = Boolean.valueOf(rs.getString("is_admin"));
            user = new User(userID, firstName, lastName, userName, passWord, isAdmin);
            break;
        }
        closeConnections(connection, statement, rs);
        return user;
    }

    /**
     * Updates user object on database.
     *
     * @param user an object of class T.
     * @return the updated and saved User object.
     * @throws SQLException if one occurs.
     */
    @Override
    public User update(User user) throws SQLException {
        Connection connection = makeConnection();
        CallableStatement callableStatement = connection.prepareCall("{call update_user(?,?,?,?,?,?)}");
        callableStatement.setInt(1, user.getUserID());
        callableStatement.setString(2, user.getFirstName());
        callableStatement.setString(3, user.getLastName());
        callableStatement.setString(4, user.getEmailAddress());
        callableStatement.setString(5, DigestUtils.sha256Hex(user.getPassword()));
        callableStatement.setString(6, String.valueOf(user.isIsAdmin()));
        int count = callableStatement.executeUpdate();
        User savedUser = get(user.getEmailAddress());
        closeConnections(connection, callableStatement, null);
        return savedUser;
    }

    /**
     * Deletes user from database
     *
     * @param id a unique identifier, an integer.
     * @throws SQLException if one occurs.
     */
    @Override
    public void delete(int id) throws SQLException {
        Connection connection = makeConnection();
        CallableStatement statement = connection.prepareCall("{call delete_user_by_id(?)}");
        statement.setInt(1, id);
        int count = statement.executeUpdate();
        closeConnections(connection, statement, null);
    }

    /**
     * Gets all users from the database
     *
     * @return a list of user objects.
     * @throws SQLException if one occurs.
     */
    @Override
    public List<User> getAll() throws SQLException {
        ArrayList<User> users = new ArrayList();
        Connection connection = makeConnection();
        CallableStatement statement = connection.prepareCall("{CALL get_users()}");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int userID = (int) rs.getObject("user_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String userName = rs.getString("email_address");
            String passWord = rs.getString("password");
            boolean isAdmin = Boolean.valueOf(rs.getString("is_admin"));
            users.add(new User(userID, firstName, lastName, userName, passWord, isAdmin));
        }
        closeConnections(connection, statement, rs);
        return users;
    }

    /**
     * Method to validate user credentials against those stored in database.
     *
     * @param username     the user's email address
     * @param userPassword the user's entered password
     * @return a boolean, true or false.
     * @throws SQLException if one occurs.
     */
    public boolean validateUser(String username, String userPassword) throws SQLException {
        boolean isValid = false;
        User user = get(username);
        if (user.getPassword().equals(DigestUtils.sha256Hex(userPassword))) {
            isValid = true;
        }
        return isValid;
    }

}
