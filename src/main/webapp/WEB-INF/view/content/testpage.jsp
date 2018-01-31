<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#likeBtn").on("click",function(){
		$.ajax({
			"type":"POST",
			"url":"/buskstop/videoLike.do",
			"data":{
				"isLike":$("#like").text(),
				'${_csrf.parameterName}':'${_csrf.token}'
			},
			"dataType":"text",
			"success":function(data){
				alert(data);
				$("#like").text(data);
			},
			
		});
	});
});

</script>
</head>
<body>
<sec:authorize access="isAuthenticated()">
	<!-- 0이면 안누른상태, 1이면 좋아요. -->
	<button id="likeBtn">좋아요!</button>
	
	<c:choose>
		<c:when test="${requestScope.userLike eq 1}">
			<div id="like">1</div>
		</c:when>
		<c:otherwise>
			<div id="like">0</div>
		</c:otherwise>
	</c:choose>
	<span>0이면 안누른상태, 1이면 좋아요.</span>
</sec:authorize>
</body>
</html>