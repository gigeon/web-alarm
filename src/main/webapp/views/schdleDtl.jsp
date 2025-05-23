<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="icon" href="../img/logo/icon.png"/>
</head>
<body>
<div class="board_area">
    <table border="1">
        <tr>
            <td>제목</td>
            <td>
                <input class="cp-text" type="text" id="schdleTtl">
            </td>
        </tr>
        <tr>
            <td>내용</td>
            <td>
                <textarea id="schdleCn" ></textarea>
            </td>
        </tr>
        <tr>
            <td>생성일자</td>
            <td>
                <input class="cp-dateTime" type="datetime-local" id="createDt">
            </td>
        </tr>
    </table>

    <div class="board_title">
        <img src="../img/docs.png">
        <h2>상세</h2>
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
        <div id="grid_list"></div>
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
            console.log(result)
        },
        () => {
            alert("조회에 실패하였습니다.")
        }
    )
}
</script>
</html>
