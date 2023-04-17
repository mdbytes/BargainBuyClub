package com.mdbytes.app.dao;

import com.mdbytes.app.model.User;
import org.apache.commons.codec.digest.DigestUtils;

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
            connection = makeConnection();
            callableStatement = connection.prepareCall("{call add_user(?,?,?,?,?)}");
            callableStatement.setString(1, user.getFirstName());
            callableStatement.setString(2, user.getLastName());
            callableStatement.setString(3, user.getEmailAddress());
            callableStatement.setString(4, DigestUtils.sha256Hex(user.getPassword()));
            callableStatement.setString(5, String.valueOf(user.isIsAdmin()));
            int count = callableStatement.executeUpdate();
            User savedUser = get(user.getEmailAddress());
            closeConnections();
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
        connection = makeConnection();
        callableStatement = connection.prepareCall("{call get_user_by_id(?)}");
        callableStatement.setInt(1, id);
        resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String userName = resultSet.getString("email_address");
            String passWord = resultSet.getString("password");
            boolean isAdmin = Boolean.valueOf(resultSet.getString("is_admin"));
            user = new User(id, firstName, lastName, userName, passWord, isAdmin);
            break;
        }
        closeConnections();
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
        connection = makeConnection();
        callableStatement = connection.prepareCall("{CALL get_user_by_email(?)}");
        callableStatement.setString(1, emailAddress);
        resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            int userID = (int) resultSet.getObject("user_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String userName = resultSet.getString("email_address");
            String passWord = resultSet.getString("password");
            boolean isAdmin = Boolean.valueOf(resultSet.getString("is_admin"));
            user = new User(userID, firstName, lastName, userName, passWord, isAdmin);
            break;
        }
        closeConnections();
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
        connection = makeConnection();
        callableStatement = connection.prepareCall("{call update_user(?,?,?,?,?,?)}");
        callableStatement.setInt(1, user.getUserID());
        callableStatement.setString(2, user.getFirstName());
        callableStatement.setString(3, user.getLastName());
        callableStatement.setString(4, user.getEmailAddress());
        callableStatement.setString(5, DigestUtils.sha256Hex(user.getPassword()));
        callableStatement.setString(6, String.valueOf(user.isIsAdmin()));
        int count = callableStatement.executeUpdate();
        User savedUser = get(user.getEmailAddress());
        closeConnections();
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
        connection = makeConnection();
        callableStatement = connection.prepareCall("{call delete_user_by_id(?)}");
        callableStatement.setInt(1, id);
        int count = callableStatement.executeUpdate();
        closeConnections();
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
        connection = makeConnection();
        callableStatement = connection.prepareCall("{CALL get_users()}");
        resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            int userID = (int) resultSet.getObject("user_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String userName = resultSet.getString("email_address");
            String passWord = resultSet.getString("password");
            boolean isAdmin = Boolean.valueOf(resultSet.getString("is_admin"));
            users.add(new User(userID, firstName, lastName, userName, passWord, isAdmin));
        }
        closeConnections();
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
