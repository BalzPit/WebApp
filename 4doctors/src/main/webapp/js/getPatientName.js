var absolutePath = function(href) {
    var link = document.createElement("a");
    link.href = href;
    return link.href;
}

function getPatientName() {
    var httpRequest = new XMLHttpRequest();
    var rest_url = absolutePath(contexPath + '/rest/patient/' + cf);

    if (!httpRequest){
      alert("Cannot create an XMLHTPP instance");
      return false;
    }

    httpRequest.onreadystatechange = getName;
    httpRequest.open('GET', rest_url);
    httpRequest.send();

    function getName() {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {

            if (httpRequest.status == 200) {
                var jsonData = JSON.parse(httpRequest.responseText);
                var patient = jsonData['data']['patient'];
                $('.welcome-title').append(patient.name);
            }
        }
    }
};

getPatientName();