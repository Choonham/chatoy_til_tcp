getIp();

$(document).ready(function () {
    let inputIp = $("#ip").data("ip");

    $(document).on('click', '#startChat', function () {
        setInterval(chatInterval, 1000);
    });

    $(document).on('click', '#connect', function () {
        let to = $("#serverIp").val();
        let port = $("#serverPort").val();

        $.ajax({
            url: "/tcpClient/connect.do" , //컨트롤러 URL
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

                if(data.result == 1) {
                    $("#status").text("연결 성공!");
                    $("#status").attr('data-id', data.userID);
                    $("#startChat").show();
                } else if(data.result == 0) {
                    $("#status").text("연결 실패, IP 혹은 포트 번호를 확인해주세요.");
                }
            },
            error: function (jqXHR) {
                console.log(jqXHR);
            }
        });
    })

    $(document).on('click', '#sendMessage', function () {
        let message = $("#messageInput").val();
        let sender = $("#ip").attr('data-ip');
        let userID = $("#status").attr('data-id');
        $.ajax({
            url: "/tcpClient/sendMessage.do" , //컨트롤러 URL
            data: {
                message: message,
                sender: sender,
                userID: userID
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-CSRF-TOKEN", $('#_csrf').val());
            },
            async: false,
            dataType: 'json',
            type: 'POST',
            success: function (data) {
                if(data.result == 1) {
                    chatInterval();
                    $("#messageInput").val("");
                } else if(data.result == 0) {
                    console.log("통신에 문제가 있습니다.");
                }
            },
            error: function (jqXHR) {
                console.log(jqXHR);
            }
        });
    })

});

function chatInterval() {
        console.log(123);
        $.ajax({
            url: "/tcpServer/showMessage.do" , //컨트롤러 URL
            data: {},
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-CSRF-TOKEN", $('#_csrf').val());
            },
            async: false,
            dataType: 'json',
            type: 'POST',
            success: function (data) {
                if(data.result == 1) {
                    $("#messageBox").empty();
                    $("#messageBox").append(data.message);
                }
            },
            error: function (jqXHR) {
                console.log(jqXHR);
            }
        });
}

function getIp() {
    let temp = jQuery.when(
        $.getJSON("https://api.ipify.org/?format=json", function (data) {
        })
    ).done(function(json) {
        $("#ip").append(json.ip);
        $("#ip").attr("data-ip", json.ip);
    });
}