package com.mdbytes.app.controller;

import com.mdbytes.app.dao.AlertDAO_MySQL;
import com.mdbytes.app.dao.ProductDAO_MySQL;
import com.mdbytes.app.dao.StoreDAO_MySQL;
import com.mdbytes.app.dao.UserDAO_MySQL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides the framework for the application environment when a user session is underway. Initiates upon
 * first request from the home page.  Updates as needed throughout user interaction.
 */
public class Event {

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
     * Default constructor creates null objects along with new data access objects which
     * can be used on event handlers as needed
     */
    public Event() {
        userDao = new UserDAO_MySQL();
        storeDao = new StoreDAO_MySQL();
        productDao = new ProductDAO_MySQL();
        alertDao = new AlertDAO_MySQL();
    }

    protected void handleException(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        try {
            request.getSession().setAttribute("errormessage", errorMessage);
            request.setAttribute("page", "home");
            request.getRequestDispatcher("WEB-INF/bbc/index.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("App won't deliver home page.  Crap!");
        }

    }
}
