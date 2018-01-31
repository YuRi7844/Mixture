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

function goStageDetail(id){
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

<div class="col-sm-12">
	<h2>공연장</h2>
	<div class="thumbnail col-sm-12" style="float: left;">
		<c:forEach items="${requestScope.list }" var="stage">
			<div class="caption text-center col-sm-3" style="float: left;">
				<img src="${initParam.rootPath }/stageImage/${stage.stageImage[0].stageImageLocation }.jpg" 
				onerror='this.src="${initParam.rootPath }/stageImage/no-image.png"'
				onclick="goStageDetail(${stage.stageNo }65);" >
				<span>${stage.stageName }</span>
				<form action="${initParam.rootPath }/stageDetail.do" id="${stage.stageNo }65">
					<input type="hidden" name="stageNo" value="${stage.stageNo }">
				</form>
			</div>
		</c:forEach>
	</div>
</div>