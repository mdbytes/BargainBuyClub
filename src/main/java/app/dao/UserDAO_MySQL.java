package app.dao;

import app.model.Alert;
import app.model.Product;
import app.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_MySQL extends DAO_MySQL implements DAO<User> {

    /**
     * Method to add user which takes a single param, args, a string array
     * in form of [firstName,lastName,emailAddress,password,isAdmin]
     *
     * @param args a String array with field data
     * @return User
     */
    @Override
    public User add(String[] args) {
        return add(args[0], args[1], args[2], args[3], args[4]);
    }

    /**
     * Method adds a user to the database with appropriate user information, returns a User object.
     *
     * @param firstName a String
     * @param lastName  a String
     * @param userName  a String
     * @param password  a String
     * @param isAdmin   a String
     * @return User object.
     */
    public User add(String firstName, String lastName, String userName, String password, String isAdmin) {
        if (getUserByEmailAddress(userName).getUserID() == 0) {
            try {
                System.out.println("trying to make new user ...  ");
                Connection conn = DriverManager.getConnection(this.dbUrl, this.username, this.password);
                CallableStatement stmt = conn.prepareCall("{call add_user(?,?,?,?,?)}");
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, userName);
                stmt.setString(4, DigestUtils.sha256Hex(password));
                stmt.setString(5, isAdmin);
                System.out.println(stmt);
                int count = stmt.executeUpdate();
                System.out.println(count);
                conn.close();
                stmt.close();
                System.out.println("insertion complete");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            User user = getUserByEmailAddress(userName);
            return user;
        } else {
            return getUserByEmailAddress(userName);
        }
    }

    /**
     * Retrieves user object from database
     *
     * @param id an integer
     * @return User object.
     */
    @Override
    public User get(int id) {
        User user = new User();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
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
            connection.close();
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User update(User user) {

        return user;
    }

    /**
     * Deletes user from database
     *
     * @param id an integer
     */
    @Override
    public void delete(int id) {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            CallableStatement statement = connection.prepareCall("{call delete_user_by_id(?)}");
            statement.setInt(1, id);
            int count = statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets all users from the database
     *
     * @return a list of user objects.
     */
    @Override
    public List<User> getAll() {
        ArrayList<User> users = new ArrayList();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
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
            connection.close();
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    /**
     * Retrieves user from database
     *
     * @param emailAddress a String
     * @return User object.
     */
    public User getUserByEmailAddress(String emailAddress) {
        User user = new User();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
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
            connection.close();
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    /**
     * Retrieves user alerts from the database
     *
     * @param userId an integer
     * @return a list of alert objects.
     */
    public List<Alert> getUserAlerts(int userId) {
        List<Alert> userAlerts = new ArrayList<Alert>();
        DAO productDao = new ProductDAO_MySQL();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            CallableStatement statement = connection.prepareCall("{call get_user_alerts(?)}");
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            Alert alert = null;
            while (rs.next()) {
                alert = new Alert(
                        (int) rs.getObject("alert_id"),
                        get((int) rs.getObject("user_id")),
                        (Product) productDao.get((int) rs.getObject("product_id")),
                        (double) rs.getObject("alert_price")
                );
                userAlerts.add(alert);
            }
            connection.close();
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userAlerts;
    }

    /**
     * Method to make an existing user an administrator.
     *
     * @param userID a unique identifier for each user, an integer
     */
    public void makeUserAdmin(int userID) {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            CallableStatement statement = connection.prepareCall("{call make_user_admin(?)}");
            statement.setInt(1, userID);
            int count = statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to validate user credentials against those stored in database.
     *
     * @param username     the user's email address
     * @param userPassword the user's entered password
     * @return a boolean, true or false.
     */
    public boolean validateUser(String username, String userPassword) {
        boolean isValid = false;
        User user = getUserByEmailAddress(username);
        if (user.getPassword().equals(DigestUtils.sha256Hex(userPassword))) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Method for updating existing user's email address.
     *
     * @param userID          a unique identifier for each user, an integer
     * @param newEmailAddress a valid email address
     */
    public void updateUserEmailAddress(int userID, String newEmailAddress) {
        int count = 0;
        try {
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            CallableStatement statement = connection.prepareCall("{call update_user_email(?,?)}");
            statement.setInt(1, userID);
            statement.setString(2, newEmailAddress);
            count = statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method for updating existing user's password.
     *
     * @param userID      a unique identifier for each user, an integer
     * @param newPassword a valid password
     */
    public void updateUserPassword(int userID, String newPassword) {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            CallableStatement statement = connection.prepareCall("{call update_user_password(?,?)}");
            statement.setInt(1, userID);
            statement.setString(2, DigestUtils.sha256Hex(newPassword));
            int count = statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
