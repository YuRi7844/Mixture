<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<form action="${initParam.rootPath}/login.do" method="post">
	<sec:csrfInput/>
	<br>
	<div class="form-group">
		<label for="inputId">ID</label>
		<input type="text" id="inputId" name="userId" autofocus class="form-control">
	</div>
		
	<div class="form-group">
		<label for="inputPassword">Password</label>
		<input type="password" name="userPassword" id="inputPassword" class="form-control">
	</div>
	
	<button type="submit" class="btn-primary">로그인</button>
</form>
<br><br>

<span><a href="${initParam.rootPath }/findId.do">ID 찾기</a></span>
<br>
<span><a href="${initParam.rootPath }/findPassword.do">비밀번호 찾기</a></span>
