var registration_form = $("#registration_form");
var cf = $("#cf");
var _name = $("#name");
var surname = $("#surname");
var email = $("#email");
var password = $("#password");
var retype_password = $("#retype_password");
var birthday = $("#birthday");
var birthplace = $("#birthplace");
var address = $("#address");
var radio_male = $("#male");
var radio_female = $("#female");
var errors = $("div.error");
var message = $("#message");
var password_strength = $("#password_strength");


const cf_regex = /[A-Za-z]{6}[0-9]{2}[A-Za-z][0-9]{2}[A-Za-z][0-9]{3}[A-Za-z]/;
const email_regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const strong_password = /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})/;
const medium_password = /(?=.*[a-z])(?=.*[0-9])(?=.{6,})/;

registration_form.submit(function (event) {

    message.html("");
    errors.html("");

    $("input").removeClass("invalid");

    if(cf.val() == null){
        cf.addClass("invalid");
        errors.eq(0).html("The fiscal code can't be null!");
        event.preventDefault();
        return false;
    }

    if(_name.val() == null){
        _name.addClass("invalid");
        errors.eq(1).html("The name can't be null!");
        event.preventDefault();
        return false;
    }

    if(surname.val() == null){
        surname.addClass("invalid");
        errors.eq(2).html("The surname can't be null!");
        event.preventDefault();
        return false;
    }

    if(email.val() == null){
        _name.addClass("invalid");
        errors.eq(3).html("The email can't be null!");
        event.preventDefault();
        return false;
    }

    if(birthday.val() == null){
        birthday.addClass("invalid");
        errors.eq(4).html("The birthday can't be null!");
        event.preventDefault();
        return false;
    }

    if(birthplace.val() == null){
        birthplace.addClass("invalid");
        errors.eq(5).html("The birthplace can't be null!");
        event.preventDefault();
        return false;
    }

    if(address.val() == null){
        address.addClass("invalid");
        errors.eq(6).html("The address can't be null!");
        event.preventDefault();
        return false;
    }

    if(password.val() == null){
        password.addClass("invalid");
        errors.eq(8).html("The password can't be null!");
        event.preventDefault();
        return false;
    }

    if(retype_password.val() == null){
        retype_password.addClass("invalid");
        errors.eq(9).html("The retyped password can't be null!");
        event.preventDefault();
        return false;
    }

    var cf_value = cf.val().trim().toUpperCase();
    var name_value = _name.val().trim();
    var surname_value = surname.val().trim();
    var email_value = email.val().trim();
    var pwd_value = password.val();
    var retype_pwd_value = retype_password.val();
    var birthday_value = birthday.val().trim();
    var birthplace_value = birthplace.val().trim();
    var address_value = address.val().trim();

    if(cf_value.length === 0) {
        cf.addClass("invalid");
        errors.eq(0).html("The fiscal code can't be empty!");
        event.preventDefault();
        return false;
    } else if(!cf_regex.test(cf_value)) {
        cf.addClass("invalid");
        errors.eq(0).html("The format of the fiscal code is wrong!");
        event.preventDefault();
        return false;
    } else {
        cf.addClass("valid");
    }

    if(name_value.length === 0) {
        _name.addClass("invalid");
        errors.eq(1).html("The name can't be empty!");
        event.preventDefault();
        return false;
    } else {
        _name.addClass("valid");
    }

    if(surname_value.length === 0) {
        surname.addClass("invalid");
        errors.eq(2).html("The surname can't be empty!");
        event.preventDefault();
        return false;
    } else {
        surname.addClass("valid");
    }

    if(email_value.length === 0) {
        email.addClass("invalid");
        errors.eq(3).html("The email can't be empty!");
        event.preventDefault();
        return false;
    } else if(!email_regex.test(email_value)) {
        email.addClass("invalid");
        errors.eq(3).html("The value inserted is not an email!");
        event.preventDefault();
        return false;
    } else {
        email.addClass("valid");
    }

    if(birthday_value.length === 0) {
        birthday.addClass("invalid");
        errors.eq(4).html("The birthday can't be empty!");
        event.preventDefault();
        return false;
    } else if(isDateInvalid(birthday_value)) {
        birthday.addClass("invalid");
        errors.eq(4).html("The date format is not valid!");
        event.preventDefault();
        return false;
    } else if(isFutureDate(birthday_value)) {
        birthday.addClass("invalid");
        errors.eq(4).html("The birthday can't be after the current date!");
        event.preventDefault();
        return false;
    } else {
        birthday.addClass("valid");
    }

    if(birthplace_value.length === 0) {
        birthplace.addClass("invalid");
        errors.eq(5).html("The birthplace can't be empty!");
        event.preventDefault();
        return false;
    } else {
        birthplace.addClass("valid");
    }

    if(address_value.length === 0) {
        address.addClass("invalid");
        errors.eq(6).html("The address can't be empty!");
        event.preventDefault();
        return false;
    } else {
        address.addClass("valid");
    }

    if(!radio_male.is(":checked") && !radio_female.is(":checked")){
        radio_male.className = "invalid";
        radio_female.className = "invalid";
        errors.eq(7).html("A gender must be selected!");
        event.preventDefault();
        return false;
    } else {
        radio_male.className = "valid";
        radio_female.className = "valid";
    }

    if(pwd_value.length === 0) {
        password.addClass("invalid");
        errors.eq(8).html("The password can't be empty");
        event.preventDefault();
        return false;
    } else if(!medium_password.test(pwd_value)) {
        password.addClass("invalid");
        errors.eq(8).html("The password should contain at least one lower case character, one number and be long at least 6 characters");
        event.preventDefault();
        return false;
    } else if(retype_pwd_value.length === 0){
        retype_password.addClass("invalid");
        errors.eq(9).html("The retyped password can't be empty");
        event.preventDefault();
        return false;
    } else if(pwd_value !== retype_pwd_value) {
        password.addClass("invalid");
        retype_password.addClass("invalid");
        errors.eq(9).html("The passwords are not corresponding");
        event.preventDefault();
        return false;
    } else {
        password.addClass("valid");
        retype_password.addClass("valid");
    }

    cf.val(cf_value);
    _name.val(name_value);
    surname.val(surname_value);
    email.val(email_value);
    password.val(CryptoJS.SHA3(pwd_value).toString());
    retype_password.val(CryptoJS.SHA3(retype_pwd_value).toString());
    birthday.val(birthday_value);
    birthplace.val(birthplace_value);
    address.val(address_value);
});

password.on("input", function (event) {

    if($(this).val().length === 0) {
        password_strength.css("display", "none");
    } else {
        strengthChecker($(this).val());
        password_strength.css("display", "block");
    }
});

function isDateInvalid(date_string) {

    var date = Date.parse(date_string);
    return isNaN(date);
}

function isFutureDate(date_string) {
    var date = new Date(date_string);
    var currentdate = new Date();

    return date > currentdate;
}

function strengthChecker(password){

    if(strong_password.test(password)) {
        errors.eq(8).html("");
        password_strength.css("background-color", "green");
        password_strength.html("Strong");
    } else if(medium_password.test(password)){
        errors.eq(8).html("");
        password_strength.css("background-color", "blue");
        password_strength.html("Medium");
    } else{
        password_strength.css("background-color", "red");
        password_strength.html("Weak");
    }
}