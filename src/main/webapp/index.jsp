<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알려드림</title>
    <link rel="icon" href="img/logo/icon.png"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="login_area">
        <h1 class="title">알려드림</h1>
        <div class="login_box">
            <div class="mb5">
                <input type="text" id="userId" placeholder="아이디"/>
            </div>
            <div>
                <input type="text" id="userPwd" placeholder="비밀번호"/>
            </div>
            <div class="btn_area">
                <button class="public" onclick="login()">로그인</button>
            </div>
        </div>
    </div>
</body>

<script>
    function login() {
        let userId = document.getElementById('userId').value;
        let userPwd = document.getElementById('userPwd').value;

        const params = new URLSearchParams();
        params.append('userId', userId);
        params.append('userPwd', userPwd);

        var contextPath = "${pageContext.request.contextPath}";
        fetch(contextPath  + '/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params.toString()
        })
        .then(response => {
            return response.json()
        })
        .then(result => {
            if(result.flag == 1) {
                document.cookie = "sessionId=" + result.sessionid
                document.cookie = "userId=" + userId

                window.location.href = "/webAlarm/views/home.jsp";
            } else {
                alert("로그인이 불가합니다.")
            }
        })
        .catch(result => {
            alert("로그인이 불가합니다.")
        })
    }

    // function insertAlarm() {
    //     Notification.requestPermission().then(function(permission) {
    //         if( permission == gr)
    //     })
    // }
</script>
</html>
