<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${initParam.rootPath }/resource/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${initParam.rootPath }/resource/bootstrap/css/bootstrap-theme.min.css">
<script src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script src="${initParam.rootPath }/resource/bootstrap/js/bootstrap.min.js"></script>

<style type="text/css">

</style>

</head>
<body>
	<header>
		<div class="header-container">
			<div class="header-top">
				<tiles:insertAttribute name="header"/>
			</div>
			<%-- 메뉴 --%>
			<nav>
				<tiles:insertAttribute name="menu"/>
			</nav>
		</div>
	</header>
	<hr>
	
	<div class="container">
		<section class="sideMenu col-sm-3">
			<tiles:insertAttribute name="sideMenu" />
		</section>
		<section class="content col-sm-9">
			<tiles:insertAttribute name="content" />
		</section>
		
	</div>
	<footer>
		<div class="footer-container">
			<tiles:insertAttribute name="footer"/>
		</div>
	</footer>
</body>
</html>