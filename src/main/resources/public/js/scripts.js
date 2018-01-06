

$(document).ready(function () {
    var href = $(location).attr('href');
    if (href.indexOf("problem") >= 0 || href.indexOf("send") >= 0)
        $('#problems_link').attr('class', 'active')
    else if (href.indexOf("result") >= 0)
        $('#results_link').attr('class', 'active');
    else if (href.indexOf('rating') >= 0 || href.indexOf('profile') >= 0)
        $('#ratings_link').attr('class', 'active');
    else
        $('#posts_link').attr('class', 'active');

    $("#form_search").on('submit', function () {
        var str = $("#search").val();
        if (str==null || str.length==0) {
            alert("Izlanayotgan so'zni kiriting");
            return false;
        }
    });


    $("#userForm").validate({
        errorClass: "validation-error",
        rules : {
            username: {
                required: true,
                minlength: 2,
                maxlength: 20
            },
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 40
            },
            passwordConfirm: {
                required: true,
                equalTo: "#password"
            }
        },
        messages : {
            username: {
                required: "Foydalanuvchi nomi kiriting",
                minlength: "Foydalanuvchi nomi kamida 2 ta simvol bo'lishi kerak",
                maxlength: "Foydalanuvchi nomi ko'pi bilan 20 ta simvol bo'lishi kerak"
            },
            email: {
                required: "Emailni kiriting",
                email: "To'g'ri email kiriting"
            },
            password: {
                required: "Parolni kiriting",
                minlength: "Parol 6 belgidan kam bo'lmasligi kerak",
                maxlength: "Parol 40 belgidan ko'p bo'lmasligi kerak"
            },
            passwordConfirm: {
                required: "Parolni tasdiqlang",
                equalTo: "Parollar mos kelmaydi"
            }
        }
    });
});