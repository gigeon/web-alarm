<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알려드림</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="icon" href="../img/logo/icon.png"/>
</head>
<body>
<div class="board_area">
    <div class="table_area">
        <table>
            <tr>
                <th colspan="3">제목</th>
                <td colspan="17">
                    <input class="cp_text" type="text" id="schdleTtl">
                </td>
            </tr>
            <tr>
                <th colspan="3">시작일</th>
                <td colspan="7">
                    <input class="cp_dateTime" type="datetime-local" id="startDt">
                </td>
                <th colspan="3">종료일</th>
                <td colspan="7">
                    <input class="cp_dateTime" type="datetime-local" id="endDt">
                </td>
            </tr>
            <tr>
                <th colspan="3">내용</th>
                <td colspan="17">
                    <textarea class="cp_textarea" id="schdleCn" ></textarea>
                </td>
            </tr>
        </table>
    </div>
    <div class="btn_area right">
        <button class="public" onclick="create()">저장</button>
        <button onclick="navigateToReferrer()">이전</button>
    </div>
</div>
</body>
<script src="../js/api.js"></script>
<script>
const urlParams = new URLSearchParams(window.location.search);
const contextPath = "${pageContext.request.contextPath}";

function create() {
    const param = new URLSearchParams();
    param.append('schdleTtl', document.getElementById("schdleTtl").value);
    param.append('schdleCn', document.getElementById("schdleCn").value);
    param.append('startDt', document.getElementById("startDt").value);
    param.append('endDt', document.getElementById("endDt").value);
    console.log(contextPath)
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
