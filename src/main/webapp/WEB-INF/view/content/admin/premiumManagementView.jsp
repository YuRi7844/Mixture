<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
$(document).ready(function(){
	$("#searchBtn").on("click",function(){
		if($("#searchCategory").val()==''){
			alert("검색할 내용을 선택해주세요.")
		} else {
			$(this).parent().submit();
		}
		
	});
});
</script>

<div class="row">
	<div class="col-sm-6">
		<h2>프리미엄공연장 관리</h2>
	</div>
	<div class="col-sm-6">
		<form action="${initParam.rootPath }/admin/searchPremiumStageAdmin.do" method="post">	
			<sec:csrfInput/>
			<select id="searchCategory" name="category">
				<option value="">-----</option>
				<option value="establishNo">사업장번호</option>
				<option value="userId">ID</option>
				<option value="location">위치</option>
			</select>
			<input type="text" name="search">
			<button type="button" id="searchBtn" class="btn btn-default">검색</button>
		</form>
	</div>
</div>
<div>
	<table class="table">
		<tr>
			<th>사업장번호</th>
			<th>대표사진</th>
			<th>공급자 ID</th>
			<th>공연장 이름</th>
			<th>위치</th>
			<th>상세보기</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		
		<c:forEach items="${requestScope.list }" var="list">
			<tr>
				<td>${list.establishNum }</td>
				<td>
					<img style="width:100px; height:100px;" src="${initParam.rootPath }/supplierImage/${list.stageImage }" onerror='this.src="${initParam.rootPath }/supplierImage/no-image.png"'>
				</td>
				<td>${list.operatorUserId }</td>
				<td>${list.stageName }</td>
				<td>${list.stageLocation }</td>
				<td>
					<form action="${initParam.rootPath }/admin/premiumStageDetail.do" method="post">
						<sec:csrfInput/>
						<input type="hidden" name="establishNum" value="${list.establishNum }">
						<button class="btn btn-default">상세보기</button>				
					</form>
				</td>
				<td>
					<form action="${initParam.rootPath }/admin/goUpdateView.do" method="post">
						<sec:csrfInput/>
						<input type="hidden" name="establishNum" value="${list.establishNum }">
						<button class="btn btn-primary">수정</button>
					</form>
				</td>
				<td>
					<form action="${initParam.rootPath }/admin/deletePremium.do" method="post">
						<sec:csrfInput/>
						<input type="hidden" name="establishNum" value="${list.establishNum }">
						<input type="hidden" name="userId" value="${list.operatorUserId }">
						<button class="btn btn-warning">삭제</button>
					</form>
				</td>
			</tr>		
		</c:forEach>
	</table>
</div>