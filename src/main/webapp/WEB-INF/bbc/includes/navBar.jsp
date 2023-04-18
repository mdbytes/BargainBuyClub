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
    String loginClass = "nav-link active";
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
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="main?action=home">BargainBuyClub.com</a>&ndash;%&gt;--%>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="width:100%;">
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


            </ul>
            <nav class="navbar navbar-dark bg-dark" id="user-nav">
                <div class="container-fluid">
                    <div id="navbarNav">
                        <ul class="navbar-nav me-auto mb-2 ">
                            <% if (request.getSession().getAttribute("username") != null) { %>
                            <li class="nav-item">
                                <a class="<%= alertsClass %>" href="main?action=display-alerts">Alerts</a>
                            </li>
                            <%}%>
                            <% if (request.getSession().getAttribute("admin-view") == "true") { %>
                            <li class="nav-item dropdown">
                                <a class="<%= adminClass %>" href="#"
                                   role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Admin
                                </a>
                                <ul class="dropdown-menu bg-dark">
                                    <li><a class="nav-link active" href="main?action=admin-display-alerts">Alerts</a>
                                    </li>
                                    <li><a class="nav-link active" href="main?action=admin-display-users">Users</a></li>
                                    <li><a class="nav-link active" href="main?action=update-system-prices">Update
                                        Prices</a>
                                    </li>
                                    <li><a class="nav-link active" href="main?action=send-notifications">Send
                                        Notifications</a>
                                    </li>
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
        </div>
    </div>
</nav>
<%--<nav class="container-fluid navbar navbar-expand-lg fixed-top navbar-dark bg-dark">--%>
<%--    <div class="container">--%>
<%--        <a class="navbar-brand" href="main?action=home">BargainBuyClub.com</a>&ndash;%&gt;--%>
<%--        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"--%>
<%--                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--            <span class="navbar-toggler-icon"></span>--%>
<%--        </button>--%>
<%--        <div class="collapse navbar-collapse" id="navbarSupportedContent">--%>
<%--            <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex justify-content-space-between w-100">--%>
<%--                <ul class="navbar-nav me-auto mb-2 mb-lg-0">--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="<%= homeClass %>" href="<%=request.getContextPath()%>/">Home</a>--%>
<%--
<%--                </ul>--%>
<%--                <ul class="navbar-nav me-auto mb-2 mb-lg-0">--%>


<%--                </ul>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>