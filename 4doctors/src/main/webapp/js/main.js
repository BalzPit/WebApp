// triggers at the very beginning when the document is loaded
$(function() {

    setSiteHeight();

});

// triggers each time the window is resized
$(window).resize(setSiteHeight);

// set the #site height to avoid overflow (and consequent scrollbars) out of the body
function setSiteHeight() {

    //set height always except for mobile version
    if(($(document).height() < $(window).height()) || ($(window).width() > 768))
        $("#site").css("height", $(window).height() - $("header").outerHeight() - $("footer").outerHeight());
    else
         $("#site").css("height", "auto");
}