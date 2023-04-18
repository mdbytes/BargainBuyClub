<%-- 
    Document   : addAlert
    Created on : Feb 28, 2020
    Author     : Martin Dwyer
    License    : GNU public license in application root folder
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%-- importing necessary Java resources --%>
<%@page import="java.util.ArrayList" %>
<%@ page import="com.mdbytes.app.model.Alert" %>
<%@ page import="com.mdbytes.app.model.Store" %>
<%@ page import="java.util.List" %>

<%-- including page header including meta data and styles --%>
<jsp:include page="/WEB-INF/bbc/includes/pageTop.jsp"/>

<%
    String errorMessage = "";
    List<Store> stores = new ArrayList<Store>();
    String userName = "";
    String sessionID = "";

    // First check that a session is active.  For active session get username,
    // and user alerts.
    if (request.getSession() != null) {
        userName = request.getSession().getAttribute("username").toString();
        HttpSession thisSession = request.getSession();
        sessionID = thisSession.getId();

        if (request.getSession().getAttribute("stores") != null) {
            stores = (ArrayList<Store>) request.getSession().getAttribute("stores");
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

<!-- main page HTML content begins here -->
<main id="add-alert-page" class="container">

    <!-- Page consists of a single form to collect alert information -->
    <h1>Add Alert</h1>
    <h4>Enter the required information below:</h4>
    <form id="add-alert-form" action="main" method="get">

        <!-- User must select store, which is limited to those in option group -->
        <div class="form-group">
            <label for="store-input">Store:</label>
            <select name="store" class="form-control" id="store-input">
                <optgroup label="Select Store">
                    <%for (Store store : stores) {%>
                    <option value="<%=store.getStoreID()%>"><%=store.getStoreName()%>
                    </option>
                    <%}%>
                </optgroup>
            </select>
        </div>

        <!-- user must provide url for product page where price and name can be retrieved -->
        <div class="form-group">
            <label for="product-url">URL for Product:</label>
            <input type="text" name="product_url" placeholder="http://..." pattern="https?://.+"
                   title="Must be a valid url beginning with http:// or https://" id="product-url" class="form-control"
                   required/><br/>
        </div>

        <!-- user must provide a number in decimal form for alert price -->
        <div class="form-group">
            <label for="alert-price-input">Alert Price:</label>
            <input type="text" name="alert-price" placeholder="0.00" id="alert-price-input"
                   pattern="[0-9]+(\.[0-9][0-9]?)?" title="Must be decimal in form 0.00" class="form-control" required/><br/>
        </div>

        <input class="hidden-element" type="text" value="add-alert" name="action"><br/>
        <div class="form-group">
            <input class="btn btn-primary btn-large btn-success" type="submit" value="Add Alert"><br/>
        </div>
    </form>

    <span class="error-message"><%= errorMessage%></span>

</main>

<%-- include pageBottom for footer and scripts --%>
<jsp:include page="/WEB-INF/bbc/includes/pageBottom.html"/>