<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-05-15
  Time: 오전 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알려드림</title>
</head>
<body>
<div>
    <table></table>
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
get(
    contextPath + "/date",
    param,
    (result) => {
        console.log("sucees")
    },
    (result) => {
        alert("일정을 불러오는데 실패하였습니다.")
    })
</script>
</html>