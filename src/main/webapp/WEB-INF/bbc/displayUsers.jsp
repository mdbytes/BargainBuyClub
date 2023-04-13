<%-- 
    Document   : displayUsers
    Created on : Feb 28, 2020
    Author     : Martin Dwyer
    License    : GNU public license in application root folder
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%-- importing necessary Java resources --%>
<%@page import="java.util.Collections,
                java.util.ArrayList,
                java.text.NumberFormat" %>
<%@ page import="com.mdbytes.app.model.User" %>

<%-- including page header including meta data and styles --%>
<jsp:include page="/WEB-INF/bbc/includes/pageTop.jsp"/>
<%
    ArrayList<User> users = new ArrayList();
    String userName = "";
    String sessionID = "";
    String isAdmin = "";

    NumberFormat cf = NumberFormat.getCurrencyInstance();

    // Checking to see if an active session is in process
    // If not, user is directed back to site home page 
    if (request.getSession() != null) {

        // Retrieving current session data
        userName = request.getSession().getAttribute("username").toString();
        isAdmin = request.getSession().getAttribute("admin").toString();
        sessionID = request.getSession().getId();
        users = (ArrayList<User>) request.getSession().getAttribute("users");

    } else {

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>

<!-- main page HTML content begins here -->
<main id="display-users-page" class="container">
    <h1>Displaying All System Users
        <h1>
            <h6>Admin: <%= userName%>
            </h6>
            <br>

            <!-- using session data (if exists) to fill users table -->
            <table class="table" id="display-users-table">
                <tr>
                    <th>User ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email Address</th>
                    <th>Admin?</th>
                    <th></th>
                </tr>
                <%
                    if (users != null) {
                        for (User user : users) {
                            out.print("<tr>");
                            out.print("<td>");
                            out.print(user.getUserID());
                            out.print("</td>");
                            out.print("<td>");
                            out.print(user.getFirstName());
                            out.print("</td>");
                            out.print("<td>");
                            out.print(user.getLastName());
                            out.print("</td>");
                            out.print("<td>");
                            out.print(user.getEmailAddress());
                            out.print("</td>");
                            out.print("<td>");
                            if (user.isIsAdmin()) {
                                out.print("Yes");
                            } else {
                                out.print("No");
                            }
                            out.print("</td>");
                            out.print("</tr>");
                        }
                    } else {
                        out.print("<tr><td>No alerts at this time</td></tr>");
                    }
                %>
            </table>
            <br>

            <!-- actions for admin to take triggered by the following buttons -->
            <!-- button for editing user -->
            <div class="user-button">
                <form class="button-form" id="edit-user">
                    <input id="display-edit-user" type="button" value="  Edit User  "
                           class="btn btn-primary btn-large btn-success user-button"></form>
            </div>

            <!-- button for deleting user -->
            <div class="user-button">
                <form class="button-form" id="delete-user">
                    <input id="display-delete-user" type="button" value="Delete User"
                           class="btn btn-primary btn-large btn-success user-button">
                </form>
            </div>

            <!-- button for making user an admin -->
            <div class="user-button">
                <form class="button-form" id="admin-user">
                    <input id="display-admin-user" type="button" value="Make Admin"
                           class="btn btn-primary btn-large btn-success user-button">
                </form>
            </div>

            <!-- button to sign out admin -->
            <div class="user-button">
                <form class="button-form" id="sign-out" action="main" method="get">
                    <input class="hidden-element " type="text" value="logout" name="action">
                    <input id="sign-out-button" type="submit" value="   Sign Out   "
                           class="btn btn-primary btn-large btn-success user-button">
                </form>
            </div>
            <div class="clear-float"></div>

            <!-- form for editing user -->
            <div id="edit-user-button-div">
                <form class="button-form" id="edit-user-form" action="main" method="get">
                    <input class="hidden-element " type="text" value="edit-user" name="action">
                    <label for="user-id-input">User ID for editing:</label>
                    <select name="user-id" id="user-id-input" class="form-control">
                        <%
                            if (users != null) {
                                for (User user : users) {
                                    out.print("<option value='");
                                    out.print(user.getUserID());
                                    out.print("'>");
                                    out.print(user.getUserID());
                                    out.print("</option>");
                                }
                            } else {
                                out.print("<tr><td>No users at this time</td></tr>");
                            }
                        %>
                    </select><br/>
                    <label for="email-address-input">New email address:</label>
                    <input type="email" name="new-email-address" placeholder="  New email address"
                           id="email-address-input"><br/>

                    <label for="sign-up-password">New password:</label>
                    <input type="password" name="new-password" class="form-control" id="sign-up-password"
                           placeholder="New Password"
                           pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$"
                           title="Must be 6 characters long, contain one upper case letter, one lower case letter, and one numeric digit"><br/>
                    <p style="clear:both; font-size: 75%;max-width: 550px;margin-top:15px;">Password must be at least 6
                        characters and must include at least one upper case letter, one lower case letter, and one
                        numeric digit.</p>

                    <input id="edit-user-button" type="submit" value="   Submit   "
                           class="btn btn-primary btn-large btn-success">
                </form>
            </div>

            <!-- form for deleting user -->
            <div id="delete-user-button-div">
                <form class="button-form" id="delete-user-form" action="main" method="get">
                    <input class="hidden-element " type="text" value="delete-user" name="action">
                    <label for="user-id">User ID to be deleted:</label>
                    <select name="user-id" id="user-id" class="form-control">
                        <%
                            if (users != null) {
                                for (User user : users) {
                                    out.print("<option value='");
                                    out.print(user.getUserID());
                                    out.print("'>");
                                    out.print(user.getUserID());
                                    out.print("</option>");
                                }
                            } else {
                                out.print("<tr><td>No users at this time</td></tr>");
                            }
                        %>
                    </select><br/>
                    <input id="delete-user-button" type="submit" value="   Submit   "
                           class="btn btn-primary btn-large btn-success">
                </form>
            </div>

            <!-- form for making user admin -->
            <div id="admin-user-button-div">
                <form class="button-form" id="admin-user-form" action="main" method="get">
                    <input class="hidden-element " type="text" value="admin-user" name="action">
                    <label for="user-id">User ID to be System Administrator:</label>
                    <select name="user-id" class="form-control">
                        <%
                            if (users != null) {
                                for (User user : users) {
                                    out.print("<option value='");
                                    out.print(user.getUserID());
                                    out.print("'>");
                                    out.print(user.getUserID());
                                    out.print("</option>");
                                }
                            } else {
                                out.print("<tr><td>No users at this time</td></tr>");
                            }
                        %>
                    </select><br/>
                    <input id="admin-user-button" type="submit" value="   Submit   "
                           class="btn btn-primary btn-large btn-success">
                </form>
            </div>
            <span class="error-message" style="clear:both;"></span>

</main>

<%-- include pageBottom for footer and scripts --%>
<jsp:include page="/WEB-INF/bbc/includes/pageBottom.html"/>
