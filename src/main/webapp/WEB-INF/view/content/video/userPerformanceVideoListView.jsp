<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function goDetail(root, no){
	document.location.href= root+'/readVideoByVideoNo.do?videoNo='+no;
}
/* $(document).ready(function(){
$("tr").on("click",function(){
document.getAttribute('value').submit();
});
}); */
</script>
<style type="text/css">
td:hover{
	cursor:pointer;
}

</style>

<div class="row">
	<form class="form-inline col-sm-6" action="${initParam.rootPath }/viewPerformanceVideoListByTitle.do">
		<sec:csrfInput/>
		<select class="form-control col-sm-1" name="filter">
			<option value="title">제목</option>
			<option value="userId">작성자</option>
			<option value="artist">아티스트명</option>
			<option value="content">내용</option>
		</select>
		<input class="form-control col-sm-2" type="text" placeholder="검색" name="search">
		<input type="hidden" id ="videoCategory" name="category" value="performance" class="form-control">
		<button class="btn btn-default" type="submit">검색</button>
	</form>
	<sec:authorize access="isAuthenticated()">
		<form class="col-sm-2 col-sm-offset-4" action="${initParam.rootPath }/member/selectPerformanceVideoCategory.do" method="post">
			<sec:csrfInput/>
			<button class="btn btn-default">글쓰기</button>
		</form>
	</sec:authorize>	
</div>
<br><hr><br>
<div>
	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th class="col-sm-1 text-center">동영상번호</th>
				<th class="col-sm-2 text-center">제목</th>
				<th class="col-sm-2 text-center">공연사진</th>
				<th class="col-sm-2 text-center">장소</th>
				<th class="col-sm-3 text-center">날짜</th>
				<th class="col-sm-1 text-center">작성자</th>
				<th class="col-sm-1 text-center">등록시간</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list }" var="video">
				<tr onclick="goDetail('${initParam.rootPath }', '${video.videoNo }');">
					<td class="text-center">${video.videoNo }</td>
					<td class="text-center">${video.videoTitle }</td>
					<td>
						<img class="center-block" style="width:100px; height:100px;" src="https://img.youtube.com/vi/${video.videoLink }/hqdefault.jpg">
					</td>
					<td class="text-center">${video.videoLocation }</td>
					<td class="text-center">
						<fmt:formatDate value="${video.videoDate }" pattern="yyyy-MM-dd"/> 
					</td>
					<td class="text-center">${video.videoUserId }</td>
					<td class="text-center">
						<fmt:formatDate value="${video.videoRegTime }" pattern="yyyy-MM-dd HH:mm:ss"/> 
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>