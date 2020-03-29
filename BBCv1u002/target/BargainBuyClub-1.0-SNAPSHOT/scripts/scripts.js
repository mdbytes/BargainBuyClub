/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function () {
    console.log("Javascript loaded");

    $("#edit-alert-button").onclick = function () {

        var alertSelectors = [];
        alertSelectors = getElementsByClass("select-alert");

        for (var i = 0; i < alertSelectors.length; i++) {
            console.log(alertSlectors[i]);
        }

    }

}












