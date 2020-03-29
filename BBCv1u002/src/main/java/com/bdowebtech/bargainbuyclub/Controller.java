/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bdowebtech.bargainbuyclub;

import com.bdowebtech.bargainbuyclub.model.Alert;
import com.bdowebtech.bargainbuyclub.model.Data.Database;
import com.bdowebtech.bargainbuyclub.model.Product;
import com.bdowebtech.bargainbuyclub.model.Store;
import com.bdowebtech.bargainbuyclub.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author marti
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private Database database = new Database();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = "";
        String password = "";
        String firstName = "";
        String lastName = "";
        String sessionID = "";
        int alertID = 0;
        switch (request.getParameter("action")) {
            case "home":
                request.getSession().invalidate();
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "login-user":
                userName = request.getParameter("username");
                password = request.getParameter("password");
                if (database.validateUser(userName, password)) {
                    HttpSession newSession = request.getSession(true);
                    newSession.setMaxInactiveInterval(300);
                    newSession.setAttribute("username", userName);
                    newSession.setAttribute("database", database);
                    newSession.setAttribute("session", newSession);
                    newSession.setAttribute("useralerts", database.getUserAlerts(userName));
                    request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
                } else {
                    request.setAttribute("sign-in-error-message", "Username or password incorrect");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                break;
                
            case "logout":
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/Controller?action=home");
                break;

            case "display-alerts":
                if (request.getSession() != null) {
                    userName = request.getSession().getAttribute("username").toString();
                    password = request.getSession().getAttribute("password").toString();
                    request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
                } else {
                    request.setAttribute("errormessage", "Username or password incorrect");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                break;

            case "register-user":
                userName = request.getParameter("username");
                password = request.getParameter("password");
                firstName = request.getParameter("first-name");
                lastName = request.getParameter("last-name");
                System.out.println(database.getUserByEmailAddress(userName).getUserID());
                if (database.getUserByEmailAddress(lastName).getUserID() == 0) {
                    User user = new User();
                    user = database.addUser(firstName, lastName, userName, password, false);
                    HttpSession newSession = request.getSession(true);
                    newSession.setAttribute("username", userName);
                    ArrayList<Alert> userAlerts = new ArrayList<Alert>();
                    userAlerts = database.getUserAlerts(userName);
                    request.setAttribute("useralerts", userAlerts);
                    request.setAttribute("session", newSession);
                    request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
                } else {
                    request.setAttribute("errormessage", "Email address already exists in database");
                    System.out.println(request.getAttribute("sign-up-error-message").toString());
                    request.getRequestDispatcher("registerUser.jsp").forward(request, response);
                }
                break;
            case "add-alert-page":
                HttpSession thisSession = (HttpSession) request.getSession();
                if (thisSession != null) {
                    request.getRequestDispatcher("addAlert.jsp").forward(request, response);
                } else {
                    request.setAttribute("errormessage", "Must be logged in to add alerts.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                break;

            case "add-alert":
                userName = request.getSession().getAttribute("username").toString();
                int storeID = Integer.parseInt(request.getParameter("store"));
                double alertPrice = Double.parseDouble(request.getParameter("alert-price"));
                String productUrl = request.getParameter("product_url");
                Product product = database.addProduct(storeID, productUrl);
                Alert alert = database.addAlert(product.getProductID(), database.getUserByEmailAddress(userName).getUserID(), alertPrice);
                request.getSession().setAttribute("useralerts", database.getUserAlerts(userName));
                request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
                break;

            case "edit-alert":
                userName = request.getSession().getAttribute("username").toString();
                alertID = Integer.parseInt(request.getParameter("alert-id"));
                alertPrice = Double.parseDouble(request.getParameter("alert-price"));
                database.updateAlertPrice(alertID, alertPrice);
                request.getSession().setAttribute("useralerts", database.getUserAlerts(userName));
                request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
                break;

            case "delete-alert":
                userName = request.getSession().getAttribute("username").toString();
                alertID = Integer.parseInt(request.getParameter("alert-id"));
                database.deleteAlert(alertID);
                request.getSession().setAttribute("useralerts", database.getUserAlerts(userName));
                request.getRequestDispatcher("displayAlerts.jsp").forward(request, response);
                break;

            default:
                request.getSession().invalidate();
                request.getRequestDispatcher("/index.jsp").forward(request, response);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
