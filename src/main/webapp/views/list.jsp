<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알려드림</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="icon" href="../img/logo/icon.png"/>
</head>
<jsp:include page="../component/nav.jsp"/>
<body>
<div class="board_area">
    <div class="board_title">
        <img src="../img/docs.png">
        <h2>목록</h2>
    </div>
    <div class="board_grid">
        <div class="grid_title threeCol">
            <p>제목</p>
            <p>내용</p>
            <p>생성일</p>
        </div>
        <div class="grid_underline"></div>
        <div id="grid_list"></div>
    </div>
    <div class="btn_area right">
        <button class="public" onclick="navigateToCreate()">등록</button>
    </div>
</div>
</body>
<script src="../js/api.js"></script>
<script src="../js/alarm.js"></script>
<script>

insertAlarmAll();

const contextPath = "${pageContext.request.contextPath}";

read();

function read() {
    get(
        contextPath + "/schdle",
        null,
        (result) => {
            let gridList = document.getElementById("grid_list");
            result.list.forEach(item => {
                const div = document.createElement("div");
                div.onclick = () => { navigateToSchdleDtl(item.schdleId) }
                div.className = "grid_data threeCol";
                div.innerHTML =
                    "<p>" + item.schdleTtl + "</p>" +
                    "<p>" + item.schdleCn + "</p>" +
                    "<p>" + item.createDt + "</p>";
                gridList.appendChild(div);
            })
        },
        () => {
            alert("조회에 실패하였습니다.")
        }
    )
}

function navigateToSchdleDtl(schdleId) {
    window.location.href = "/webAlarm/views/schdleDtl.jsp?schdleId=" + schdleId;
}

function navigateToCreate() {
    window.location.href = "/webAlarm/views/create.jsp";
}
</script>
</html>
