<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	등록성공!
	
	${requestScope.artist }
	
	<img src="${initParam.rootPath }/artistImage/${requestScope.artist.artistImage }">
</body>
</html>