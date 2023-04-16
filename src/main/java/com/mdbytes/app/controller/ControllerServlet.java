package com.mdbytes.app.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet handles all flow for the application requiring any business logic for users.
 * Discerns need through request parameters and forwards to the appropriate event handlers.
 */
@WebServlet(name = "BargainControllerServlet", value = "/main")
public class ControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.  Directs application to event handling class based on request parameter 'action'.
     * If no request parameter 'action' is provided, the user is forwarded back to the application
     * home page.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        boolean success = false;

        HomeEvent homeEvent = new HomeEvent(request, response);
        UserEvent userEvent = new UserEvent(request, response);
        AlertEvent alertEvent = new AlertEvent(request, response);
        AdminEvent adminEvent = new AdminEvent(request, response);

        switch (request.getParameter("action")) {
            case "home":
                homeEvent.goHome(request, response);
                break;

            case "login-user":
                userEvent.loginUser(request, response);
                break;

            case "logout":
                userEvent.logoutUser(request, response);
                break;

            case "display-alerts":
                alertEvent.displayAlerts(request, response);
                break;

            case "admin-display-alerts":
                adminEvent.adminDisplayAlerts(request, response);
                break;

            case "register-user":
                userEvent.registerUserBeta(request, response);
                break;

            case "add-alert-page":
                alertEvent.displayNewAlertPage(request, response);
                break;

            case "add-alert":
                alertEvent.addAlert(request, response);
                break;

            case "edit-alert":
                alertEvent.editAlert(request, response);
                break;

            case "delete-alert":
                alertEvent.deleteAlert(request, response);
                break;

            case "admin-display-users":
                adminEvent.displayUsersForAdmin(request, response);
                break;

            case "edit-user":
                userEvent.editUser(request, response);
                break;

            case "delete-user":
                userEvent.deleteUser(request, response);
                break;

            case "admin-user":
                adminEvent.makeUserAdmin(request, response);
                break;

            case "update-system-prices":
                adminEvent.updateSystemPrices(request, response);
                break;

            case "send-notifications":
                adminEvent.sendNotifications(request, response);
                break;

            default:
                request.getSession().invalidate();
                success = homeEvent.goHome(request, response);
                if (!success) {
                    response.sendRedirect(request.getContextPath());
                }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method. Forwards application to the processRequest method
     * ot this class.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method. Forwards application to the processRequest method
     * of this class.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
