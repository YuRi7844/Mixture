<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
$(document).ready(function(){
	$(".my_menu li").hover(function(){
		$("ul:first", this).stop().slideDown();
	}, function(){
		$("ul:first", this).stop().slideUp();
	});
});
</script>

<style>
.userProfileTbl tr td:nth-child(1){
	width: 70px;
}
#followBox{
	transition: 0.3s;
	color: #999;
}

#followBox:hover{
	cursor: pointer;
	color: #000;
	border-bottom: 2px solid #000;
}

.my_menu{
	position: relative;
	z-index: 1;
}
.my_menu>ul>li {
	transition: 0.5s;
}
.my_menu>ul>li>a {
	font-size: 14px;
}
.my_menu>ul>li:hover {
	background-color: #999;
}
.my_menu>ul>li:hover>a {
	color: #fff;
}
.my_menu a {
	font-weight: bold;
	color: #000;
	text-decoration: none;
	transition: 0.5s;
}
.my_menu li {
	text-align: center;
	line-height: 30px;
}

#my_sub {
	display: none;
}

#my_sub li {
	text-align: center;
	background-color: GhostWhite;
	border-bottom: 1px solid #999;
	transition: 0.5s;
	position: relative;
	z-index: 2;
}
#my_sub li:hover {
	background-color: #eee;
}
#my_sub li a{
	color : #aaa;
}
#my_sub li:hover > a {
	color: #000;
}

</style>

<div style="width: 100%; float: left;">
<h5 style="float: left; font-weight: bold;">${requestScope.user.userName } 님의 마이 페이지</h5>
</div>

<div style="width: 100%; height: 180px; float: left; margin-top: 20px;">
	<!-- 일반 사용자 프로필 -->
	<div style="width: 40%; height: 100%; float: left; margin-left: 10%; border-radius: 10px; box-shadow:3px 3px #888; background-color: #fafafa;">
		<!-- default 이미지 -->
		<div style="background-color: #fff; width: 40%; height: 80%; margin-top: 3%; margin-left: 5%; overflow: hidden;	border-radius: 10px; float: left;">
			<img alt="img" src="${initParam.rootPath }/myPageImage/myPageDefaultImage.png" style="width: 100%; height: 100%;">
		</div>
		<!-- 유저 정보 들어가는 부분 -->
		<div style="width: 50%; height: 80%; margin-top: 3%; margin-left: 3%; float: left;">
			<table style="margin-top: 10%;" class="userProfileTbl">
				<tr>
					<td><label>Name</label></td>
					<td>${requestScope.user.userName }</td>
				</tr>
				<tr>
					<td><label>Address</label></td>
					<td>
						${requestScope.user.userAddress }
					</td>
				</tr>
				<tr>
					<td><label>PH</label></td>
					<td>
						<c:set var="phoneNum" value="${requestScope.user.userPhoneNum }"/>
						${fn:substring(phoneNum, 0, 3) }-${fn:substring(phoneNum, 3, 7) }-${fn:substring(phoneNum, 7, 11) }
					</td>
				</tr>
				<tr>
					<td><label>E-mail</label></td>
					<td>${requestScope.user.email }</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 부가 정보 -->
	<div style="width: 30%; height: 100%; float: left; margin-left: 5%;">
		<div style="width: 90%; height: 90%; margin-left: 5%; margin-top: 12%;">
			<div style="width: 100%; text-align: center;">
				<h5 style="font-weight: bold;">Follow 정보</h5>			
			</div>
			<div style="margin-top: 20px; width: 80%; height: 30%; text-align: center; margin-left: 10%; float: left;" class="followTbl">
				<div style="height: 100%; font-weight: bold; width: 50%; float: left;" id="followBox">
					following<br>
					${requestScope.followingCount }명
				</div>
				<div style="height: 100%; font-weight: bold; width: 50%; float: left;" id="followBox">
					follower<br>
					${requestScope.followerCount }명
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 프로필 등록 전용 버튼 박스 -->
<div style="margin-top: 10px; width: 100%; height: 40px; float: left;">
	<div style="width: 500px; height: 100%; margin-left: 100px;">
		<sec:authorize access="!hasRole('ROLE_ARTIST')">
			<a style="float: left; margin-left: 10px;" class="btn btn-default" href="${initParam.rootPath }/registerArtistView.do">아티스트 프로필 등록</a>
		</sec:authorize>
		<sec:authorize access="!hasRole('ROLE_PRODUCER')">
			<a type="button" style="float: left; margin-left: 10px;" class="btn btn-default" href="${initParam.rootPath }/registerSupplierView.do">내 공연장 등록</a>
		</sec:authorize>
	</div>
</div>
<!-- 마이페이지 목록 박스 -->
<nav style="width: 100%; text-align: center; height: 80px; float: left; margin-top: 10px;">
	<div style="width: 80%; height: 100%; margin-left: 10%; float: left;">
		<div class="my_menu" style="height: 50%; width: 100%; float: left;">
			<ul>
				<li style="padding: 0; width: 25%; float: left;"><a href="${initParam.rootPath }/member/selectMyPerformance.do">내가올린 글</a>
					<ul id="my_sub" style="padding: 0;">
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/member/selectMyPerformance.do" style="padding: 0;">공연정보</a></li>
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/member/selectMyVideo.do" style="padding: 0;">공연영상</a></li>
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/member/selectMyStage.do" style="padding: 0;">공연장대관</a></li>
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/member/selectMyHelp.do" style="padding: 0;">고객센터</a></li>
					</ul>
				</li>
				<li style="padding: 0; width: 25%; float: left;"><a href="${initParam.rootPath }/member/MyStageSupply.do">공연장 내역</a>
					<ul id="my_sub" style="padding: 0;">
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/member/MyStageSupply.do" style="padding: 0;">신청받은  내역</a></li>
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/member/MyStageApply.do" style="padding: 0;">신청한 내역</a></li>
					</ul>
				</li>
				<li style="padding: 0; width: 25%; float: left;"><a href="${initParam.rootPath }/member/myLikeInfo.do">좋아한 글</a>
					<ul id="my_sub" style="padding: 0;">
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/member/myLikePerformance.do" style="padding: 0;">공연정보</a></li>
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/member/myLikeVideo.do" style="padding: 0;">공연영상</a></li>
					</ul>
				</li>
				<li style="padding: 0; width: 25%; float: left;"><a href="${initParam.rootPath }/member/passwordCheck.do">정보관리</a>
					<ul id="my_sub" style="padding: 0;">
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/member/passwordCheck.do" style="padding: 0;">회원정보 수정</a></li>
						<sec:authorize access="hasRole('ROLE_ARTIST')">
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/artist/passwordCheck.do" style="padding: 0;">아티스트정보 수정</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_PRODUCER')">
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/producer/selectViewPremiumStage.do?userId=${requestScope.user.userId}" style="padding: 0;">공연장정보 수정</a></li>
						</sec:authorize>
						<li style="padding: 0; width: 100%;"><a href="${initParam.rootPath }/member/outMember.do" style="padding: 0;">회원 탈퇴</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>