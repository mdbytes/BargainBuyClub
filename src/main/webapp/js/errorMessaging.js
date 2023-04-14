let message = document.querySelector("#error-message").textContent;
let errorButton = document.querySelector("#launch-error-button");
let closeModalButton = document.querySelector("#close-modal-btn");

if (message.trim() !== "") {
    errorButton.click();
}
closeModalButton.addEventListener('click', () => {
    message = "";
})