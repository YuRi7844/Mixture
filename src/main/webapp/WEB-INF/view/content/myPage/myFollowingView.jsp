<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function goDetail(){
	document.getElementById('detailForm').submit();
}
</script>

<h2>내 팔로우 아티스트 목록</h2>

<c:forEach items="${requestScope.list }" var="artist">
	<div class="thumbnail col-sm-4" onclick=goDetail(); style="cursor:pointer;">
		<img class="img-circle" src="${initParam.rootPath }/artistImage/${artist.artistImage }" onerror='this.src="${initParam.rootPath }/artistImage/no-image.png"'>
		<div class="caption">
			<h3>${artist.artistName }</h3>
			<h3>${artist.artistSns }</h3>		
		</div>
	</div>
	<form action="${initParam.rootPath }/artistInfo.do" method="post" id="detailForm">
		<sec:csrfInput/>
		<input type="hidden" name="artistId" value="${artist.artistId }">
	</form>
</c:forEach>