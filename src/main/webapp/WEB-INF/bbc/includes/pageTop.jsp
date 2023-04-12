<!DOCTYPE html>
<html lang="en">
<head>

    <!-- setting metadata within HTML document -->
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta content="BargainBuyClub application to monitor prices" name="description">
    <meta content="monitor,products,prices,email,alerts,notification" name="keywords">
    <meta content="Martin Dwyer" name="author">

    <!-- site title, icon and styles (including bootstrap and jQuery style sources -->
    <title>BargainBuyClub.com</title>
    <link href="images/shopping-cart.png" rel="icon" type="image/png">
    <link href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" rel="stylesheet"/>
    <link crossorigin="anonymous" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet" type="text/css">

</head>

<body>

<!-- Navigation -->
<jsp:include page="navBar.jsp"/>

<div id="timeout" title="Warning">
    <p>You will be logged off after 10 minutes of inactivity. Just click "stay logged in" below to keep working.</p>
</div>
