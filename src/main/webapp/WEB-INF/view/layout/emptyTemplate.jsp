<%@ page contentType="text/html;charset=utf-8"%>
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

<script>

</script>

<style type="text/css">

</style>
<title>Insert title here</title>
</head>
<body>
	<header>
		<div class="header-container">
			<%-- 메뉴 --%>
			<nav>
				<tiles:insertAttribute name="menu"/>
			</nav>
		</div>
	</header>
	<div class="container">
		<section class="content">
			<tiles:insertAttribute name="content" />
		</section>
		
	</div>
</body>
</html>