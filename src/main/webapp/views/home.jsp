<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알려드림</title>
    <link rel="icon" href="../img/logo/icon.png"/>
    <link rel="stylesheet" href="../css/style.css">
</head>
<jsp:include page="../component/nav.jsp"/>
<body>
<%
    Calendar cal = Calendar.getInstance();

    String yearParam = request.getParameter("year");
    String monthParam = request.getParameter("month");

    int year = (yearParam != null) ? Integer.parseInt(yearParam) : cal.get(Calendar.YEAR);
    int month = (monthParam != null) ? Integer.parseInt(monthParam) : cal.get(Calendar.MONTH);

    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month);
    cal.set(Calendar.DAY_OF_MONTH, 1);

    int startDay = cal.get(Calendar.DAY_OF_WEEK);
    int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
%>

<div class="month_area">
    <button onclick="navigateToDate(0, <%= year %>, <%= month %>)"> ◀ </button>
    <h2><%= year %>년 <%= (month + 1) %>월</h2>
    <button onclick="navigateToDate(1, <%= year %>, <%= month %>)"> ▶ </button>

</div>

<div class="calender_area">
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
        </tr>
        <%
            int day = 1;
            boolean started = false;

            for (int i = 0; i < 6; i++) { // 최대 6줄 (6주)
        %>
        <tr>
            <%
                for (int j = 1; j <= 7; j++) { // 요일 1(일) ~ 7(토)
                    if (i == 0 && j < startDay) {
            %>
            <td></td>
            <%
            } else if (day <= lastDay) {
            %>
            <td onclick="navigateToDetail(<%= year %>, <%= month + 1 %>, <%= day %>);"><%= day %></td>
            <%
                day++;
            } else {
            %>
            <td></td>
            <%
                    }
                }
            %>
        </tr>
        <%
                if (day > lastDay) break;
            }
        %>
    </table>
</div>
<div class="btn_area">
    <button onclick="navigateToCreate()">등록</button>
    <button onclick="navigateToReferrer()">이전</button>
</div>
</body>
<script>

function navigateToDetail(year, month, day) {
    window.location.href = "/webAlarm/views/date.jsp?year=" + year + "&month=" + month + "&day=" + day;
}

function navigateToDate(flag, year, month) {
    if (flag === 0) {
        // 이전 달
        month--;
        if (month < 0) {
            month = 11;
            year--;
        }
    } else {
        // 다음 달
        month++;
        if (month > 11) {
            month = 0;
            year++;
        }
    }

    // 페이지 새로 로드하면서 파라미터 전달
    window.location.href = "/webAlarm/views/home.jsp?year=" + year + "&month=" + month;
}

function navigateToCreate() {
    window.location.href = "/webAlarm/views/create.jsp";
}
</script>
</html>