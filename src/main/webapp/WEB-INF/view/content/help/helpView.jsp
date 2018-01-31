<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script>
	function goDetail(root, no){
		document.location.href= root+'/helpDetail.do?helpNum='+no;
	}
</script>

<h1>고객센터</h1>
<style type="text/css">

table {
	width: 100%;
	border-collapse: collapse;
}

th {
	padding: 5px;
	text-align: center;
}

td {
	padding: 5px;
	text-align: center;
}
td.subject{
	text-align: left;
}

select {
	width: 150px;
	height: 30px;
}

#ct_cs{
	width:960px;
	margin: auto;
}
</style>
<div id="ct_cs">
	<div class="tb_box">
		<table border="1" class="tb_center">
			<colgroup>
				<col style="width:75px"><col style="width:88px"><col><col style="width:110px">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">분류</th>
					<th scope="col" style="width: 60%;">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.map.list }" var="item">
				<tr onclick="goDetail('${initParam.rootPath}', ${item.helpNum })">
					<td class="num">${item.helpNum }</td>
					<td class="sort">${item.helpCategory }</td>
					<td class="subject">${item.helpTitle }</td>
					<td class="userId">${item.helpUserId }</td>
					<td class="date"><fmt:formatDate value="${item.helpRegTime }" pattern="yyyy-MM-dd"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<button onclick="location.href='${initParam.rootPath}/helpRegisterView.do'">글쓰기</button>
</div>
<div class="uio_base_srch">
	<form id="schform" name="schform" action="${initParam.rootPath }/selectHelp.do">
		<fieldset>
		<legend>검색</legend>
		<select title="검색 구분" name="category" style="width:70px">
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="user">작성자</option>
		</select>
		<input type="text" id="keystr" name="search" title="검색어 입력" value="">
		<i class="btn_search"><button type="submit">검색</button></i>
		
		</fieldset>
	</form>
	<%-- 페이징 --%>
	<p/>
	<div style="text-align: center; width: 100%;">
		<ul class="pagination">
			<%-- 첫 페이지 --%>
			<li>
				<a href="${initParam.rootPath }/selectHelp.do?page=1&category=${requestScope.map.category}&search=${requestScope.map.search}">&lt;&lt;</a>
			</li>
			<%-- 이전 페이지 그룹 --%>
			<c:choose>
				<c:when test="${requestScope.map.pageBean.previousPageGroup }">
					<li>
						<a href="${initParam.rootPath }/selectHelp.do?page=${requestScope.map.pageBean.beginPage - 1}&category=${requestScope.map.category}&search=${requestScope.map.search}">◀</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="disabled">
						<a href="#">◀</a>
					</li>
				</c:otherwise>
			</c:choose>
			<%-- 현재 페이지가 속한 페이지 그룹내의 페이지들 링크 --%>
			<c:forEach begin="${requestScope.map.pageBean.beginPage }" end="${requestScope.map.pageBean.endPage }" var="num">
				<c:choose>
					<c:when test="${num == requestScope.map.pageBean.page }">
						<li class="active">
							<a href="#">${num }</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="${initParam.rootPath }/selectHelp.do?page=${num}&category=${requestScope.map.category}&search=${requestScope.map.search}">${num }</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<%-- 다음 페이지 그룹 --%>
			<c:choose>
				<c:when test="${requestScope.map.pageBean.nextPageGroup }">
					<li>
						<a href="${initParam.rootPath }/selectHelp.do?page=${requestScope.map.pageBean.endPage + 1}&category=${requestScope.map.category}&search=${requestScope.map.search}">▶</a>		
					</li>
				</c:when>
				<c:otherwise>
					<li class="disabled">
						<a href="#">▶</a>
					</li>
				</c:otherwise>
			</c:choose>
			<%-- 마지막 페이지 --%>
			<li>
				<a href="${initParam.rootPath }/selectHelp.do?page=${requestScope.map.pageBean.totalPage}&category=${requestScope.map.category}&search=${requestScope.map.search}">&gt;&gt;</a>
			</li>
		</ul>
	</div>
</div>