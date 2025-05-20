<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알려드림</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div>
    <table border="1">
        <tr>
            <td>제목</td>
            <td>
                <input class="cp-text" type="text" id="schdleTtl">
            </td>
        </tr>
        <tr>
            <td>시작일</td>
            <td>
                <input class="cp-dateTime" type="datetime-local" id="startDt">
            </td>
            <td>종료일</td>
            <td>
                <input class="cp-dateTime" type="datetime-local" id="endDt">
            </td>
        </tr>
        <tr>
            <td>내용</td>
            <td>
                <textarea id="schdleCn" ></textarea>
            </td>
        </tr>
    </table>
</div>
<div class="btn_area">
    <button onclick="create()">저장</button>
    <button onclick="navigateToReferrer()">이전</button>
</div>
</body>
<script src="../js/api.js"></script>
<script>
const urlParams = new URLSearchParams(window.location.search);
const contextPath = "${pageContext.request.contextPath}";

read();
function create() {
    const param = new URLSearchParams();
    param.append('schdleDtlId', schdleDtlId);
    param.append('schdleId', schdleId);
    param.append('startDt', document.getElementById("startDt").value);
    param.append('endDt', document.getElementById("endDt").value);
    post(
        contextPath + "/date",
        param,
        () => {
            alert("저장에 성공하였습니다.")
            navigateToReferrer();
        },
        () => {
            alert("저장에 실패하였습니다.")
        }
    )
}

function navigateToReferrer() {
    window.location.href = document.referrer;
}
</script>
</html>
