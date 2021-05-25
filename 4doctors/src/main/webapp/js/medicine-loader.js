/*
var array_paesi = ['Belgio','Francia','Inghilterra','Italia','Olanda','Spagna'],
esempio_select_1 = document.getElementById('esempio_select_1');

for( paesi in array_paesi ) {
    esempio_select_1.add( new Option( array_paesi[paesi] ) );
};
*/
/*
var xhr = new XMLHttpRequest();
xhr.open('GET', '/rest/medicine');
xhr.send();
xhr.onload = function() {

    if(xhr.status === 200){

        responseObject = JSON.parse(xhr.responseText);
        med = document.getElementById('esempio_select_1');

        for (var i = 0; i < responseObject.resourceList.length; i++){

            med.add(new Option(responseObject.resourceList[i].medicine.name))

        }
    }
}
*/

$.getJSON("/rest/medicine", function(data) {
    var items = [];
    $.each(data, function(key, val) {   <option value="ipod">iPod</option>
        items.push( "<option value='" + key + "'>" + val + "</option>" );
        });
    $("<ul/>", {    "class": "my-new-list",    html: items.join( "" )  }).appendTo( "body" );});