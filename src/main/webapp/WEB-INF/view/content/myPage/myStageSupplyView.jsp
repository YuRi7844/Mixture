<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="${initParam.rootPath}/resource/jquery/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
	$(".successBtn").on("click", function(){
		var success = confirm("예약을 수락하시겠습니까?");
		if(success){
			$.ajax({
				"url":"${initParam.rootPath}/successStageReservation.do",
				"type" : "post",
				"data" : {
					"rentalNo" : $(this).parent().parent().children("td:eq(0)").text(),
					'${_csrf.parameterName}':'${_csrf.token}'
				},
				"dataType" : "text",
				"success" : function(msg){
					alert(msg);
					location.reload();
				},
				"error": function(jqXHR, textStatus, errorThrown){
					alert(textStatus + " : " + errorThrown);
					location.reload();
				}
			});
		}else{
			alert("취소 되었습니다.");
		}
	});
	$(".rejectBtn").on("click", function(){
		var success = confirm("예약을 거절하시겠습니까?");
		if(success){
			$.ajax({
				"url":"${initParam.rootPath}/rejectStageReservation.do",
				"type" : "post",
				"data" : {
					"rentalNo" : $(this).parent().parent().children("td:eq(0)").text(),
					'${_csrf.parameterName}':'${_csrf.token}'
				},
				"dataType" : "text",
				"success" : function(msg){
					alert(msg);
					location.reload();
				},
				"error": function(jqXHR, textStatus, errorThrown){
					alert(textStatus + " : " + errorThrown);
					location.reload();
				}
			});
		}else{
			alert("취소 되었습니다.");
		}
	});
});
</script>
<body>
<h1>공급자 - 공연장 예약 상태 페이지 입니다.</h1>
<table style="text-align: center;">
	<thead>
		<tr>
			<td>대관 번호</td>
			<td>공연장 이름</td>
			<td>대관 날짜</td>
			<td>신청자 id</td>
			<td>신청 상태</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.stageReservation }" var="item">
		<tr style="height: 50px;">
			<td>${item.rentalNoNumber }</td>
			<td>${item.stageName }</td>
			<td><fmt:formatDate value="${item.rentalDate }" pattern="yyyy년 MM월 dd일"/></td>
			<td>${item.rentalUserId }</td><!-- 유저 정보 보여주는 페이지를 링크 하면 될것 같아요 -->
			<c:choose>
				<c:when test="${item.rentalStateCode eq 0 }">
				<td style="font-weight: bold;">
					거절됨
				</td>
				</c:when>
				<c:when test="${item.rentalStateCode eq 2 }">
				<td style="font-weight: bold;">
					수락됨
				</td>
				</c:when>
				<c:when test="${item.rentalStateCode eq 3 }">
				<td style="font-weight: bold;">
					취소됨
				</td>
				</c:when>
				<c:otherwise>
				<td>
					<button type="button" class="btn btn-success successBtn">수락</button>
					<button type="button" class="btn btn-danger rejectBtn">거절</button>
				</td>
				</c:otherwise>
			</c:choose>
		</tr>
		</c:forEach>
	</tbody>
</table>
<button type="button" onclick="history.back();">뒤로가기</button>
</body>