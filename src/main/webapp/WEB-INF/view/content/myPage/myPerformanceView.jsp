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

<!-- like 공연정보 -->
<div class="col-sm-12">
	<h2>공연정보</h2>
	<div class="thumbnail col-sm-12" style="float: left;">
		<c:forEach items="${requestScope.list }" var="performance">
			<div class="caption text-center col-sm-3" style="float: left;">
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