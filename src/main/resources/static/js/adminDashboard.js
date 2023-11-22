
$(document).ready(function () {
    var trigger = $('.hamburger'),
        overlay = $('.overlay'),
        isClosed = false;

    trigger.click(function () {
        hamburger_cross();
    });

    function hamburger_cross() {
        if (isClosed == true) {
            overlay.hide();
            trigger.removeClass('is-open');
            trigger.addClass('is-closed');
            isClosed = false;
        } else {
            overlay.show();
            trigger.removeClass('is-closed');
            trigger.addClass('is-open');
            isClosed = true;
        }
    }

    $('[data-toggle="offcanvas"]').click(function () {
        $('#wrapper').toggleClass('toggled');
    });
        // Close the sidebar when a link is clicked
        // $(".nav.sidebar-nav li a").click(function () {
        //     if ($("#wrapper").hasClass("toggled")) {
        //         $("#wrapper").removeClass("toggled");
        //     }
        // });
    // Handle the click event for "Manage Technology" link
    $(document).on('click', '#manageTechnology', function (e) {
        e.preventDefault(); // Prevent the default link behavior
        var url = $(this).data('url'); // Get the URL from the data attribute

        // Hide the map and charts when "Manage Technology" is clicked
        $('.indian-map').hide(); // Hide the map container
        $('.chart-container').hide(); // Hide all elements with class 'chart-container'
        $('.barChartAdmin').hide();
        $('.pieChartAdmin').hide();


        // Load the content based on the URL
        loadContent(url);
    });

    // Handle the click event for "Manage Expert" link
    $(document).on('click', '#viewExpert', function (e) {
        e.preventDefault(); // Prevent the default link behavior
        var url = $(this).data('url'); // Get the URL for manageExpert.html

        // Hide the map, charts, and "Manage Technology" content when "Manage Expert" is clicked
        $('.indian-map').hide();
        $('.chart-container').hide();
        $('.barChartAdmin').hide();
        $('.pieChartAdmin').hide();

        // $('#manage-technology-content').hide();

        // Load the content based on the URL (manageExpert.html)
        loadContent(url);
    });

    // Handle the click event for "View Queries" link
    $(document).on('click', '#viewQueries', function (e) {
        e.preventDefault(); // Prevent the default link behavior
        var url = $(this).data('url'); // Get the URL for viewQueries.html

        // Hide the map, charts, and "Manage Technology" content when "View Queries" is clicked
        $('.indian-map').hide();
        $('.chart-container').hide();
        $('.barChartAdmin').hide();
        $('.pieChartAdmin').hide();


        // $('#manage-technology-content').hide();

        // Load the content based on the URL (viewQueries.html)
        loadContent(url);
    });


    function loadContent(url) {
        // Get the content container div
        var contentContainer = $('#state-info');

        // You can use AJAX to load content dynamically, for example:
        $.ajax({
            url: url, // Use the URL from the data attribute
            method: 'GET',
            success: function (data) {
                // Set the loaded HTML content into the container
                contentContainer.html(data);
                // Display the container
                contentContainer.show();
            },
            error: function () {
                console.error('Error loading content');
            }
        });
    }
});
