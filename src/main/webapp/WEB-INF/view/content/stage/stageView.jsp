<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>

<script>
	$(document).ready(function(){
		<%--
		var nowDate = new Date().toISOString().substr(0, 10).replace('T', ' ');
		$("#startDate").val(nowDate);
		--%>
		$(".submit").click(function(){
		if($("#locationSearch").val()=="" 
			&& $("#nameSearch").val()==""
			&& $("#idSearch").val()==""
			&& ($("#startDate").val()==""
			&& $("#endDate").val()=="")){
				alert("검색할 키워드를 입력해주세요.");
				return false;
			} 
			
		})
	});
	
	function goDetail(root, no){
		document.location.href= root+'/stageDetail.do?stageNo='+no
	}
	
	
</script>
<style type="text/css">
table {
	width: 100%;
	border-collapse: collapse;
}
td {
	padding: 5px;
	text-align: center;
}
select {
	width: 150px;
	height: 30px;
}
#container {
	width: 960px;
	margin: 0 auto;
}
#product_tb{
	border: none;
}
#product_tb tr td{
	font-weight: bold;
	color: #888;
}
#product_tb tbody tr:nth-child(2n){
	background-color: #47a3d2;
}
#product_tb tbody tr:nth-child(2n) td{
	color: #fff;
}
#product_tb tbody tr:nth-child(2n) td a{
	color: #fff;
}
#product_tb tbody tr{
	line-height: 30px;
}
#product_tb thead{
	border-bottom: solid #ccc 1px;
}
#thead tr td{
	color : #000;
}
.likeBtn{
	color: red;
}
#product_tb tbody tr:nth-child(2n) .likeBtn{
	color: red;
}
.likeBtn:hover{
	color: #47a3d2;
	text-decoration: none;
}
#product_tb tbody tr:nth-child(2n) .likeBtn:hover{
	color: #fff;
}
#product_tb img{
	height : 100px;
}
#product_tb tbody tr td:nth-child(1) {
	border-right: 2px #ccc solid;
}
#product_tb tbody tr:hover{
	background-color: #ddd;
}
#product_tb tbody tr:nth-child(2n):hover{
	background-color: #337ab7;
}
.imgSize {
    width: auto; height: auto;
    max-width: 225px;
    max-height: 225px;
    border-radius: 10px;
    word-break: keep-all;
}
.SearchBox{
	text-align: center;

}

