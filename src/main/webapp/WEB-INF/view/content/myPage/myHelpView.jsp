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
function goHelpDetail(id){
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
	<h2>고객센터</h2>
	<div class="thumbnail col-sm-12" style="float: left;">
		<c:forEach items="${requestScope.list }" var="help">
			<div class="caption text-center col-sm-3" style="float: left;">
				<img src="${initParam.rootPath }/helpImage/${help.helpImage }" 
				onerror='this.src="${initParam.rootPath }/helpImage/no-image.png"'
				onclick="goHelpDetail(${help.helpNum });" >
				<span>${help.helpTitle }</span>
				<form action="${initParam.rootPath }/helpDetail.do" id="${help.helpNum }">
					<input type="hidden" name="helpNum" value="${help.helpNum }">
				</form>
			</div>
		</c:forEach>
	</div>
</div>