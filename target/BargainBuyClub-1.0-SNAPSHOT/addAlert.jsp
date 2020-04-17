<%-- 
    Document   : addAlert
    Created on : Mar 14, 2020, 8:07:11 AM
    Author     : marti
--%>

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
    String errorMessage = "";
    ArrayList<Alert> alerts = new ArrayList<Alert>();
    String userName = "";
    String sessionID = "";

    System.out.println(request.getSession().getId());
    if (request.getSession() != null) {
        userName = request.getSession().getAttribute("username").toString();
        HttpSession thisSession = (HttpSession) request.getSession();
        sessionID = thisSession.getId();
        if (request.getAttribute("useralerts") != null) {
            alerts = (ArrayList<Alert>) request.getAttribute("useralerts");
        }
        if (request.getAttribute("errormessage") != null) {
            errorMessage = request.getAttribute("errormessage").toString();
        } else {
            errorMessage = "";
        }
    } else {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

%>
<div id="add-alert-page" class="container">


    <h4>Enter the required information below to add an alert:</h4>
    <form id="add-alert-form" action="Controller" method="get">

        <div class="form-group">
            <label for="store">Store:</label>
            <select name="store" class="form-control" id="store-input">
                <optgroup label="Select Store">
                
                <option value="1">Best Buy</option>
                </optgroup>
            </select> 
             
        </div>
        <div class="form-group">
            <label for="product_url">URL for Product:</label>
            <input type="text" name ="product_url" placeholder="http://..." pattern="https?://.+" title="Must be a valid url beginning with http:// or https://" id="product-url" class="form-control" required/><br/>            
        </div>
        <div class="form-group">
            <label for="alert-price">Alert Price:</label>
            <input type="text" name = "alert-price" placeholder="0.00" id="alert-price-input" pattern="[0-9]+(\.[0-9][0-9]?)?" title="Must be decimal in form 0.00" class="form-control" required/><br/>
        </div>
    
        <input class="hidden-element" type="text" value="add-alert" name="action"><br/>
        
        <div class="form-group">
        <input class="btn btn-primary btn-large btn-success" type="submit" value="Add Alert"><br/>
        </div>
    </form>

    <span class="error-message"><%= errorMessage%></span>
    <br/>
    Username: <%= userName%>
    <br/>
    Session ID: <%= sessionID%>
</div>
<jsp:include page="/includes/pageBottom.html" />