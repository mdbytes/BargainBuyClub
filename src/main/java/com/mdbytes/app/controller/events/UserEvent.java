package com.mdbytes.app.controller.events;

import com.mdbytes.app.controller.Event;
import com.mdbytes.app.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class extends Event to handle events related to user and user objects.
 */
public class UserEvent extends Event {

    public UserEvent() {
        super();
    }

    /**
     * Method processes a registration request and forwards a new user to the display
     * alerts page unless email is already registered.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if occurs with handling request or response.
     * @throws IOException      if occurs with forwarding user to view.
     */
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userName = request.getParameter("username");
        password = request.getParameter("password");
        firstName = request.getParameter("first-name");
        lastName = request.getParameter("last-name");

        if (userDao.getUserByEmailAddress(userName).getUserID() == 0) {
            User user = new User();
            System.out.println(firstName + "," + lastName + "," + userName + "," + password);
            String[] args = {firstName, lastName, userName, password, String.valueOf(false)};
            user = userDao.add(firstName, lastName, userName, password, String.valueOf(false));
            HttpSession newSession = request.getSession(true);
            newSession.setMaxInactiveInterval(300);
            newSession.setAttribute("admin", userDao.getUserByEmailAddress(userName).isIsAdmin());
            newSession.setAttribute("username", userName);
            request.setAttribute("session", newSession);
            newSession.setAttribute("useralerts", userDao.getUserAlerts(userID));
            newSession.setAttribute("admin-view", "false");
            request.setAttribute("page", "alerts");
            request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
        } else {
            request.setAttribute("errormessage", "Email address already exists in database");
            System.out.println(request.getAttribute("sign-up-error-message").toString());
            request.setAttribute("page", "login");
            request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
        }
    }

    /**
     * Method edits user information as requested by validated administrators.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     */
    public void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userID = Integer.parseInt(request.getParameter("user-id"));
        String newEmailAddress = request.getParameter("new-email-address");
        String newPassword = request.getParameter("new-password");
        if (!newEmailAddress.equals("")) {
            userDao.updateUserEmailAddress(userID, newEmailAddress);
        }
        if (!newPassword.equals("")) {
            userDao.updateUserPassword(userID, newPassword);
        }
        request.getSession().setAttribute("users", userDao.getAll());
        request.setAttribute("page", "admin");
        request.getRequestDispatcher("WEB-INF/bbc/displayUsers.jsp").forward(request, response);
    }

    /**
     * Method deletes users as requested by validated administrators.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     */
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userID = Integer.parseInt(request.getParameter("user-id"));
        userDao.delete(userID);
        request.getSession().setAttribute("users", userDao.getAll());
        request.setAttribute("page", "admin");
        request.getRequestDispatcher("WEB-INF/bbc/displayUsers.jsp").forward(request, response);
    }

    /**
     * Method gives users administrator privileges as requested by validated administrators.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     */
    public void makeUserAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userID = Integer.parseInt(request.getParameter("user-id"));
        userDao.makeUserAdmin(userID);
        request.getSession().setAttribute("users", userDao.getAll());
        request.setAttribute("page", "admin");
        request.getRequestDispatcher("WEB-INF/bbc/displayUsers.jsp").forward(request, response);
    }

    /**
     * Method processes login username and password request and forwards users to
     * their individual alerts page after user validation.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     */
    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("username") != null) {
            userName = request.getParameter("username");
            this.password = request.getParameter("password");
            System.out.println(userName + " , " + password);
            if (this.userDao.validateUser(userName, password)) {
                User currentUser = userDao.getUserByEmailAddress(userName);
                this.userID = currentUser.getUserID();
                HttpSession newSession = request.getSession(true);
                newSession.setMaxInactiveInterval(300);
                newSession.setAttribute("user", currentUser);
                newSession.setAttribute("admin", userDao.getUserByEmailAddress(userName).isIsAdmin());
                newSession.setAttribute("username", userName);
                newSession.setAttribute("user-id", currentUser.getUserID());
                newSession.setAttribute("useralerts", userDao.getUserAlerts(userID));
                if (currentUser.isIsAdmin()) {
                    newSession.setAttribute("admin-view", "true");
                } else {
                    newSession.setAttribute("admin-view", "false");
                }
                request.setAttribute("session", newSession);
                request.setAttribute("page", "alerts");
                request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
            } else {
                request.setAttribute("sign-in-error-message", "Username or password incorrect");
                request.setAttribute("page", "login");
                request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("page", "login");
            request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
        }
    }

    /**
     * Method logs out user and returns the user to the home page.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws IOException if I/O error occurs
     */
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        request.setAttribute("page", "home");
        response.sendRedirect(request.getContextPath() + "/main?action=home");
    }

    /**
     * Method prepares and presents all users when selected by validated administrators.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if servlet specific exception occurs
     * @throws IOException      if I/O error occurs
     */
    public void displayUsersForAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("users", userDao.getAll());
        request.setAttribute("page", "admin");
        request.getRequestDispatcher("WEB-INF/bbc/displayUsers.jsp").forward(request, response);
    }


}
