<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script>
	function goDetail(root, no){
		document.location.href= root+'/artistPerformanceDetailView.do?performanceNo='+no
	}
	
	$(document).ready(function(){
		$(".likeBtn").on("click",function(){  
			$.ajax({
				"type":"POST",
				"url":"${initParam.rootPath}/performanceLike.do",
				"context" : this,
				"data":{
					"no":$(this).parent().parent().parent().children("div").children("p:eq(0)").text(),
					'${_csrf.parameterName}':'${_csrf.token}'
				},
				"dataType":"text",
				"success":function(count){
					$(this).html("<span class='glyphicon glyphicon-heart'></span>"+count);
				},
				"error":function(a,b,c){
					alert(a);
					alert(b);
					alert(c);
				}
			});
		});
		$("#category").change(function(){
			var val = $(this).val();
			if(val=='date'){
				$(".search").css('display', 'none');
				$(".date_search").css('display', 'block');
			}else{
				$(".search").css('display', 'block');
				$(".date_search").css('display', 'none');
			}
		});
	});
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

</style>

	<div id="container">
		<h1>VIEW - 아티스트공연 정보 리스트</h1>
		<hr>
		<div class="row">
		<c:forEach items="${requestScope.map.list }" var="item" varStatus="num">
			<div class="thumbnail col-sm-4" style="cursor:pointer;">
				<img class="imgSize" src="${initParam.rootPath }/performanceImage/${item.performanceImage }" onerror='this.src="${initParam.rootPath }/supplierImage/no-image.png"' onclick="goDetail('${initParam.rootPath }', ${item.performanceNo})">
				<div class="caption" >
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.performanceNo})">Title : ${item.performanceTitle }</p>
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.performanceNo})">${item.performanceLocation }</p>
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.performanceNo})">
					공연 날짜 : <fmt:formatDate value="${item.performanceDate}" pattern="yy-MM-dd hh:mm"/>
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.performanceNo})">작성자 : ${item.performanceUserId }</p>
					<p class="text-center" onclick="goDetail('${initParam.rootPath }', ${item.performanceNo})">등록된 시간 : <fmt:formatDate value="${item.performanceRegTime }" pattern="HH:mm"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 조회수 : ${item.performanceHits }</p>
					 
				</div>
				<div>
					<p class="text-center"><a class="likeBtn"><span class="glyphicon glyphicon-heart"></span>${item.likeCount }</a></p>
				</div>
			</div>
		</c:forEach>
	</div>
		<br>
		<form action="${initParam.rootPath }/selectArtistPerformance.do">
			<select name="category" id="category" style="float: left;">
				<option value="title">제목</option>
				<option value="user">작성자</option>
				<option value="location">공연장소</option>
				<option value="name">공연이름</option>
				<option value="content">내용</option>
				<option value="date" id="option">공연날짜</option>
			</select> 
			<input type="text" placeholder="검색" name="search" class="search" style="float: left; margin-left: 5px;">
			<div class="date_search" style="display:none; float: left; margin-left: 5px;">
			시작일 : <input type="date" name="sDate">
			종료일 : <input type="date" name="eDate">
			</div>
			<button type="submit">검색</button>
			<button type="button" onclick="location.href='${initParam.rootPath}/artist/performanceRegisterView.do'">글쓰기</button>
		</form>
		<%-- 페이징 처리 --%>
		<p/>
		<div style="text-align: center; width: 100%;">
			<ul class="pagination">
				<%-- 첫페이지로 이동 --%>
				<li>
					<a href="${initParam.rootPath }/selectArtistPerformance.do?page=1&category=${requestScope.map.category}&search=${requestScope.map.search}">&lt;&lt;</a>
				</li>
				<%--
					이전 페이지 그룹 처리
					만약에 이전 페이지 그룹이 있으면 링크처리하고 없으면 화살표만 나오도록 처리
				 --%>
				<c:choose>
					<c:when test="${requestScope.map.pageBean.previousPageGroup }">
						<li>
							<a href="${initParam.rootPath }/selectArtistPerformance.do?page=${requestScope.map.pageBean.beginPage - 1}&category=${requestScope.map.category}&search=${requestScope.map.search}">◀</a>
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
								<a href="${initParam.rootPath }/selectArtistPerformance.do?page=${num}&category=${requestScope.map.category}&search=${requestScope.map.search}">${num }</a>
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
							<a href="${initParam.rootPath }/selectArtistPerformance.do?page=${requestScope.map.pageBean.endPage + 1}&category=${requestScope.map.category}&search=${requestScope.map.search}">▶</a>
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
					<a href="${initParam.rootPath }/selectArtistPerformance.do?page=${requestScope.map.pageBean.totalPage}&category=${requestScope.map.category}&search=${requestScope.map.search}">&gt;&gt;</a>
				</li>
			</ul>
		</div>
	</div>
