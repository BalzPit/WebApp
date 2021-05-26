
(function() {
var xhr1 = new XMLHttpRequest();
var xhr2 = new XMLHttpRequest();

var url_medicine = 'http://localhost:8080/4Doctors-1.00/rest/medicine';
var url_exams = 'http://localhost:8080/4Doctors-1.00/rest/exams';

xhr1.onreadystatechange = alertContents1;
xhr2.onreadystatechange = alertContents2;

xhr1.open('GET', url_medicine);
xhr2.open('GET', url_exams);

xhr1.send();
xhr2.send();

function alertContents1() {

    if(xhr1.readyState === XMLHttpRequest.DONE){

    if(xhr1.status === 200){

        var responseObject = JSON.parse(xhr1.responseText);
        var resource = responseObject['resourceList'];
        var med = document.getElementById('med_select');

        for (var i = 0; i < resource.length; i++){

            med.add(new Option(resource[i].medicine.name, resource[i].medicine.code));

        }
    }
    }
}

function alertContents2() {

    if(xhr2.readyState === XMLHttpRequest.DONE){

    if(xhr2.status === 200){

        var responseObject2 = JSON.parse(xhr2.responseText);
        var resource2 = responseObject2['resourceList'];
        var med2 = document.getElementById('ex_select');

        for (var i = 0; i < resource2.length; i++){

            med2.add(new Option(resource2[i].exam.name, resource2[i].exam.code));

        }
    }
    }
}



$("#medicine").click(function(){
        $("#medicine_select").css("display","block");
        $("#quant").css("display","block");
        $("#exam_select").css("display","none");
});

$("#exam").click(function(){
        $("#exam_select").css("display","block");
        $("#medicine_select").css("display","none");
        $("#quant").css("display","none");
});
})();


