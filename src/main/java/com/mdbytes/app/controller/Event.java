package com.mdbytes.app.controller;

import com.mdbytes.app.dao.AlertDAO_MySQL;
import com.mdbytes.app.dao.ProductDAO_MySQL;
import com.mdbytes.app.dao.StoreDAO_MySQL;
import com.mdbytes.app.dao.UserDAO_MySQL;
import com.mdbytes.app.model.Alert;
import com.mdbytes.app.model.Product;
import com.mdbytes.app.model.User;

import javax.servlet.http.HttpSession;

/**
 * Provides the framework for the application environment when a user session is underway. Initiates upon
 * first request from the home page.  Updates as needed throughout user interaction.
 */
public class Event {

    /**
     * The user - once they have been identified upon signing up or signing in.
     */
    protected User user;

    /**
     * The user's unique identifier
     */
    protected int userID;

    /**
     * The user's email address
     */
    protected String userName;

    /**
     * User's encrypted password
     */
    protected String password;

    /**
     * User's first name
     */
    protected String firstName;

    /**
     * User's last name
     */
    protected String lastName;

    /**
     * Alert object selected
     */
    protected Alert alert;

    /**
     * Alert object unique id
     */
    protected int alertID;

    /**
     * Product object selected
     */
    protected Product product;

    /**
     * Store id
     */
    protected int storeID;

    /**
     * Product url location
     */
    protected String productUrl;

    /**
     * Price the user desires to be notified
     */
    protected double alertPrice;

    /**
     * Data access object for handling user related transactions
     */
    protected UserDAO_MySQL userDao;

    /**
     * Data access object for handling store related transactions
     */
    protected StoreDAO_MySQL storeDao;

    /**
     * Data access object for handling product related transactions
     */
    protected ProductDAO_MySQL productDao;

    /**
     * Data access object for handling alert related transactions
     */
    protected AlertDAO_MySQL alertDao;

    /**
     * Id of the user session initiated
     */
    protected String sessionID;

    /**
     * Default constructor creates null objects along with new data access objects which
     * can be used on event handlers as needed
     */
    public Event() {
        this.user = null;
        this.userID = 0;
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.alert = null;
        this.alertID = 0;
        this.product = null;
        this.storeID = 0;
        this.productUrl = "";
        this.alertPrice = 0.0;
        this.sessionID = "";

        userDao = new UserDAO_MySQL();
        storeDao = new StoreDAO_MySQL();
        productDao = new ProductDAO_MySQL();
        alertDao = new AlertDAO_MySQL();

    }

    /**
     * Default constructor creates null objects along with new data access objects which
     * can be used on event handlers as needed
     */
    public Event(User user, HttpSession session) {
        this.user = null;
        this.userID = 0;
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.alert = null;
        this.alertID = 0;
        this.product = null;
        this.storeID = 0;
        this.productUrl = "";
        this.alertPrice = 0.0;
        this.sessionID = "";

        userDao = new UserDAO_MySQL();
        storeDao = new StoreDAO_MySQL();
        productDao = new ProductDAO_MySQL();
        alertDao = new AlertDAO_MySQL();

    }
}
