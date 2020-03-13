<%-- 
    Document   : login
    Created on : Feb 28, 2020, 3:12:23 PM
    Author     : marti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/includes/pageTop.html" />
<%
    String errorMessage;
    if(request.getParameter("errorMessage") != null) {
       errorMessage = request.getParameter("errorMessage").toString();
    } else {
       errorMessage = "";
    }

%>

    <body>
        <h1>BargainBuyClub.com</h1>
        <h3>Login</h3>
        
        <form action="Controller" method="get">
            <label for="username">Username: </label>
            <input type="text" name="username" placeholder="Enter username"><br/>
            <label for="password">Password: </label>
            <input type="password" name="password"><br/>
            
            <input type="text" value="display-alerts" name="action">
            
            <input type="submit" value="Login">
        </form>
    <span><%= errorMessage %></span>
        
        
    </body>
<jsp:include page="/includes/pageTop.html" />
