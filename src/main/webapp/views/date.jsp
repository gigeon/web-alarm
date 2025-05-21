<%@ page import="java.lang.reflect.Array" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알려드림</title>
    <link rel="icon" href="../img/logo/icon.png"/>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div>
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
</div>

<div id="schdle_area"></div>

<div class="btn_area">
    <button onclick="navigateToReferrer()">이전</button>
</div>
</body>
<script src="../js/api.js"></script>
<script>
const urlParams = new URLSearchParams(window.location.search);

const year = urlParams.get("year");
const month = urlParams.get("month");
const day = urlParams.get("day");

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
                div.onclick = () => {
                    navigateToDateDetail(item.schdleDtlId)
                }
                // div.className
                div.innerHTML =
                    "<h4>" + item.schdleTtl + "</h4>" +
                    "<h4>" + item.schdleCn + "</h4>";
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
    window.location.href = document.referrer;
}

</script>
</html>