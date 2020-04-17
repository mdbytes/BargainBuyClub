/*
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 */
package com.bdowebtech.bargainbuyclub;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marti
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    
    public EventHandler gunther = new EventHandler();

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
            throws ServletException, IOException, SQLException {

        switch (request.getParameter("action")) {
            case "home":
                gunther.goHome(request,response);               
                break;

            case "login-user":
                gunther.loginUser(request, response);              
                break;
                
            case "logout":
                gunther.logoutUser(request,response);
                break;

            case "display-alerts":
                gunther.displayAlerts(request,response);
                break;
                
            case "admin-display-alerts":
                gunther.adminDisplayAlerts(request,response);                
                break;
                
            case "register-user":
                gunther.registerUser(request,response);
                
                break;
            case "add-alert-page":
                gunther.displayNewAlertPage(request,response);                
                break;

            case "add-alert":
                gunther.addAlert(request,response);
                break;

            case "edit-alert":
                gunther.editAlert(request,response);
                break;

            case "delete-alert":
                gunther.deleteAlert(request,response);
                break;
            
            case "admin-display-users":
                gunther.displayUsersForAdmin(request,response);
                break;
            
            case "edit-user":
                gunther.editUser(request,response);
                break;
                
                
            case "delete-user":
                gunther.deleteUser(request,response);
                break;

            case "admin-user":
                gunther.makeUserAdmin(request,response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
