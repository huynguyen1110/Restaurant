let controllerUrl = "http://localhost:10000/api/v1/admin"
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

$('#mediaList').click(function () {
    window.location.href = controllerUrl + '/media-library?currentPage=1';
});

$('#image').change(function () {
        let img = document.getElementById('image')
        let allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;
        let fileSize = img.files[0].size;
        if (!allowedExtensions.exec(img.value)) {
            alert('Invalid file type! Accept: .jpg, .jpeg, .png');
            $('#image').val('');
            return false;
        } else if (fileSize > 5000000) {
            alert('File too big!');
            $('#image').val('');
            return false;
        } else {
            $(".file-upload-content").css("display", "block");
            let output = document.getElementById('output');
            output.src = URL.createObjectURL(img.files[0]);
            output.onload = function () {
                URL.revokeObjectURL(output.src)
            }
        }
    }
);

$('#uploadDone').click(function () {
    $(".file-upload-content").css("display", "none");
    $.ajax({
        url: pocketBaseUrl + "collections/" + collection + "/records",
        headers: {"Authorization": ("Admin " + localStorage.getItem('token'))},
        type: "POST",
        data: new FormData($("#formImage")[0]),
        contentType: false,
        cache: false,
        processData: false,
        error: function (err) {
            console.log('PocketBase  error! ' + err)
        },
        success: function (data) {
            let mapData = new Map(Object.entries(data))
            let postData = {
                collectionId: mapData.get("@collectionId"),
                collectionName: mapData.get("@collectionName"),
                imageId: data.id,
                imageName: data.image
            }
            saveImage(postData);
        }
    });
    $('#image').val('');
});

function saveImage(postData) {
    $.ajax({
        url: controllerUrl + "/save-image",
        type: "POST",
        data: JSON.stringify(postData),
        contentType: 'application/json',
        dataType: 'json',
        cache: false,
        processData: false,
        error: function () {
            nativeToast({
                message: 'upload fail!',
                rounded: true,
                timeout: 2000,
                type: 'error',
            })
        },
        success: function () {
            nativeToast({
                message: 'upload success!',
                rounded: true,
                timeout: 2000,
                type: 'success',
            })
        }
    })
}

$('.image-upload-wrap').bind('dragover', function () {
    $('.image-upload-wrap').addClass('image-dropping');
});
$('.image-upload-wrap').bind('dragleave', function () {
    $('.image-upload-wrap').removeClass('image-dropping');
});
