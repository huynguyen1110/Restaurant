$('#summernote').summernote({
    tabsize: 2,
    height: 200,
    callbacks: {
        onChange: function (contents) {
            console.log(contents)
            $("#description").val(contents);
            document.getElementById('descriptionError').innerText = "";
        }
    }
});
function validateTitle() {
    let title = document.getElementById('title').value;
    if (title.startsWith(" ") || title.endsWith(" ")) {
        document.getElementById('titleError').innerText = "Title without starting and ending spaces";
        return false;
    } else {
        for (let i = 0; i < title.length; i++) {
            let character = title.charAt(i);
            if (character == '@' || character == '$' || character == '#') {
                document.getElementById('titleError').innerText = "Title must not contain characters '" + character + "'";
                return false;
            }
        }
    }
    document.getElementById('titleError').innerText = "";
}
function validateEmpty() {
    let title = document.getElementById("title").value;
    let description = document.getElementById("description").value;
    if (title == "" || title == null) {
        document.getElementById('titleError').innerText = "Title cannot be blank.";
        return false;
    }
    if (description == "" || description == null) {
        document.getElementById('descriptionError').innerText = "Description cannot be blank.";
        return false;
    }
}
$("#form-add-plan").submit((event) => {
    event.preventDefault()
    validateEmpty()
    let planAjax = {
        title: $("#title").val(),
        description: {
            content: $("#description").val(),
        },
        promoPrice: $("#promoPriceId").val(),
        price: $("#priceId").val(),
        monthly: $("#monthly").val(),
        itemLimit: $("#itemLimitId").val(),
        orderLimit: $("#orderLimitId").val(),
        trialPeriod: $("#trialPeriodId").val(),
        ordering: $('.ordering').is(":checked"),
        status: $("#status").val(),
    }
    $.ajax({
        type: 'POST',
        url: '/api/v1/plan/create',
        data: JSON.stringify(planAjax),
        contentType: "application/json",
        datatype: "json",
        success: function () {
            document.getElementById("form-add-plan").reset();
            $("#summernote").summernote("code", "");
            nativeToast({
                message: 'Insert ok!',
                rounded: true,
                timeout: 2000,
                type: 'success',
            })
            setTimeout(successOk, 3000);
        },
        error: function () {
            nativeToast({
                message: 'Insert fail!',
                rounded: true,
                timeout: 2000,
                type: 'error',
            })
            document.getElementById('error').innerText = "Insert failed";
        }
    })
});
function successOk() {
    window.location.href = '/api/v1/plan/list'
}
function  validatePrice1(){
    const even = document.createEvent('Event');
    if ($.inArray(even.keyCode, [8]) !== -1 ||
        ((even.keyCode == 65 || even.keyCode == 67 || even.keyCode == 86) && even.ctrlKey === true) ||
        (even.keyCode >= 35 && even.keyCode <= 39) || even.key == ".") {
        return;
    }
    if (e.shiftKey || e.keyCode < 48 || e.keyCode > 57) {
        e.preventDefault();
    }
}
function  validatePrice2(id){
    let price = $("#"+id).val();
    if (price.startsWith(".")) {
        $("#"+id).val("");
    }
    for (let i = 0; i < price.length; i++) {
        let character = price.charCodeAt(i);
        if ((character < 48 || character > 57) && character != 46) {
            $("#"+id).val("0.00");
        }
    }
}
function  validatePrice3(id){
    let price = $("#"+id).val();
    let price2 = "";
    if (price.endsWith(".")) {
        for (let i = 0; i < price.length - 1; i++) {
            price2 += price[i];
        }
        $("#"+id).val(price2);
    }
}
function validatePlanPeriod1() {
    const e = document.createEvent('Event');
    if ($.inArray(e.keyCode, [8]) !== -1 ||
        ((e.keyCode == 65 || e.keyCode == 67 || e.keyCode == 86) && e.ctrlKey === true) ||
        (e.keyCode >= 35 && e.keyCode <= 39)) {
        return;
    }
    if (e.shiftKey || e.keyCode < 48 || e.keyCode > 57) {
        e.preventDefault();
    }
}
function validatePlanPeriod2(id) {
    let price = $("#" + id).val();
    for (let i = 0; i < price.length; i++) {
        let character = price.charCodeAt(i);
        if (character < 48 || character > 57) {
            $("#" + id).val("0");
        }
    }
}
