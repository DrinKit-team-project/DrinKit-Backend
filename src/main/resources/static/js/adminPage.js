'use strict';

$(".cafe-register-button").on("click", registerCafe);

function registerCafe(e) {
    e.preventDefault();
    console.log("hihi");

    var url = $(e.target).val();
    var cafeName = $("#cafeName").val();
    var cafeImgUrl = $("#cafeImgUrl").val();
    var categoryNames = $("#cafeCategory").val();
    var nameAndUrl = cafeName + "&" + cafeImgUrl + "&" + categoryNames;
    console.log(nameAndUrl);

    $.ajax({
        type: 'post',
        url: url,
        contentType: 'text/html; charset=utf-8',
        data: nameAndUrl,
        dataType: 'json'}).done(function makeCafeSuccess(data) {
            console.log("success to make cafe.");
            window.location.reload();
    }).fail(function makeCafeFail(data) {
        console.log("fail...");
        alert("fail to make cafe.");
    });
}