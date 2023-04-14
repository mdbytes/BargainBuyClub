/*
 * File:        scripts.js
 * Author:      Martin Dwyer
 * Date:        April 17, 2020
 * Description: This file is part of the BargainBuyClub application.
 * License:     The application is provide herein under the GNU General Public 
 *              License, a free copyleft license for software.  A copy of this 
 *              license has been provided in the root folder of this application.
 */
$(document).ready(function () {
    console.log("Javascript loaded");

    /*
     * checkAdmin() verifies whether or not user is administrator and then sets
     * HTML element #admin-options accordingly.  This setting determines whether or 
     * not HTML elements for admins are displayed. 
     * 
     */
    function checkAdmin() {
        var admin = document.getElementById('is-admin').innerHTML;

        if (admin === "true") {
            $('#admin-options').css("display", "block");
            console.log("is admin");
        } else {
            $('#admin-options').css("display", "none");
            console.log("not admin");
        }
    }

    // checkAdmin() is prompted by the id 'display-alerts-page' 
    if (document.getElementById('display-alerts-page') != null) {
        checkAdmin();
    }

    /*
     * For presentation, the options to edit and delete alerts are not displayed
     * until the user clicks on the respective option.  The following algorithms 
     * toggle the appearance of these supplemental operation forms.  
     * 
     */
    var editDisplayed = false;
    var deleteDisplayed = false;

    $('#display-edit-alert').click(function () {
            if (editDisplayed === false) {
                if (deleteDisplayed == true) {
                    $('#delete-alert-button-div').css("display", "none");
                    deleteDisplayed = false;
                }
                $('#edit-alert-button-div').css("display", "inline-block");
                editDisplayed = true;
            } else {
                $('#edit-alert-button-div').css("display", "none");
                editDisplayed = false;
            }

        }
    );

    $('#display-delete-alert').click(function () {
            if (deleteDisplayed === false) {
                if (editDisplayed == true) {
                    $('#edit-alert-button-div').css("display", "none");
                    editDisplayed = false;
                }
                $('#delete-alert-button-div').css("display", "inline-block");
                deleteDisplayed = true;
            } else {
                $('#delete-alert-button-div').css("display", "none");
                deleteDisplayed = false;
            }

        }
    );

    /*
     * Similar to alert operations forms, user operation forms are not displayed
     * until chosen by the admin user.  The following algorithms control which form
     * appears in accordance with the user's selection. 
     * 
     */
    var deleteDisplayUser = false;
    var editDisplayUser = false;
    var adminDisplayUser = false;

    $('#display-edit-user').click(function () {
            if (editDisplayUser === false) {
                if (deleteDisplayUser == true) {
                    $('#delete-user-button-div').css("display", "none");
                    deleteDisplayUser = false;
                }
                if (adminDisplayUser == true) {
                    $('#admin-user-button-div').css("display", "none");
                    adminDisplayUser = false;
                }
                $('#edit-user-button-div').css("display", "inline-block");
                editDisplayUser = true;
            } else {
                $('#edit-user-button-div').css("display", "none");
                editDisplayUser = false;
            }

        }
    );

    $('#display-delete-user').click(function () {
            if (deleteDisplayUser === false) {
                if (editDisplayUser == true) {
                    $('#edit-user-button-div').css("display", "none");
                    editDisplayUser = false;
                }
                if (adminDisplayUser == true) {
                    $('#admin-user-button-div').css("display", "none");
                    adminDisplayUser = false;
                }
                $('#delete-user-button-div').css("display", "inline-block");
                deleteDisplayUser = true;
            } else {
                $('#delete-user-button-div').css("display", "none");
                deleteDisplayUser = false;
            }

        }
    );

    $('#display-admin-user').click(function () {
            if (adminDisplayUser === false) {
                if (editDisplayUser == true) {
                    $('#edit-user-button-div').css("display", "none");
                    editDisplayUser = false;
                }
                if (deleteDisplayUser == true) {
                    $('#delete-user-button-div').css("display", "none");
                    deleteDisplayUser = false;
                }
                $('#admin-user-button-div').css("display", "inline-block");
                adminDisplayUser = true;
            } else {
                $('#admin-user-button-div').css("display", "none");
                adminDisplayUser = false;
            }

        }
    );

    /*
    * idleLogout() establishes a timer object and conditions for resetting the
    * timer object to zero.  User is notified at 8 minutes, and logged off at
    * 10 minutes.
    *
    */
    function idleLogout() {
        var t;
        window.onload = resetTimer;
        window.onmousemove = resetTimer;
        window.onmousedown = resetTimer;  // catches touchscreen presses as well      
        window.ontouchstart = resetTimer; // catches touchscreen swipes as well 
        window.onclick = resetTimer;      // catches touchpad clicks as well
        window.onkeypress = resetTimer;
        window.addEventListener('scroll', resetTimer, true); // improved; see comments

        function warning() {
            console.log("warning called at " + t);
            $("#timeout").dialog({
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                buttons: {
                    "Stay logged in": function () {
                        $(this).dialog("close");
                        resetTimer();
                    }
                }
            });
        }

        function logoff() {
            $("#sign-out-button").click();
        }

        function resetTimer() {
            clearTimeout(t);
            t = setTimeout(warning, 480000);  // time is in milliseconds
            t = setTimeout(logoff, 600000);
        }
    }

    idleLogout();


})












