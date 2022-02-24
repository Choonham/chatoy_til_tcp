getIp();

$(document).ready(function () {
    let inputIp = $("#ip").data("ip");
});

$("#submit").click(function() {
    let to = $("#serverIp").val();
    let port = $("#serverPort").val();

    $.ajax({
        url: "/client/tcp.do" , //컨트롤러 URL
        data: { 
            ip: to, 
            port: port
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader("X-CSRF-TOKEN", $('#_csrf').val());
        },
        async: false,
        dataType: 'json',
        type: 'POST',
        success: function (data) {
            console.log(data);
        },
        error: function (jqXHR) {
            console.log(jqXHR);
        }
    });
})

function getIp() {
    let temp = jQuery.when(
        $.getJSON("https://api.ipify.org/?format=json", function (data) {
        })
    ).done(function(json) {
        $("#ip").append(json.ip);
        $("#ip").attr("data-ip", json.ip);
    });
}