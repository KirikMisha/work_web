$(document).ready(function() {
    var path = window.location.pathname;
    var link = $('header a[href="' + path + '"]');
    link.addClass('active');

    $(".navbar-toggler").click(function() {
        $("#collapsedNav").toggleClass("show");
    });

    $(document).click(function(event) {
        if (!$(event.target).closest('.navbar-toggler').length && !$(event.target).closest('#collapsedNav').length) {
            $("#collapsedNav").removeClass("show");
        }
    });
});

