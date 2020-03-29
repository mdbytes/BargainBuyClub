/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function () {
    console.log("Javascript loaded");
    
    var editDisplayed = false;
    
    $('#display-edit-alert').click(function() {
        if(editDisplayed == false) {
            $('#edit-alert-button-div').css("display","inline-block");
            editDisplayed = true;
        } else {
            $('#edit-alert-button-div').css("display","none");
            editDisplayed = false;
        }
    }
    );
    
    var deleteDisplayed = false;
    
    $('#display-delete-alert').click(function() {
        if(deleteDisplayed == false) {
            $('#delete-alert-button-div').css("display","inline-block");
            deleteDisplayed = true;
        } else {
            $('#delete-alert-button-div').css("display","none");
            deleteDisplayed = false;
        }
        
        
        
    }
    );


}












