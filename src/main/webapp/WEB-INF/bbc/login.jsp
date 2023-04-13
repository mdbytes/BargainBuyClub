<%-- 
    Document   : login
    Created on : Feb 28, 2020
    Author     : Martin Dwyer
    License    : GNU public license in application root folder
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%-- including page header including meta data and styles --%>
<jsp:include page="/WEB-INF/bbc/includes/pageTop.jsp"/>
<%
    String signInErrorMessage = "";
    String signUpErrorMessage = "";

    // If request is sent back with error messages, they are retrieved here
    // and appear below.  On a user's first trip here, the attribute does not exist.  
    if (request.getAttribute("sign-in-error-message") != null) {
        signInErrorMessage = request.getAttribute("sign-in-error-message").toString();
    }
    if (request.getAttribute("sign-up-error-message") != null) {
        signUpErrorMessage = request.getAttribute("sign-up-error-message").toString();
    }
    out.println(signUpErrorMessage);
%>

<!-- main page HTML content begins here -->
<main id="login-page" class="container">

    <!-- Format of page is in two rows -->
    <!-- First row is for existing users to sign in -->
    <div class="row">
        <div id="shopper-home">
            <img class="center" src="images/megaphone.png">
            <hr>
        </div>

        <!-- HTML validations are used extensively in both sign in and sign up processes -->
        <form method="post" action="main" id="signin" style="float:left;">
            <div class="form-group">
                <h3>Sign In</h3>
            </div>

            <!-- username must be a valid email address -->
            <div class="form-group sign-in-input" style="float:left;">
                <label for="sign-in-username"><h5>Username:</h5></label>
                <input type="email" class="form-control" id="sign-in-username" placeholder="Email Address"
                       name="username" required>
            </div>

            <!-- password must be 6 characters long, one uppercase letter, one lowercase letter, and one number -->
            <div class="form-group sign-in-input" style="">
                <label for="sign-in-password"><h5>Password:</h5></label>
                <input type="password" class="form-control" id="sign-in-password" placeholder="Password"
                       name="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$"
                       title="Must be 6 characters long, contain one upper case letter, one lower case letter, and one numeric digit"
                       required>
            </div>

            <input type="text" name="action" value="login-user" class="hidden-element">
            <button type="submit" class="btn btn-primary btn-success" style="margin-bottom:25px;">Sign In</button>
            <span class="error-message"><%= signInErrorMessage%></span>
        </form>
    </div>
    <hr>

    <!-- The second row allows new users to sign up -->
    <div class="row">
        <form method="get" action="main" id="signup">
            <div class="form-group">
                <h3>Or Sign Up</h3>
            </div>
            <div class="form-row">
                <div class="form-group sign-up-input">
                    <label for="first-name"><h5>First name:</h5></label>
                    <input type="text" class="form-control" placeholder="First name" name="first-name" id="first-name"
                           required>
                </div>
                <div class="form-group sign-up-input">
                    <label for="last-name"><h5>Last name:</h5></label>
                    <input type="text" class="form-control" placeholder="Last name" name="last-name" id="last-name"
                           required>
                </div>
            </div>
            <div class="form-row">

                <!-- HTML validations used for email address and password as above -->
                <div class="form-group sign-up-input">
                    <label for="username"><h5>Email address (username):</h5></label>
                    <input type="email" class="form-control" id="sign-up-username" placeholder="Email (Username)"
                           name="username" id="username" required>
                </div>
                <div class="form-group sign-up-input">
                    <label for="sign-up-password"><h5>Password:</h5></label>
                    <input type="password" name="password" class="form-control" id="sign-up-password"
                           placeholder="Password"
                           pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$"
                           title="Must be 6 characters long, contain one upper case letter, one lower case letter, and one numeric digit"
                           required>
                </div>
            </div>
            <input type="text" name="action" value="register-user" class="hidden-element">
            <button type="submit" class="btn btn-primary btn-success">Sign Up</button>
            <span class="error-message"><%= signUpErrorMessage%></span>
            <p style="clear:both; font-size: 75%;max-width: 550px;margin-top:15px;">Password must be at least 6
                characters and must include at least one upper case letter, one lower case letter, and one numeric
                digit.</p>
        </form>
    </div>
</main>

<%-- include pageBottom for footer and scripts --%>
<jsp:include page="/WEB-INF/bbc/includes/pageBottom.html"/>
