<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Success!

<form action="${initParam.rootPath }/member/myPageMain.do">
	<sec:csrfInput/>
	<button>마이페이지</button>
</form>
</body>
</html>