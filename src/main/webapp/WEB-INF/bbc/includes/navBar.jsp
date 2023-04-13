<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 4/10/23
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <a class="nav-link active" href="about">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="contact">Contact</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="privacy">Privacy</a>
                </li>
                <% if (request.getSession().getAttribute("username") != null) { %>
                <li class="nav-item">
                    <a class="nav-link active" href="main?action=display-alerts">Alerts</a>
                </li>
                <%}%>
                <% if (request.getSession().getAttribute("admin-view") == "true") { %>
                <li class="nav-item dropdown ">
                    <a class="nav-link active dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
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
                    <a class="nav-link active" href="main?action=logout">Logout</a>
                </li>
                <%} else { %>
                <li class="nav-item">
                    <a class="nav-link active" href="main?action=login-user">Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="main?action=login-user">Login</a>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>
