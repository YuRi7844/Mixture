<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("tr").on("click",function(){
		document.getElementById("detailForm").submit();
	});
});

</script>
<style type="text/css">
table, td {
	border: 1px solid black;
}

table {
	width: 100%;
	border-collapse: collapse;
}

td {
	padding: 5px;
	text-align: center;
}
tr:hover{
	background-color : yellow;
	
}

</style>
</head>
<body>
<sec:csrfInput/>
<table>
<!-- 
	VIDEO_NO NUMBER(10), /* 동영상번호 */
	VIDEO_TITLE VARCHAR2(100) NOT NULL, /* 제목 */
	VIDEO_LINK VARCHAR2(80) NOT NULL, /* 링크 */
	VIDEO_LOCATION VARCHAR2(30), /* 장소 */
	VIDEO_CONTENT VARCHAR2(3000), /* 게시자 등록글 */
	VIDEO_DATE DATE, /* 등록일자 */
	VIDEO_ARTIST VARCHAR2(200), /* 아티스트 */
	VIDEO_CATEGORY VARCHAR2(20), /* 영상 카테고리 */
	VIDEO_USER_ID VARCHAR2(50) NOT NULL, /* 사용자아이디 */
 -->
	<thead>
		<tr>
			<th>동영상번호</th>
			<th>제목</th>
			<th>링크</th>
			<th>장소</th>
			<th>내용</th>
			<th>날짜</th>
			<th>사용자 아이디</th>
			<th>등록시간</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${requestScope.list }" var="video">
			<tr>
				<td>${video.videoNo }</td>
				<td>${video.videoTitle }</td>
				<td>
					<img src="https://img.youtube.com/vi/${video.videoLink }/hqdefault.jpg">
				</td>
				<td>${video.videoLocation }</td>
				<td>${video.videoContent }</td>
				<td>${video.videoDate }</td>
				<td>${video.videoUserId }</td>
				<td>${video.videoRegTime }</td>
			</tr>
			<form action="${initParam.rootPath }/readVideoByVideoNo.do" method="post" id="detailForm">
				<sec:csrfInput/>
				<input type="hidden" value="${video.videoNo }" name="videoNo">
			</form>
		</c:forEach>
	</tbody>
</table>
</body>
</html>