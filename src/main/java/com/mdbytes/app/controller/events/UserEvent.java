package com.mdbytes.app.controller.events;

import com.mdbytes.app.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class extends Event to handle events related to user and user objects.
 */
public class UserEvent extends Event {

    public UserEvent(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * Method processes a registration request and forwards a new user to the display
     * alerts page unless email is already registered.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     */
    public boolean registerUserBeta(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("errormessage", "Registration disabled during beta development phase.");
            request.setAttribute("sign-up-error-message", "Registration disabled during this development phase.");
            request.setAttribute("page", "home");
            handleException(request, response, "User registration not allowed at this time.  Try again later.");
            //request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There was a problem registering user.  Check db connection and file redirect.");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }
    }

    /**
     * Method processes a registration request and forwards a new user to the display
     * alerts page unless email is already registered.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     */
    public boolean registerUser(HttpServletRequest request, HttpServletResponse response) {

        try {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String firstName = request.getParameter("first-name");
            String lastName = request.getParameter("last-name");

            if (userDao.get(userName).getUserID() == 0) {
                System.out.println(firstName + "," + lastName + "," + userName + "," + password);
                String[] args = {firstName, lastName, userName, password, String.valueOf(false)};
                User newUser = new User();
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmailAddress(userName);
                newUser.setIsAdmin(false);
                newUser.setPassword(password);
                User user = userDao.add(newUser);
                HttpSession newSession = request.getSession(true);
                newSession.setMaxInactiveInterval(300);
                newSession.setAttribute("user", user);
                newSession.setAttribute("admin", user.isIsAdmin());
                newSession.setAttribute("username", user.getEmailAddress());
                newSession.setAttribute("user-id", user.getUserID());
                newSession.setAttribute("useralerts", alertDao.getAll(user.getUserID()));
                if (user.isIsAdmin()) {
                    newSession.setAttribute("admin-view", "true");
                } else {
                    newSession.setAttribute("admin-view", "false");
                }
                request.setAttribute("session", newSession);
                request.setAttribute("page", "alerts");
                newSession.setAttribute("admin", userDao.get(userName).isIsAdmin());
                newSession.setAttribute("username", userName);
                request.setAttribute("session", newSession);
                newSession.setAttribute("useralerts", alertDao.getAll(user.getUserID()));
                newSession.setAttribute("admin-view", "false");
                request.setAttribute("page", "alerts");
                request.getRequestDispatcher("WEB-INF/bbc/displayAlerts.jsp").forward(request, response);
            } else {
                request.setAttribute("errormessage", "Email address already exists in database");
                System.out.println(request.getAttribute("sign-up-error-message").toString());
                request.setAttribute("page", "login");
                request.getRequestDispatcher("WEB-INF/bbc/login.jsp").forward(request, response);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There was a problem registering users at this time.");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }

    }

    /**
     * Method edits user information as requested by validated administrators.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     */
    public boolean editUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String newEmailAddress = request.getParameter("new-email-address");
            String newPassword = request.getParameter("new-password");
            if (!newEmailAddress.equals("")) {
                user.setEmailAddress(newEmailAddress);
                userDao.update(user);
            }
            if (!newPassword.equals("")) {
                user.setPassword(newPassword);
                userDao.update(user);
            }
            request.getSession().setAttribute("users", userDao.getAll());
            request.setAttribute("page", "admin");
            request.getRequestDispatcher("WEB-INF/bbc/displayUsers.jsp").forward(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There was a problem editing a user.");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }
    }

    /**
     * Method deletes users as requested by validated administrators.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     */
    public boolean deleteUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getAttribute("user");
            userDao.delete(user.getUserID());
            request.getSession().setAttribute("users", userDao.getAll());
            request.setAttribute("page", "admin");
            request.getRequestDispatcher("WEB-INF/bbc/displayUsers.jsp").forward(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problem deleting user.");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }
    }

    /**
     * Method processes login username and password request and forwards users to
     * their individual alerts page after user validation.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     */
    public boolean loginUser(HttpServletRequest request, HttpServletResponse response) {

        try {
            if (request.getParameter("username") != null) {
                String userName = request.getParameter("username");
                String password = request.getParameter("password");
                System.out.println(userName + " , " + password);
                if (userDao.validateUser(userName, password)) {
                    User currentUser = userDao.get(userName);
                    int userID = currentUser.getUserID();
                    HttpSession newSession = request.getSession(true);
                    newSession.setMaxInactiveInterval(300);
                    newSession.setAttribute("user", currentUser);
                    newSession.setAttribute("admin", userDao.get(userName).isIsAdmin());
                    newSession.setAttribute("username", userName);
                    newSession.setAttribute("user-id", currentUser.getUserID());
                    newSession.setAttribute("useralerts", alertDao.getAll(userID));
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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problem logging in user.");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }

    }

    /**
     * Method logs out user and returns the user to the home page.
     *
     * @param request  servlet request
     * @param response servlet response
     * @return true or false depending on method success or failure
     */
    public boolean logoutUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().invalidate();
            request.setAttribute("page", "home");
            response.sendRedirect(request.getContextPath());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problem logging out user.");
            handleException(request, response, "Oops!  That was bad!  Check it out!");
            return false;
        }
    }


}