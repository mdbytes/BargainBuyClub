window.onload = function () {
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



}


