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
                <th>제목</th>
                <td>
                    <input class="cp_text" type="text" id="schdleTtl">
                </td>
            </tr>
            <tr>
                <th>내용</th>
                <td>
                    <textarea id="schdleCn" class="cp_textarea"></textarea>
                </td>
            </tr>
            <tr>
                <th>생성일자</th>
                <td>
                    <input class="cp-dateTime" type="datetime-local" id="createDt" disabled>
                </td>
            </tr>
        </table>
    </div>

    <div class="board_title">
        <img src="../img/docs.png">
        <h2>상세 목록</h2>
    </div>

    <div class="board_grid">
        <div class="grid_title sixCol">
            <p>아이디</p>
            <p>시작일</p>
            <p>종료일</p>
            <p>생성일자</p>
            <p>사용여부</p>
            <p>삭제</p>
        </div>
        <div class="grid_underline"></div>
        <div id="grid_list"></div>
    </div>
    <div class="btn_area right">
        <button class="public" onclick="update()">저장</button>
        <button onclick="navigateToReferrer()">이전</button>
    </div>
</div>
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
        contextPath + "/schdle/detail",
        param,
        (result) => {
            document.getElementById("schdleTtl").value = result.schdle.schdleTtl;
            document.getElementById("schdleCn").value = result.schdle.schdleCn;
            document.getElementById("createDt").value = result.schdle.createDt;

            let gridList = document.getElementById("grid_list");
            result.list.forEach(item => {
                const div = document.createElement("div");
                div.className = "grid_data sixCol";
                div.id = "id" + item.schdleDtlId
                div.innerHTML =
                    "<p>" + item.schdleDtlId + "</p>" +
                    "<p>" + item.startDt + "</p>" +
                    "<p>" + item.endDt + "</p>" +
                    "<p>" + item.createDt  + "</p>" +
                    "<p>" + (item.useYn == "true" ? 'Y' : 'N')  + "</p>" +
                    "<p>" +
                        "<button class='warning grid_btn' onclick='dataDlt(" + item.schdleDtlId + ")'>삭제</button> " +
                    "</p>";
                gridList.appendChild(div);
            })
        },
        () => {
            alert("조회에 실패하였습니다.")
        }
    )
}

function update() {
    const param = new URLSearchParams();
    param.append('schdleId', schdleId);
    param.append('schdleTtl', document.getElementById("schdleTtl").value);
    param.append('schdleCn', document.getElementById("schdleCn").value);
    put(
        contextPath + "/schdle",
        param,
        () => {
            alert("저장에 성공하였습니다.")
        },
        () => {
            alert("저장에 실패하였습니다.")
        }
    )
}

function dataDlt(schdleDtlId) {
    console.log(schdleDtlId)
    const param = new URLSearchParams();
    param.append('schdleId', schdleDtlId);
    dlt(
        contextPath + "/schdle",
        param,
        () => {
            location.reload(true);
            alert("삭제에 성공하였습니다.")
        },
        () => {
            alert("삭제에 실패하였습니다.")
        }
    )
}

function navigateToReferrer() {
    window.location.href = "/webAlarm/views/list.jsp";
}
</script>
</html>
