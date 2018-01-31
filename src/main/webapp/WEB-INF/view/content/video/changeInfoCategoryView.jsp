<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${initParam.rootPath }/videoChangeInfoView.do">
	<input type="number" name="videoNo">
	<input type="hidden" name="videoCategory" value="artist">
	<button>Artist</button>
</form>

<form action="${initParam.rootPath }/videoChangeInfoView.do">
	<input type="number" name="videoNo">
	<input type="hidden" name="videoCategory" value="performance">
	<button>Performance</button>
</form>

<form action="${initParam.rootPath }/videoChangeInfoView.do">
	<input type="number" name="videoNo">
	<input type="hidden" name="videoCategory" value="practice">
	<button>Practice</button>
</form>

</body>
</html>