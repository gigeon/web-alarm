<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Date d = new Date();
%>
<head>
    <title>웹 알림 프로그램</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="monthArea">
        <h2> <%= d.getMonth()+1 %> </h2>
    </div>
    <div>
        <table>
            <tr>
                <th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
            </tr>
        </table>
    </div>
</body>
</html>
