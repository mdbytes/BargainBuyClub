/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    console.log("Javascript loaded");

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

    if (document.getElementById('display-alerts-page') != null) {
        checkAdmin();
    }

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

})












