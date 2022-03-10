getIp();

$(document).ready(function () {
    let inputIp = $("#ip").data("ip");

    $(document).on('click', '#connectAndSend', function () {
        let to = $("#serverIp").val();
        let port = $("#serverPort").val();
        let message = $("#messageInput").val();

        $.ajax({
            url: "/udpClient/sendMessage.do" , //컨트롤러 URL
            data: {
                message: message,
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

                if(data.result == 1) {
                    console.log("메시지 전송함");
                } else if(data.result == 0) {
                    $("#status").text("연결 실패, IP 혹은 포트 번호를 확인해주세요.");
                }
            },
            error: function (jqXHR) {
                console.log(jqXHR);
            }
        });
    })
});

function getIp() {
    let temp = jQuery.when(
        $.getJSON("https://api.ipify.org/?format=json", function (data) {
        })
    ).done(function(json) {
        $("#ip").append(json.ip);
        $("#ip").attr("data-ip", json.ip);
    });
}