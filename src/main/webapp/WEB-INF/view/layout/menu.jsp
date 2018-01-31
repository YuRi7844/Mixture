<%@ page contentType="text/html;charset=UTF-8" %>
<%--Spring Security 커스텀태그 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- 시연용 메뉴CSS ***********************************************************  -->
<style type="text/css">

.main_menu {
	position: relative;
	z-index: 1;
	margin-top: 10px;
	height: 49.9px;
}

.main_menu>ul>li {
	float: left;
	transition: 0.5s;
}

.main_menu>ul>li>a {
	font-size: 16px;
}

.main_menu>ul>li:hover {
	background-color: #F9A2EB;
}

.main_menu>ul>li:hover>a {
	color: #fff;
}

.main_menu a {
	font-weight: bold;
	color: #000;
	text-decoration: none;
	width: 100%;
	height: 100%;
	transition: 0.5s;
}

.main_menu li {
	text-align: center;
	line-height: 50px;
	height: 50px;
}

#sub_menu {
	display: none;
}

#sub_menu li {
	text-align: center;
	background-color: GhostWhite;
	border-bottom: 1px solid #999;
	transition: 0.5s;
	position: relative;
	z-index: 2;
}
#sub_menu li:hover {
	background-color: #eee;
}
#sub_menu li a{
	color : #aaa;
}
#sub_menu li:hover > a {
	color: #000;
}

</style>
<!-- 시연용 메뉴CSS ***********************************************************  -->



<%--
   <sec:authorize> : 태그 하위 내용을 볼 수 있는 사용자인지 인증/권한 체크
    속성 -  access="Spring Security EL"  : 볼수 있는 권한 설정											
 --%>

<!--원래 UL CSS <ul class="nav nav-stacked"> -->
	<!-- <li><a href="${initParam.rootPath }/youtubeTest.do">유튜브테스트</a></li> -->
<%--인증 관련 없는 메뉴 (로그인 여부와 관련없이 나올 메뉴) --%>
	<%-- 임시용 --%>

   <%--인증된(로그인한) 사용자 메뉴 : 인증 안된상태에서 안보여야 하는 메뉴 --%>
   
   
   <!-- <li><a href="${initParam.rootPath }/youtubeTest.do">유튜브테스트</a></li> -->
   <%--인증 관련 없는 메뉴 (로그인 여부와 관련없이 나올 메뉴) --%>
   <div>
   <div class="main_menu col-sm-12 text-center" style="height: 80px;">
      <ul class="col-sm-12">
         <li class="col-sm-2 col-sm-offset-2 col-xs-12" style="padding: 0;"><a href="#">공연정보</a>
            <ul id="sub_menu" class="col-sm-12" style="padding: 0;">
               <li class="col-sm-12" style="padding: 0;"><a href="${initParam.rootPath }/selectArtistPerformance.do">아티스트공연정보</a></li>
               <li class="col-sm-12" style="padding: 0;"><a href="${initParam.rootPath }/selectPerformance.do">일반공연정보</a></li>
            </ul>
         </li>
         <li class="col-sm-2 col-xs-12" style="padding: 0;"><a href="#">공연장대관</a>
         	<ul id="sub_menu" class="col-sm-12" style="padding: 0;">
         		<li class="col-sm-12" style="padding: 0;"><a href="${initParam.rootPath }/selectPremiumStage.do?page=1" id="goPremiumStage">프리미엄공연장</a></li>
         		<li class="col-sm-12" style="padding: 0;"><a href="${initParam.rootPath }/selectAllStage.do" id="goStage">일반공연장</a></li>
         	</ul>
         </li>
         <li class="col-sm-2 col-xs-12" style="padding: 0;"><a href="#">공연영상</a>
            <ul id="sub_menu" class="col-sm-12" style="padding: 0;">
               <li class="col-sm-12" style="padding: 0;"><a href="#" id="goArtistVideo">홍보영상</a></li>
               <li class="col-sm-12" style="padding: 0;"><a href="#" id="goPerformanceVideo">공연영상</a></li>
               <li class="col-sm-12" style="padding: 0;"><a href="#" id="goPracticeVideo">연습영상</a></li>
            </ul>
         </li>
         <li class="col-sm-2 col-xs-12" style="padding: 0;"><a href="${initParam.rootPath }/selectHelp.do">고객센터</a></li>
      </ul>
   </div>
   </div>
   <%-- 임시용 --%>
   <!-- 아티스트 홍보 영상 페이지로 이동하는 폼 hidden -->
   <form action="${initParam.rootPath }/videoListCategory.do" method="post" style="display: none;" id="goArtistVideoForm">
      <sec:csrfInput/>
      <input type="hidden" name="category" value="artist"/>
   </form>   
   <!-- 공연영상 페이지로 이동하는 폼 hidden -->
   <form action="${initParam.rootPath }/videoListCategory.do" method="post" style="display: none;" id="goPerformanceVideoForm">
      <sec:csrfInput/>
      <input type="hidden" name="category" value="performance"/>
   </form>
   <!-- 연습영상 페이지로 이동하는 폼 hidden -->
   <form action="${initParam.rootPath }/videoListCategory.do" method="post" style="display: none;" id="goPracticeVideoForm">
      <sec:csrfInput/>
      <input type="hidden" name="category" value="practice"/>
   </form>
   

<script>
$(document).ready(function(){
	$("#goArtistVideo").on("click", function(){
		$("#goArtistVideoForm").submit();
	});
	
	$("#goPerformanceVideo").on("click", function(){
		$("#goPerformanceVideoForm").submit();
	});
	
	$("#goPracticeVideo").on("click", function(){
		$("#goPracticeVideoForm").submit();
	});
	
	<%-- 메뉴 드롭다운 --%>
	$(".main_menu li").hover(function(){
		$("ul:first", this).stop().slideDown();
	}, function(){
		$("ul:first", this).stop().slideUp();
	});
});
</script>