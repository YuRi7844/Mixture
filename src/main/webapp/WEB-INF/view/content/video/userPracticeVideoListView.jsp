<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function goDetail(root, no){
			document.location.href= root+'/readVideoByVideoNo.do?videoNo='+no
	}
/* $(document).ready(function(){
	$("tr").on("click",function(){
		document.getAttribute('value').submit();
	});
}); */

</script>
<style type="text/css">
th{
	text-align:center;
}

td {
	cursor:pointer;
	text-align: center;
}

</style>

<div class="row">
	<form class="form-inline col-sm-6" action="${initParam.rootPath }/viewPracticeVideoListByTitle.do">
		<sec:csrfInput/>
		<select class="form-control" name="filter">
			<option value="title">제목</option>
			<option value="userId">작성자</option>
			<option value="artist">아티스트명</option>
			<option value="content">내용</option>
		</select>
		<input class="form-control col-sm-2" type="text" placeholder="검색" name="search">
		<input type="hidden" id ="videoCategory" name="category" value="practice" class="form-control">
		<button class="btn btn-default" type="submit">검색</button>
	</form>
	<sec:authorize access="isAuthenticated()">
		<form class="col-sm-2 col-sm-offset-4" action="${initParam.rootPath }/member/selectMemberVideoCategory.do" method="post">
			<sec:csrfInput/>
			<button class="btn btn-default">글쓰기</button>
		</form>
	</sec:authorize>	
</div>

<br>
<hr>
<br>

<div>
	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th>동영상번호</th>
				<th>제목</th>
				<th>이미지</th>
				<th>내용</th>
				<th>날짜</th>
				<th>작성자</th>
				<th>등록시간</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list }" var="video">
				<tr onclick="goDetail('${initParam.rootPath }','${video.videoNo }')">
					<td>${video.videoNo }</td>
					<td>${video.videoTitle }</td>
					<td>
						<img class="center-block" style="width:100px; height:100px" src="https://img.youtube.com/vi/${video.videoLink }/hqdefault.jpg">
					</td>
					<td>${video.videoContent }</td>
					<td>
						<fmt:formatDate value="${video.videoDate }" pattern="yyyy-MM-dd"/>
					</td>
					<td>${video.videoUserId }</td>
					<td>
						<fmt:formatDate value="${video.videoRegTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

	
	
	