<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	$(document).ready(function() {
		$("#searchCategory").on("change", function() {
			if ($("#searchCategory").val() == 'name') {
				$("#sDate").css("display","none");
				$("#eDate").css("display","none");
				$("#search").css("display","inline");
			} else if ($("#searchCategory").val() == 'location') {
				$("#sDate").css("display","none");
				$("#eDate").css("display","none");
				$("#search").css("display","inline");
			} else if ($("#searchCategory").val() == 'date') {
				$("#sDate").css("display","inline");
				$("#eDate").css("display","inline");
				$("#search").css("display","none");
			} else if ($("#searchCategory").val() == 'userId'){
				$("#sDate").css("display","none");
				$("#eDate").css("display","none");
				$("#search").css("display","inline");
			}
		});

		$("#searchBtn").on("click", function() {
			if ($("#searchCategory").val() == '') {
				alert("검색할 내용을 선택해주세요.")
			} else {
				$(this).parent().submit();
			}

		});
	});
</script>

<div class="row">
	<div class="col-sm-6">
		<h2>일반공연정보 관리</h2>
	</div>
	<div class="col-sm-6">
		<form action="${initParam.rootPath }/admin/searchPerformance.do" method="post">
			<sec:csrfInput />
			<select id="searchCategory" name="category">
				<option value="">-----</option>
				<option value="name">공연이름</option>
				<option value="location">장소</option>
				<option value="date">날짜</option>
				<option value="userId">ID</option>
			</select> 
			
			<input id="search" type="text" name="search"> 
			<input style="display:none;" type="date" name="sDate" id="sDate"> 
			<input style="display:none;" type="date" name="eDate" id="eDate">
			
			<button type="button" id="searchBtn" class="btn btn-default">검색</button>
		</form>
	</div>
</div>

<div>
	<table class="table col-sm-12">
		<thead>
			<tr>
				<th>공연명</th>
				<th>사진</th>
				<th>위치</th>
				<th>날짜</th>
				<th>ID</th>
				<th>공연날짜</th>
				<th>상세보기</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list }" var="performance">
				<tr>
					<td>${performance.performanceName }</td>
					<td>
						<img style="width:100px; heigth:100px;" src="${initParam.rootPath }/performanceImage/${performance.performanceImage }" onerror='this.src="${initParam.rootPath }/performanceImage/no-image.png"'>
					</td>
					<td>${performance.performanceLocation }</td>
					<td>
						<fmt:formatDate value="${performance.performanceDate }" pattern="yyyy-MM-dd"/>
					</td>
					<td>${performance.performanceUserId }</td>
					<td>
						<fmt:formatDate value="${performance.performanceDate }" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						<form action="${initParam.rootPath }/admin/viewDetailPerformance.do" method="post">
							<sec:csrfInput/>
							<input type="hidden" name="performanceNo" value="${performance.performanceNo }">
							<button class="btn btn-default">상세보기</button>
						</form>
					</td>
					<td>
						<form action="${initParam.rootPath }/admin/updateNormalPerformance.do" method="post">
							<sec:csrfInput/>
							<input type="hidden" name="performanceNo" value="${performance.performanceNo }">
							<button class="btn btn-primary">수정</button>
						</form>
					</td>
					<td>
						<form action="${initParam.rootPath }/admin/deleteNormalPerformance.do" method="post">
							<sec:csrfInput/>
							<input type="hidden" name="performanceNo" value="${performance.performanceNo }">
							<button class="btn btn-warning">삭제</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>