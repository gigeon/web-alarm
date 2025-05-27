<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알려드림</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="icon" href="../img/logo/icon.png"/>
</head>
<body>
<div class="board_area">
    <div class="board_title">
        <img src="../img/docs.png">
        <h2>상세</h2>
    </div>
    <div class="table_area">
        <table>
            <tr>
                <td colspan="3">제목</td>
                <td colspan="17">
                    <input class="cp_text" type="text" id="schdleTtl" disabled>
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
                    <textarea class="cp_textarea" id="schdleCn" disabled></textarea>
                </td>
            </tr>
        </table>
    </div>
    <div class="btn_area right">
        <button class="public" onclick="update()">저장</button>
        <button class="warning" onclick="deleteDate()">삭제</button>
        <button onclick="navigateToReferrer()">이전</button>
    </div>
</div>
</body>
<script src="../js/api.js"></script>
<script>
const urlParams = new URLSearchParams(window.location.search);
const schdleDtlId = urlParams.get("schdleDtlId");
const contextPath = "${pageContext.request.contextPath}";
let schdleId = "";

read();

function read() {
    let param = {"schdleDtlId": schdleDtlId}
    get(
        contextPath + "/date/detail",
        param,
        (result) => {
            document.getElementById("schdleTtl").value = result.schdleTtl;
            document.getElementById("startDt").value = result.startDt;
            document.getElementById("endDt").value = result.endDt;
            document.getElementById("schdleCn").value = result.schdleCn;
            schdleId = result.schdleId
        },
        () => {
            alert("조회에 실패하였습니다.")
        }
    )
}
function update() {
    const param = new URLSearchParams();
    param.append('schdleDtlId', schdleDtlId);
    param.append('schdleId', schdleId);
    param.append('startDt', document.getElementById("startDt").value);
    param.append('endDt', document.getElementById("endDt").value);
    put(
        contextPath + "/date",
        param,
        () => {
            alert("저장에 성공하였습니다.")
        },
        () => {
            alert("저장에 실패하였습니다.")
        }
    )
}

function deleteDate() {
    const param = new URLSearchParams();
    param.append('schdleDtlId', schdleDtlId);
    dlt(
        contextPath + "/date",
        param,
        () => {
            alert("삭제에 성공하였습니다.")
            navigateToReferrer();
        },
        () => {
            alert("삭제에 실패하였습니다.")
        }
    )
}

function navigateToReferrer() {
    window.location.href = document.referrer;
}
</script>
</html>
