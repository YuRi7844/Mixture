<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${initParam.rootPath}/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function goPerformanceDetail(id){
	document.getElementById(id).submit();
}

function goVideoDetail(id){
	document.getElementById(id).submit();
}

</script>

<style>
img{
	max-width:100%;
	max-heigth:100%;
	cursor:pointer;
}
</style>

<!-- like video -->
<div class="col-sm-12">
	<h2>공연영상</h2>
	<div class="thumbnail col-sm-12" style="float: left;">
		<c:forEach items="${requestScope.list }" var="video">
			<div class="caption text-center col-sm-3" style="float: left;">
				<img src="https://img.youtube.com/vi/${video.videoLink }/hqdefault.jpg"
				onclick="goVideoDetail(${video.videoNo });" >
				<span>${video.videoTitle }</span>
				<form action="${initParam.rootPath }/readVideoByVideoNo.do" id="${video.videoNo }">
					<input type="hidden" name="videoNo" value="${video.videoNo }">
				</form>
			</div>
		</c:forEach>
	</div>
</div>