var cf = document.getElementById("user");
var password = document.getElementById("key");
var radio_patient = document.getElementById("patient");
var radio_doctor = document.getElementById("doctor");
var login_form = document.getElementById("login_form");
var errors = document.getElementsByClassName("error");

const cf_regex = /[A-Za-z]{6}[0-9]{2}[A-Za-z][0-9]{2}[A-Za-z][0-9]{3}[A-Za-z]/;

login_form.addEventListener("submit", function (event) {

    if(cf.value == null){
        cf.className = "invalid";
        errors[0].innerHTML = "The fiscal code can't be null!";
        event.preventDefault();
        return false;
    }

    if(password.value == null){
        password.className = "invalid";
        errors[1].innerHTML = "The password can't be null!";
        event.preventDefault();
        return false;
    }

    var cf_value = cf.value.trim().toUpperCase();
    var pwd_value = password.value;

    if(cf_value.length === 0) {
        cf.className = "invalid";
        errors[0].innerHTML = "The fiscal code can't be empty!";
        event.preventDefault();
        return false;
    } else if(!cf_regex.test(cf_value)) {
        cf.className = "invalid";
        errors[0].innerHTML = "The format of the fiscal code is wrong!";
        event.preventDefault();
        return false;
    } else {
        cf.className = "valid";
    }

    if(pwd_value.length === 0) {
        password.className = "invalid";
        errors[1].innerHTML = "The password can't be empty!";
        event.preventDefault();
        return false;
    } else {
        password.className = "valid";
    }

    if(!radio_patient.checked && !radio_doctor.checked){
        radio_patient.className = "invalid";
        radio_doctor.className = "invalid";
        errors[2].innerHTML = "A role must be selected!";
        event.preventDefault();
        return false;
    } else {
        radio_patient.className = "valid";
        radio_doctor.className = "valid";
    }

    cf.value = cf_value;
    password.value = CryptoJS.SHA3(pwd_value).toString();
});

cf.addEventListener("focus", function () {
    cf.classList.remove("invalid");
    errors[0].innerHTML = "";
});

password.addEventListener("focus", function () {
    password.classList.remove("invalid");
    errors[1].innerHTML = "";
});

radio_patient.addEventListener("focus", function () {
    radio_patient.classList.remove("invalid");
    errors[2].innerHTML = "";
});

radio_doctor.addEventListener("focus", function () {
    radio_doctor.classList.remove("invalid");
    errors[2].innerHTML = "";
});
