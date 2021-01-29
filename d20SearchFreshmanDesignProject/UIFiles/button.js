// Name of File - GamepadButton.js
// Author(s) - Cassidy Ashe, https://bbbootstrap.com/snippets/simple-back-top-smooth-scroll-17111555
// Purpose - Function for return-to-top button on search.html, help.html, and create.html
// Dependencies - JavaScript
// Date of last Edit - May 29, 2020

// back to top button, curtesy of https://bbbootstrap.com/snippets/simple-back-top-smooth-scroll-17111555
$(document).ready(function() {
    $(window).scroll(function() {
    if ($(this).scrollTop() > 20) {
    $('#toTopBtn').fadeIn();
    } else {
    $('#toTopBtn').fadeOut();
    }
    });
    
    $('#toTopBtn').click(function() {
    $("html, body").animate({
    scrollTop: 0
    }, 1000);
    return false;
    });
    });