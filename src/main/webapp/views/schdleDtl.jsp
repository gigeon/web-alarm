<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
<script src="../js/api.js"></script>
<script>
const urlParams = new URLSearchParams(window.location.search);
const schdleId = urlParams.get("schdleId");
const contextPath = "${pageContext.request.contextPath}";

read()

function read() {
    let param = {"schdleId": schdleId}
    get(
        contextPath + "/date/detail",
        param,
        (result) => {

        },
        () => {
            alert("조회에 실패하였습니다.")
        }
    )
}
</script>
</html>
