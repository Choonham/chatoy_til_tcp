<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/udpClient.js"></script>
    <title>Title</title>
</head>
<body>
    <h1>Your IP : <span id="ip"></span></h1>
    <h1 > connect IP : <input id="serverIp" type="text"/></h1>
    <h1 > connect PORT : <input id="serverPort" type="text"/></h1>

    <input type="text" id="messageInput" style="width: 404px; height: 30px; border: 2px solid darkgray;" placeholder="메시지를 입력해주세요."/>

    <input type="button" size="10px" id="connectAndSend" value="전송"/>

</body>
</html>
