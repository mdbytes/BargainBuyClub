<%-- 
    Document   : login
    Created on : Feb 28, 2020, 3:12:23 PM
    Author     : marti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/includes/pageTop.html" />
<%
    String errorMessage;
    if(request.getAttribute("errormessage") != null) {
       errorMessage = request.getAttribute("errormessage").toString();
    } else {
       errorMessage = "";
    }

%>

<body>
    <div class="container">
        <h1>BargainBuyClub.com</h1>
        <h3>Login</h3>

        <form action="Controller" method="get">
            <label for="username">Username: </label>
            <input type="text" name="username" placeholder="Enter username"><br/>
            <label for="password">Password: </label>
            <input type="password" name="password"><br/>

            <input class="hidden-element" type="text" value="display-alerts" name="action">

            <input class="btn btn-primary btn-large btn-success" type="submit" value="Login">
        </form>
        <span class="error-message"><%= errorMessage %></span>

    </div>
</body>
<jsp:include page="/includes/pageTop.html" />
