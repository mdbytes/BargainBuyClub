<%-- 
    Document   : registerUser
    Created on : Mar 13, 2020, 3:15:46 PM
    Author     : marti
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.bdowebtech.bargainbuyclub.model.Data.HomePageData" %>
<%@page import="com.bdowebtech.bargainbuyclub.model.Alert" %>

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
        
        
        <h4>Enter the required information below to register:</h4>
        <form action="Controller" method="get">
            <label for="username">User Name:
                <input type="text" name = "username" placeholder="Enter email address" required/><br/>
            <label for="password">Password:
                <input type="text" name = "password" placeholder="Password" required/><br/>
            <label for="first-name">First Name:
                <input type="text" name = "first-name" placeholder="First name" required/><br/>
            <label for="last-name">Last Name:
                <input type="text" name = "last-name" placeholder="Last name" required/><br/>
            
            <input class="hidden-element" type="text" value="register-user" name="action"><br/>
            
            <input class="btn btn-primary btn-large btn-success" type="submit" value="Sign Up"><br/>
            
        </form>
    
        <span class="error-message"><%= errorMessage %></span>
        
    </div>
</body>
<jsp:include page="/includes/pageBottom.html" />

