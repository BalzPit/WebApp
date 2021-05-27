var doctors_table = $("#doctors_table");
var patients_table = $("#patients_table");
var doctors_wrapper = $("#doctors_wrapper");
var patients_wrapper = $("#patients_wrapper");

const contextPath = "http://localhost:8080/4Doctors-1.00";

$(document).ready(function (){
    $.getJSON(contextPath + "/rest/doctor/list", function (data, status) {
        if(status == "success") {
            var doctors_list = data["data"]["doctors-list"];

            if(doctors_list.length !== 0){
                $.each(doctors_list, function (index, element){
                    var cf_html = $(document.createElement("td")).text(element["cf"]);
                    var name_html = $(document.createElement("td")).text(element["name"]);
                    var surname_html = $(document.createElement("td")).text(element["surname"]);
                    var buttons = $(document.createElement("td")).html("<button class='info' type='button'>INFO</button><button class='update' type='button'>UPDATE</button>");
                    doctors_table.append($(document.createElement("tr")).append(cf_html).append(name_html).append(surname_html).append(buttons));
                });

                doctors_table.css("display", "inline-block");
            }
            else {
                doctors_wrapper.html("<p class='table_message'>There are no active doctors in the database.</p>");
            }
        } else {
            console.log(data);
            console.log(status);
            alert("Problem occurred while getting the list of doctors");
        }
    });

    $.getJSON(contextPath + "/rest/patient/list", function (data, status) {
        if(status == "success") {
            var patients_list = data["data"]["patients-list"];

            if(patients_list.length !== 0) {
                $.each(patients_list, function (index, element){
                    var cf_html = $(document.createElement("td")).text(element["cf"]);
                    var name_html = $(document.createElement("td")).text(element["name"]);
                    var surname_html = $(document.createElement("td")).text(element["surname"]);
                    var buttons = $(document.createElement("td")).html("<button class='info' type='button'>INFO</button><button class='delete' type='button'>DELETE</button>");
                    patients_table.append($(document.createElement("tr")).append(cf_html).append(name_html).append(surname_html).append(buttons));
                });

                patients_table.css("display", "inline-block");
            } else {
                patients_wrapper.html("<p class='table_message'>There are no patients in the database.</p>");
            }
        } else {
            console.log(data);
            console.log(status);
            alert("Problem occurred while getting the list of patients");
        }
    });
});

doctors_table.on("click", "button.info", function (){

    var row = $(this).parent().parent();

    if(row.next().attr("class") != "info_row") {
        var cf = $(this).parent().siblings("td:first").text();
        var info_row = $(document.createElement("tr")).attr("class", "info_row");
        var info_td = $(document.createElement("td")).attr("colspan", "4");

        $.getJSON(contextPath + "/rest/doctor/" + cf, function (data, status) {
            if(status == "success") {
                var doctor = data["data"]["doctor"];
                var list = $(document.createElement("ul"));
                $.each(doctor, function (index, element) {
                    list.append($(document.createElement("li")).text(element));
                })
                info_td.html(list);
                info_row.html(info_td);
                row.after(info_row);
            }
            else {
                console.log(data);
                console.log(status);
                alert("Problem occurred while processing the request");
            }
        });
    } else {
        row.next().remove();
    }
});

patients_table.on("click", "button.info", function (){

    var row = $(this).parent().parent();

    if(row.next().attr("class") != "info_row") {
        var cf = $(this).parent().siblings("td:first").text();
        var info_row = $(document.createElement("tr")).attr("class", "info_row");
        var info_td = $(document.createElement("td")).attr("colspan", "4");

        $.getJSON(contextPath + "/rest/patient/" + cf, function (data, status) {
            if(status == "success") {
                var patient = data["data"]["patient"];
                var list = $(document.createElement("ul"));
                $.each(patient, function (index, element) {
                    list.append($(document.createElement("li")).text(element));
                })
                info_td.html(list);
                info_row.html(info_td);
                row.after(info_row);
            }
            else {
                console.log(data);
                console.log(status);
                alert("Problem occurred while processing the request");
            }
        });
    } else {
        row.next().remove();
    }
});

doctors_table.on("click", "button.update", function (){
    var cf = $(this).parent().siblings("td:first").text();
    var row = $(this).parent().parent();
    var next_row = row.next();

    $.ajax({
        url: contextPath + "/rest/doctor/" + cf,
        method: "PUT",
        contentType: "application/json",
        success: function(result) {
            row.remove();
            if(next_row.attr("class") == "info_row") {
                next_row.remove();
            }
            if($("#doctors_table tr").length === 1) {
                doctors_wrapper.html("<p class='table_message'>There are no active doctors in the database.</p>");
            }
        },
        error: function(result) {
            console.log(result);
            alert("Problem occurred while processing the request");
        }
    });
});

patients_table.on("click", "button.delete", function (){
    var cf = $(this).parent().siblings("td:first").text();
    var row = $(this).parent().parent();
    var next_row = row.next();

    $.ajax({
        url: contextPath + "/rest/patient/" + cf,
        method: "DELETE",
        success: function(result) {
            row.remove();
            if(next_row.attr("class") == "info_row") {
                next_row.remove();
            }
            if($("#patients_table tr").length === 1) {
                patients_wrapper.html("<p class='table_message'>There are no patients in the database.</p>");
            }
        },
        error: function(result) {
            console.log(result);
            alert("Problem occurred while processing the request");
        }
    });
});