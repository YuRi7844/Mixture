<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
$(document).ready(function(){
	$("#searchSelect").on("change",function(){
		if($(this).val()=='registTime'){
			$("#sDate").css("display","inline");
			$("#eDate").css("display","inline");
			$("#searchBar").css("display","none");
		} else if ($(this).val()=='reservation') {
			$("#sDate").css("display","none");
			$("#eDate").css("display","none");
			$("#searchBar").css("display","none");
		} else {
			$("#sDate").css("display","none");
			$("#eDate").css("display","none");
			$("#searchBar").css("display","inline");
		}
	});
});
</script>


<div class="row">
	<h2 class="text-center col-sm-6">일반공연장목록</h2>
	<form action="${initParam.rootPath }/admin/stageSearch.do" method="post" class="form-group col-sm-6">
		<select id="searchSelect" name="category" class="col-sm-3 form-controll">
			<option value="id">등록자 ID</option>
			<option value="registTime">등록시간</option>
		</select>
		<div id="searchDiv" class="col-sm-9">
			<select id="reservation" name="reserve">
				<option value="1">예약가능</option>
				<option value="0">예약완료</option>
			</select>
			<input class="form-controll" id="sDate" type="date" name="sDate" style="display:none;">
			<input class="form-controll" id="eDate" type="date" name="eDate" style="display:none;">
			<input id="searchBar" class="form-controll" type="text" name="userId">
			<button id="searchBtn" class="btn btn-primary">검색</button>
		</div>
		<sec:csrfInput/>
	</form>	
</div>

<div>
<!-- 
공연장번호,공급자 아이디, 공연장이름, 주소, 대관일, 내용, 승인코드(예약), 등록시간, 별점
-->
	<table class="table text-center">
		<thead class="thead text-center">
			<tr>
				<th>공연장번호</th>
				<th>공급자 아이디</th>
				<th>공연장 이름</th>
				<th>주소</th>
				<th>대관일</th>
				<th>내용</th>
				<th>예약상태</th>
				<th>등록시간</th>
				<th>별점</th>
				<th>상세보기</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list }" var="stage">
				<tr>
					<td>${stage.stageNo }</td>
					<td>${stage.stageSellerId }</td>
					<td>${stage.stageName }</td>
					<td>${stage.stageLocation }</td>
					<td><fmt:formatDate value="${stage.stageRentalDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${stage.stageContent }</td>
					<c:choose>
						<c:when test="${stage.stageReservation eq 1 }">
							<td>예약가능</td>
						</c:when>
						<c:otherwise>
							<td>예약완료</td>
						</c:otherwise>
					</c:choose>
					<td><fmt:formatDate value="${stage.stageRegTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${stage.starpointAvg }</td>
					<td>
						<form action="${initParam.rootPath }/stageDetail.do" method="post">
							<sec:csrfInput/>
							<input type="hidden" name="stageNo"	value="${stage.stageNo }">
							<button class="btn btn-default">상세보기</button>
						</form>
					</td>
					<td>
						<form action="${initParam.rootPath }/admin/updateStage.do" method="post">
							<sec:csrfInput/>
							<input type="hidden" name="stageNo"	value="${stage.stageNo }">
							<button class="btn btn-primary">수정</button>
						</form>
					</td>
					<td>
						<form action="${initParam.rootPath }/admin/deleteStage.do" method="post">
							<sec:csrfInput/>
							<input type="hidden" name="stageNo" value="${stage.stageNo }">
							<button class="btn btn-warning">삭제</button>
						</form>
					</td>
				</tr>
			</c:forEach>		
		</tbody>
	</table> 
</div>
