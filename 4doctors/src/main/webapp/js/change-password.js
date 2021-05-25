var change_password_form = document.getElementById("change_password_form");
var passwords = document.querySelectorAll('input[type=password]');
var errors = document.getElementsByClassName("error");

change_password_form.addEventListener("submit", function (event) {

    passwords.forEach(function (element) {
        if(element.value == null){
            element.className = "invalid";
            errors[1].innerHTML = "The password can't be null!";
            event.preventDefault();
            return false;
        }
        var pwd_value = element.value;

        if(pwd_value.length === 0) {
            element.className = "invalid";
            errors[1].innerHTML = "The password can't be empty!";
            event.preventDefault();
            return false;
        } else {
            element.className = "valid";
        }

        element.value = CryptoJS.SHA3(pwd_value).toString();

    });
});

passwords.forEach(function (element) {
        element.addEventListener("focus", function () {
            element.classList.remove("invalid");
            errors[1].innerHTML = "";
        });
});


