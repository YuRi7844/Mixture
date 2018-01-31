<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<link rel="stylesheet" href="${initParam.rootPath }/resource/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/bootstrap/css/bootstrap-theme.min.css">
<script src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script src="${initParam.rootPath }/resource/bootstrap/js/bootstrap.min.js"></script>
<script>

</script>
<style type="text/css">
body{
	background-color:#F8E6E0;
}
.container{
	margin-top:40px;
	padding:0;
	background-color:WHITE;
}
.header-top{
	background-color:#9F81F7;
}
.footer-container{
	background-color:#9F81F7;
}
</style>
</head>
<body>
	<!-- 헤더 -->
	<header class="header-container">
		<div class="header-top">
			<tiles:insertAttribute name="header"/>		
		</div>
		<nav>
			<tiles:insertAttribute name="menu"/>
		</nav>
	</header>
	<!-- 메인 -->
	<div class="container">
		<section class="profile">
			<tiles:insertAttribute name="profile"/>
		</section>
		<section class="content">
			<tiles:insertAttribute name="content"/>
		</section>
	</div>
	<!-- 푸터 -->
	<footer class="footer-container">
		<tiles:insertAttribute name="footer"/>
	</footer>
</body>
</html>