package com.mdbytes.app.controller.events;

import com.mdbytes.app.dao.AlertDAO_MySQL;
import com.mdbytes.app.dao.ProductDAO_MySQL;
import com.mdbytes.app.dao.StoreDAO_MySQL;
import com.mdbytes.app.dao.UserDAO_MySQL;
import com.mdbytes.app.model.Alert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    /**
     * Default constructor creates null objects along with new data access objects which
     * can be used on event handlers as needed
     *
     * @param request  an active Http request
     * @param response an active Http response
     */
    public Event(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.request = request;
            this.response = response;
            this.userDao = new UserDAO_MySQL();
            this.storeDao = new StoreDAO_MySQL();
            this.productDao = new ProductDAO_MySQL();
            this.alertDao = new AlertDAO_MySQL();
        } catch (Exception e) {
            handleException(request, response, "Cannot access database at this time.");
        }
    }

    /**
     * Handles all exceptions in the application by receiving an error message and redirecting the
     * user back to the home page.  Once there an alert message will show the user the error message.
     *
     * @param request      the HTTPServlet request object.
     * @param response     the HTTPServlet response object.
     * @param errorMessage a String containing the particular error message.
     */
    public void handleException(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        try {
            HomeEvent homeEvent = new HomeEvent(request, response);
            request.getSession().setAttribute("errormessage", errorMessage);
            request.setAttribute("page", "home");
            List<Alert> homeAlerts = null;
            if (request.getAttribute("homeAlerts") != null) {
                homeAlerts = (ArrayList<Alert>) request.getAttribute("homeAlerts");
            } else {
                homeAlerts = homeEvent.loadHome();
                request.setAttribute("homeAlerts", homeAlerts);
            }
            request.getRequestDispatcher("WEB-INF/bbc/index.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("App won't deliver home page!  Ran out of luck!!  Crap!!!");
        }
    }
}
