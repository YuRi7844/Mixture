<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
	*{
		text-decoration: none;
		list-style: none;
	}
	.login_box{
		float: right;
		margin-top: 85px;
	}
	.login_box ul{
		float: rigth;
	}
	.login_box li a{
		font-size: 18px;
		font-weight : bold;
		color: #888;
	}
	.login_box li a:hover{
		text-decoration: none;
		color : red;
	}
	.login_box ul li{  
		float: left;
		text-align: center;	
	}
</style>

<div class="header_menu col-sm-12">
	<div class="header_logo col-sm-3">
		<a href="${initParam.rootPath }/index.do">
			<img alt="logo" src="${initParam.rootPath }/etc_Image/main_logo.png" class="col-sm-12 img-responsive img-rounded">
		</a>
	</div>
	
	<div class="login_box col-sm-3">
		<%--인증 안된(로그인 안한) 사용자 메뉴 : 인증되면 안보여야 하는 메뉴 --%>
		<sec:authorize access="!isAuthenticated()">
			<ul class="col-sm-12">
				<li class="col-sm-6"><a href="${initParam.rootPath }/login_form.do">로그인</a></li>
				<li class="col-sm-6"><a href="${initParam.rootPath }/join_member_form.do">회원가입</a></li>
			</ul>
		</sec:authorize>
		<%--인증된(로그인한) 사용자 메뉴 : 인증 안된상태에서 안보여야 하는 메뉴 --%>
		<sec:authorize access="isAuthenticated()">
			<ul class="col-sm-12">
				<li class="col-sm-3"><a id="logout" style="cursor: pointer;">로그아웃</a>
				<li class="col-sm-3"><a href="${initParam.rootPath }/member/myPageMain.do">마이페이지</a></li>
				<li class="col-sm-3"><a href="${initParam.rootPath }/member/myPageView.do">new마이페이지</a>
				<li class="col-sm-3"><a href="${initParam.rootPath }/beforeIndex.do">예전 index.jsp</a>
			</ul>
		</sec:authorize>
		
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<ul class="col-sm-12">
				<li class="col-sm-6"><a href="${initParam.rootPath }/admin/adminPage.do">관리자페이지</a></li>
				<li class="col-sm-6"><a href="${initParam.rootPath }/admin/registAdmin.do">관리자 등록</a></li>
			</ul>
		</sec:authorize>
	</div>
</div>

<!-- 
	로그아웃전송폼
	+ 로그인/로그아웃은 반드시 POST방식으로 요청하며 csrf 토큰을 보내야 한다.
	+ 로그아웃은 단순 링크이므로 아래와 같이 hidden 폼을 만들고 클릭시 Javascript에서 form을 submit하여 처리한다.
		- style="display:none" ~> 사용자에게 보이지 않게! ~> hidden form 으로 만든다.
 -->
<form id="logoutForm" action="${initParam.rootPath }/logout.do" method="post" style="display:none">
    <sec:csrfInput/>	
</form>

<script>
$(document).ready(function(){
	$("#logout").on("click", function(){
		$("#logoutForm").submit();
	});
});
</script>