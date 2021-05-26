
(function() {
var xhr = new XMLHttpRequest();
var url = 'http://localhost:8080/4Doctors-1.00/rest/medicine';
xhr.onreadystatechange = alertContents;
xhr.open('GET', url);
xhr.send();

function alertContents() {

    if(xhr.readyState === XMLHttpRequest.DONE){

    if(xhr.status === 200){

        var responseObject = JSON.parse(xhr.responseText);
        var resource = responseObject['resourceList'];
        var med = document.getElementById('med_select');

        for (var i = 0; i < resource.length; i++){

            med.add(new Option(resource[i].medicine.name, resource[i].medicine.code));

        }
    }
    }
}
})();
