let selected = [];
let controllerUrl = "http://localhost:10000/api/v1/admin";
let collection;
let pocketBaseUrl;
let loginPocketBaseInfo = {
    email: "",
    password: "",
}
$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: controllerUrl + "/get-value",
        data: {},
        success: function (data) {
            collection = data.pocketBaseCollection,
                pocketBaseUrl = data.pocketBaseHost,
                loginPocketBaseInfo.email = data.pocketBaseEmail,
                loginPocketBaseInfo.password = data.pocketBasePassword,
                login(loginPocketBaseInfo)
        }
    });
});

function login(loginInformation) {
    $.ajax({
        url: pocketBaseUrl + "admins/auth-via-email",
        type: "POST",
        data: loginInformation,
        error: function (err) {
            console.log('Error!', err)
        },
        success: function (data) {
            localStorage.setItem("token", data.token)
        }
    });
}

$('#delete-image').click(function () {
    selected.forEach(imageId => {
            document.getElementById(imageId).remove();
            deleteImage(imageId)
        }
    );
    $.ajax({
        url: controllerUrl + "/delete-image",
        type: "DELETE",
        data: JSON.stringify(selected),
        contentType: 'application/json',
        dataType: 'json',
        cache: false,
        processData: false,
        error: function () {
            nativeToast({
                message: 'delete fail!',
                rounded: true,
                timeout: 2000,
                type: 'error',
            })
        },
        success: function () {
            nativeToast({
                message: 'deleted!',
                rounded: true,
                timeout: 2000,
                type: 'success',
            })
        },
    });
    selected = [];
});

function deleteImage(imageId) {
    $.ajax({
        url: pocketBaseUrl + "collections/" + collection + "/records/" + imageId,
        headers: {"Authorization": ("Admin " + localStorage.getItem('token'))},
        type: "DELETE",
        error: function (err) {
            console.log('Error!', err)
        },
        success: function (data) {
        }
    })
}

$('.image_in_lib').click(function () {
    let border = $(this).css("border");
    let width = border.split(" ");
    if (width[0] === "0px") {
        this.style = "border : 5px lightgreen solid;transition : 0.3s all;";
        selected.push(this.name);
    } else {
        this.style = "border :none;transition: 0.3s all";
        selected = selected.filter(item => item !== this.name);
    }
});

$('#upload').click(function () {
    window.location.href = controllerUrl + '/upload-image'
});

function search(val) {
    let searchName = $('#' + val).val();
    window.location.href = controllerUrl + '/media-library?keyword=' + searchName
}

function prev_button() {
    let a = document.getElementById("current").value;
    if (a > 1) {
        a--;
    }
    window.location.href = controllerUrl + '/media-library?currentPage=' + a;
}

function next_button() {
    let a = document.getElementById("current").value;
    let b = document.getElementById("total").value;
    if (a < b) {
        a++;
    }
    window.location.href = controllerUrl + '/media-library?currentPage=' + a;
}




