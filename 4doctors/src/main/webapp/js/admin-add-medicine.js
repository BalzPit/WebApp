var add_medicine_form = $("#add_medicine_form");
var code = $("#m_code");
var _name = $("#name");
var producer = $("#prod");
var description = $("#description");
var radio_etici = $("#ETICI");
var radio_otc = $("#OTC");
var radio_sop = $("#SOP");
var errors = $("div.error");
var add_medicine_message = $("#add_medicine_message");

const contextPath = "http://localhost:8080/4Doctors-1.00";


add_medicine_form.submit(function (event) {

    if(code.val() == null){
        code.addClass("invalid");
        errors.eq(0).html("The medicine code can't be null!");
        event.preventDefault();
        return false;
    }

    if(_name.val() == null){
        _name.addClass("invalid");
        errors.eq(1).html("The name can't be null!");
        event.preventDefault();
        return false;
    }

    if(description.val() == null){
            code.addClass("invalid");
            errors.eq(0).html("The description can't be null!");
            event.preventDefault();
            return false;
        }

    var code_value = code.val().trim().toUpperCase();
    var name_value = _name.val().trim();
    var producer_value = producer.val().trim();
    var description_value = description.val().trim();

    if(code_value.length === 0) {
        code.addClass("invalid");
        errors.eq(0).html("The medicine code can't be empty!");
        event.preventDefault();
        return false;
    } else {
        code.addClass("valid");
    }

    if(name_value.length === 0) {
        _name.addClass("invalid");
        errors.eq(1).html("The name can't be empty!");
        event.preventDefault();
        return false;
    } else {
        _name.addClass("valid");
    }

    if(producer_value.length === 0) {
        producer.addClass("invalid");
        errors.eq(2).html("The producer can't be empty!");
        event.preventDefault();
        return false;
    } else {
        producer.addClass("valid");
    }

    if(description_value.length === 0) {
        description.addClass("invalid");
        errors.eq(3).html("The email can't be empty!");
        event.preventDefault();
        return false;
    } else {
        description.addClass("valid");
    }

    if(!radio_etici.is(":checked") && !radio_sop.is(":checked") && !radio_otc.is(":checked")){
        radio_etici.addClass("invalid");
        radio_sop.addClass("invalid");
        radio_otc.addClass("invalid");
        errors.eq(8).html("A type must be selected!");
        event.preventDefault();
        return false;
    } else {
        radio_etici.addClass("valid");
        radio_sop.addClass("valid");
        radio_otc.addClass("valid");
    }

    event.preventDefault();

    var medicine_json = {
        medicine: {
            code: code_value,
            name: name_value,
            medicine_class: $('input[name="type"]:checked').val(),
            producer: producer_value,
            description: description_value
        }
    };

    $.ajax({
        url: contextPath + "/rest/medicine",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(medicine_json),
        success: function(result) {
            add_medicine_message.addClass("alert alert-success").attr("role", "alert").text("New medicine added");
            //alert("New doctor added");
        },
        error: function(result) {
            console.log(result);
            add_medicine_message.addClass("alert alert-danger").attr("role", "alert").text("Problem occurred while adding new medicine");
            //alert("Problem occurred while processing the request");
        }
    });
});

$("input").focus(function () {
    $(this).removeClass("invalid");
    errors.html("");
});