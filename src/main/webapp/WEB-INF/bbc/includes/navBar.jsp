<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 4/10/23
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String homeClass = "nav-link";
    String aboutClass = "nav-link";
    String privacyClass = "nav-link";
    String contactClass = "nav-link";
    String loginClass = "nav-link";
    String alertsClass = "nav-link";
    String adminClass = "nav-link dropdown-toggle";
    String url = (String) request.getAttribute("page");
    switch (url) {
        case "home":
            homeClass = "nav-link active";
            break;
        case "about":
            aboutClass = "nav-link active";
            break;
        case "contact":
            contactClass = "nav-link active";
            break;
        case "privacy":
            privacyClass = "nav-link active";
            break;
        case "login":
            loginClass = "nav-link active";
            break;
        case "alerts":
            alertsClass = "nav-link active";
            break;
        case "admin":
            adminClass = "nav-link active dropdown-toggle";
            break;
        default:
            break;
    }
%>
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="main?action=home">BargainBuyClub.com</a>
        <button aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler"
                data-bs-target="#navbarNav" data-bs-toggle="collapse" type="button">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav" style="display:flex; justify-content: end;">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="<%= homeClass %>" href="<%=request.getContextPath()%>/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="<%= aboutClass  %>" href="about">About</a>
                </li>
                <li class="nav-item">
                    <a class="<%= contactClass %>" href="contact">Contact</a>
                </li>
                <li class="nav-item">
                    <a class="<%= privacyClass  %>" href="privacy">Privacy</a>
                </li>
                <% if (request.getSession().getAttribute("username") != null) { %>
                <li class="nav-item">
                    <a class="<%= alertsClass %>" href="main?action=display-alerts">Alerts</a>
                </li>
                <%}%>
                <% if (request.getSession().getAttribute("admin-view") == "true") { %>
                <li class="nav-item dropdown ">
                    <a class="<%= adminClass %>" href="#"
                       role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Admin
                    </a>
                    <ul class="dropdown-menu bg-dark">
                        <li><a class="nav-link active" href="main?action=admin-display-alerts">Alerts</a></li>
                        <li><a class="nav-link active" href="main?action=admin-display-users">Users</a></li>

                    </ul>
                </li>
                <%}%>
                <% if (request.getSession().getAttribute("username") != null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="main?action=logout">Logout</a>
                </li>
                <%} else { %>
                <li class="nav-item">
                    <a class="<%= loginClass %>" href="main?action=login-user">Register</a>
                </li>
                <li class="nav-item">
                    <a class="<%= loginClass %>" href="main?action=login-user">Login</a>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>
