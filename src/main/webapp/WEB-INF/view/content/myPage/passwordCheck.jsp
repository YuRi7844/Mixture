<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
${requestScope.errorMsg}
<div>
	<h2>비밀번호 확인</h2>
	<form class="form-inline" action="${initParam.rootPath }/checkUserPassword.do" method="post">
		<sec:csrfInput/>
		<div class="form-group">
			<input type="hidden" name="category" value="${requestScope.category }">
			<input class="form-control" type="password" name="password" required="required">
			<input type="hidden" name="userId" value="<sec:authentication property='principal.userId'/>">
			<button class="btn btn-default" type="submit">비밀번호확인</button>
		</div>
	</form>
</div>
