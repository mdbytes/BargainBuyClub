let message = document.querySelector("#error-message").textContent;
let errorButton = document.querySelector("#launch-error-button");
let closeModalButton = document.querySelector("#close-modal-btn");

if (message.trim() !== "") {
    errorButton.click();
}
closeModalButton.addEventListener('click', () => {

    console.log(location.href);
    console.log(location.hostname);
    console.log(location.host);
    console.log(location.pathname);
    console.log(location.origin);
    console.log(location.host + location.pathname.replace("main", ""));
    window.location.href = location.pathname.replace("main", "");
})