'use strict';

$(".cafe-register-button").on("click", registerCafe);
$(".menu-register-button").on("click", registerMenu);
$(".addTagBtn").on("click", addTag);

function registerCafe(e) {
    e.preventDefault();
    console.log("registerCafe in.");

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

function registerMenu(e) {
    e.preventDefault();
    console.log("register Menu in.");

    var url = $(e.target).val();
    var krName = $("#menuKrName").val();
    var enName = $("#menuEnName").val();
    var calories = $("#menuCalories").val();
    var category = $("#menuCategory").val();
    var description = $("#menuDescription").val();
    var cafeName = $("#parent-cafe").val();

    var tagList = $("#menuAddTag").val();
    var pricePerSize = $("#menuPricePerSize").val();

    var menuInfo = krName + "&" + enName + "&" + calories + "&" + category + "&" +description + "&" + cafeName + "&" + tagList + "&" + pricePerSize;

    $.ajax({
        type: 'post',
        url: url,
        contentType: 'text/html; charset=utf-8',
        data: menuInfo,
        dataType: 'json'}).done(function addMenuSuccess(data) {
            console.log("success.");
            window.location.reload();
    }).fail(function addMenuFail(data) {
        console.log("fail..");
        alert("add menu fail....");
    })
}

function addTag(e) {
    e.preventDefault();
    console.log("add Tag in.");

    var url = $(e.target).val();
    console.log(url);

    var tagName = $("#tagName").val();
    console.log(tagName);

    $.ajax({
        type: 'post',
        url: url,
        contentType: 'text/html; charset=utf-8',
        data: tagName,
        dataType: 'json'}).done(function addTagSuccess(data) {
        console.log("success.");
        window.location.reload();
    }).fail(function addTagFail(data) {
        console.log("fail..");
        console.log(data);
        // alert("add tag fail....");
    })
}