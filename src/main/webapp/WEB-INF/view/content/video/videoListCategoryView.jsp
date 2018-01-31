<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h2>카테고리선택</h2>
<form action="${initParam.rootPath }/videoListCategory.do" method="post">
	<sec:csrfInput/>
	<input type="hidden" name="category" value="artist"/>
	<button type="submit">아티스트 홍보영상</button>
</form>
<p>
<form action="${initParam.rootPath }/videoListCategory.do" method="post">
	<sec:csrfInput/>
	<input type="hidden" name="category" value="performance"/>
	<button type="submit">공연영상</button>
</form>
<p>
<form action="${initParam.rootPath }/videoListCategory.do" method="post">
	<sec:csrfInput/>
	<input type="hidden" name="category" value="practice"/>
	<button type="submit">연습영상</button>
</form>
<p>
