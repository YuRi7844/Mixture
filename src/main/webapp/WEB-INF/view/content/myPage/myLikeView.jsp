<%@ page contentType="text/html;charset=UTF-8"%>
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

<!-- like 공연정보 -->
<div class="col-sm-6">
	<h2>공연정보</h2>
	<div class="thumbnail col-sm-6">
		<c:forEach items="${requestScope.map.performanceList }" var="performance">
			<div class="caption text-center col-xm-6">
				<img src="${initParam.rootPath }/performanceImage/${performance.performanceImage }" 
				onerror='this.src="${initParam.rootPath }/performanceImage/no-image.png"'
				onclick="goPerformanceDetail(${performance.performanceNo }65);" >
				<span>${performance.performanceTitle }</span>
				<form action="${initParam.rootPath }/performanceDetailView.do" id="${performance.performanceNo }65">
					<input type="hidden" name="performanceNo" value="${performance.performanceNo }">
				</form>
			</div>
		</c:forEach>
	</div>
</div>
<!-- like video -->
<div class="col-sm-6">
	<h2>공연영상</h2>
	<div class="thumbnail col-sm-6">
		<c:forEach items="${requestScope.map.videoList }" var="video">
			<div class="caption text-center col-xm-6">
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