$(document).ready(function () {
    $('#admin_name').on('click', function (event) {
        event.preventDefault();
        $(this).closest('#header_dropdown').find('.account_dropdown').toggle();
    });
    $(document).on('click', function (event) {
        if ($(event.target).closest('#header_dropdown').length === 0) {
            $('.account_dropdown').hide();
        }
    });
});

