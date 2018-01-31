<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h2>정말 탈퇴하시겠습니까?</h2>
<form action="${initParam.rootPath }/member/dropUser.do" method="post">
	<sec:csrfInput/>
	<button class="btn btn-default">확인</button>
</form>

<a class="btn btn-default" href="${initParam.rootPath }/member/myPageMain.do" role="button">취소</a>
