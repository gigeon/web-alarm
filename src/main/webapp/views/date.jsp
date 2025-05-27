<%@ page import="java.lang.reflect.Array" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알려드림</title>
    <link rel="icon" href="../img/logo/icon.png"/>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="board_area">
    <div class="date_area">
        <button onclick="navigateToDate(0)"> ◀ </button>
        <h3>
            <%= request.getParameter("year") %>년
            <%= request.getParameter("month") %>월
            <%= request.getParameter("day") %>일
        </h3>
        <button onclick="navigateToDate(1)"> ▶ </button>
    </div>
    <div class="underline"></div>
    <div id="schdle_area"></div>

    <div class="btn_area right">
        <button onclick="navigateToReferrer()">이전</button>
    </div>
</div>

</body>
<script src="../js/api.js"></script>
<script src="../js/alarm.js"></script>
<script>

insertAlarmAll();

const urlParams = new URLSearchParams(window.location.search);

let year = urlParams.get("year");
let month = urlParams.get("month");
let day = urlParams.get("day");

const param = {
    "year": year,
    "month": month,
    "day": day,
}
var contextPath = "${pageContext.request.contextPath}";
initialize();

function initialize() {
    get(
        contextPath + "/date",
        param,
        (result) => {
            let schdleArea = document.getElementById("schdle_area");
            result.list.forEach(item => {
                const div = document.createElement("div");
                const underLine = document.createElement("div");
                div.onclick = () => {
                    navigateToDateDetail(item.schdleDtlId)
                }
                div.className = "schdle_row clickable";
                underLine.className = "schdle_row_underline";
                div.innerHTML =
                    "<h4>" + item.schdleTtl + "</h4>" +
                    "<h4>" + item.schdleCn + "</h4>";
                div.appendChild(underLine);
                schdleArea.appendChild(div);

            });

        },
        (result) => {
            alert("일정을 불러오는데 실패하였습니다.")
        })
}
function navigateToDateDetail(schdleDtlId) {
    window.location.href = "/webAlarm/views/dateDtl.jsp?schdleDtlId=" + schdleDtlId;
}

function navigateToReferrer() {
    window.location.href = "/webAlarm/views/home.jsp"
}

function navigateToDate(flag) {
    const currentDate = new Date(year, month, day); // 현재 날짜 객체

    if (flag === 0) {
        currentDate.setDate(currentDate.getDate() - 1);
    } else {
        currentDate.setDate(currentDate.getDate() + 1);
    }

    year = currentDate.getFullYear();
    month = currentDate.getMonth();
    day = currentDate.getDate();

    window.location.href = "/webAlarm/views/date.jsp?year=" + year + "&month=" + month + "&day=" + day;
}

</script>
</html>