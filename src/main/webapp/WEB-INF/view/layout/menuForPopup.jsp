<%@ page contentType="text/html;charset=UTF-8" %>
<style type="text/css">

#topMenu {
	height: 2em;
}

#topMenu ul {
	list-style-type: none;
	margin: 0px;
	padding: 00px;
}

#topMenu ul li {
	color: white;
	background-color: #BBBBCC;
	float: left;
	line-height: 3.5em;
	vertical-align: middle;
	text-align: center;
	position: relative;
	margin-right: 1px;
}

.menuLink, .submenuLink {
	text-decoration: none;
	display: block;
	width: 15em;
	font-size: 24px;
	font-weight: bold;
	font-family: "Trebuchet MS", Dotum;
}

.menuLink {
	color: white;
}

.topMenuLi:hover .menuLink {
	color: white;
	background-color: #FFA7A7;
}

.topMenuLi:hover .submenu {
	height: 93px;
}


</style>
<div id="top">
	<div id="header">
		<nav id="topMenu">
			<ul>
				<li class="topMenuLi" style="padding: 0;"><a class="menuLink" href="${initParam.rootPath}/searchStageByAddress.do">주소로 검색</a></li>
				<li class="topMenuLi" style="padding: 0;"><a class="menuLink" href="${initParam.rootPath}/searchStageByKeyword.do">장소명으로 검색</a></li>
				
			</ul>
		</nav>
	</div>
</div>
<hr>