</style>
<div id="container">
	<hr>
	<h1>일반 공연장</h1>
	<hr>
	
	<form class="stageReservation" action="${initParam.rootPath}/selectAllStage.do">
		<input type="text" class="SearchBox" name="nameSearch" id="nameSearch" placeholder="공연장 이름으로 검색">
		<input type="text" class="SearchBox" name="locationSearch" id="locationSearch" placeholder="장소명으로 검색">
		<input type="date" class="SearchBox" name="startDate" id="startDate">
		<input type="date" class="SearchBox" name="endDate" id="endDate">
		<input type="text" class="SearchBox" name="idSearch" id="idSearch" placeholder="공급자로 검색">
		<button type="submit" name="search" class="submit">검색</button>
		<sec:csrfInput/>
	</form>
	<hr>
	<div class="row">
		<c:forEach items="${requestScope.map.list }" var="item" varStatus="num">
			<div class="thumbnail col-sm-4" style="cursor: pointer;">
				<img class="imgSize" src="${initParam.rootPath }/stageImage/${item.stageImage[0].stageImageLocation }.jpg" onerror='this.src="${initParam.rootPath }/supplierImage/no-image.png"' onclick="goDetail('${initParam.rootPath }', ${item.stageNo})">
				<div class="caption" >
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.stageNo})">${item.stageNo}.  ${item.stageName }</p>
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.stageNo})">
					공연 날짜 : <fmt:formatDate value="${item.stageRentalDate}" pattern="yyyy년 MM월 dd일"/>&nbsp;&nbsp;
					<fmt:formatDate value="${item.stageStartTime}" pattern="HH:mm"/>~~<fmt:formatDate value="${item.stageEndTime }" pattern="HH:mm"/></p>
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.stageNo})">${item.stageLocation }</p>
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.stageNo})">가격 : ${item.stageCost}, 면적 : ${item.stageArea}</p>
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.stageNo})">등록된 시간 : <fmt:formatDate value="${item.stageRegTime }" pattern="yyyy-MM-dd HH:mm"/></p>
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.stageNo})">작성자 : ${item.stageSellerId }</p>
				</div>
			</div>
		</c:forEach>
	</div>
	 
	<div style="width: 100%;">
		<sec:authorize access="hasAnyRole('ROLE_MEMBER','ROLE_ARTIST','ROLE_PRODUCER')">
			<a href="${initParam.rootPath}/stage/stageRegisterView.do" class="btn btn-default" style="float: right;">작성</a>
		</sec:authorize>
	</div>

	<div style="text-align: center; width: 100%;">
			<ul class="pagination">
				<%-- 첫페이지로 이동 --%>
				<li>
					<a href="${initParam.rootPath }/selectAllStage.do?page=1&locationSearch=${requestScope.map.stageLocation}&nameSearch=${requestScope.map.stageName}&stageDate=${requestScope.map.startDate}&endDate=${requestScope.map.endDate}&stageSellerId=${requestScope.map.startSellerId}">&lt;&lt;</a>
				</li>
				<%--
					이전 페이지 그룹 처리
					만약에 이전 페이지 그룹이 있으면 링크처리하고 없으면 화살표만 나오도록 처리
				 --%>
				<c:choose>
					<c:when test="${requestScope.map.pageBean.previousPageGroup }">
						<li>
							<a href="${initParam.rootPath }/selectAllStage.do?page=&locationSearch=${requestScope.map.stageLocation}&nameSearch=${requestScope.map.stageName}&stageDate=${requestScope.map.startDate}&endDate=${requestScope.map.endDate}&stageSellerId=${requestScope.map.startSellerId}">◀</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="disabled">
				 			<a href="#">◀</a>
				 		</li>
				 	</c:otherwise>
				</c:choose>
				<%--
				 	현재 page가 속한 page 그룹내의 페이지들 링크
				 	현재 pageGroup의 시작 page ~ 끝 page
				 	- 만약에 p가 현재페이지면 링크처리를 하지 않고 p가 현재 페이지가 아니라면 링크 처리.
				  --%>
				<c:forEach begin="${requestScope.map.pageBean.beginPage }"
					end="${requestScope.map.pageBean.endPage }" var="num">
					<c:choose>
						<c:when test="${num == requestScope.map.pageBean.page }">
							<li class="active">
				  				<a href="#">${num }</a>
				  			</li>
				  		</c:when>
						<c:otherwise>
							<li>
								<a href="${initParam.rootPath }/selectAllStage.do?page=${num}&locationSearch=${requestScope.map.stageLocation}&nameSearch=${requestScope.map.stageName}&stageDate=${requestScope.map.startDate}&endDate=${requestScope.map.endDate}&stageSellerId=${requestScope.map.startSellerId}">${num }</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<%--
				  	다음페이지 그룹으로 이동
				  	만약에 다음페이지 그룹이 있으면 링크 처리 없으면 화살표만 나오도록 처리
				  --%>
				<c:choose>
					<c:when test="${requestScope.map.pageBean.nextPageGroup }">
						<li>
							<a href="${initParam.rootPath }/selectAllStage.do?page=${requestScope.map.pageBean.endPage + 1}&locationSearch=${requestScope.map.stageLocation}&nameSearch=${requestScope.map.stageName}&stageDate=${requestScope.map.startDate}&endDate=${requestScope.map.endDate}&stageSellerId=${requestScope.map.startSellerId}">▶</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="disabled">
				  			<a href="#">▶</a>
				  		</li>
				  	</c:otherwise>
				</c:choose>
				<%-- 마지막 페이지로 이동 --%>
				<li>
					<a href="${initParam.rootPath }/selectAllStage.do?page=${requestScope.map.pageBean.totalPage}&locationSearch=${requestScope.map.stageLocation}&nameSearch=${requestScope.map.stageName}&stageDate=${requestScope.map.startDate}&endDate=${requestScope.map.endDate}&stageSellerId=${requestScope.map.startSellerId}">&gt;&gt;</a>
				</li>
			</ul>
		</div>
	</div>