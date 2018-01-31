<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">


function sendForm(formId){
	document.getElementById(formId).submit();
}

</script>

<div class="container col-sm-3" style="padding:0;">
	<div>
		<ul class="col-sm-12" onmouseover="" onmouseout="">
			<li>아티스트 등록</li>
			<li>공연장 공급자 등록</li>
			<li>회원정보수정</li>
			<li>회원탈퇴</li>
			<li>팔로우정보조회</li>
			<li>
				<span onmouseover=onMenu(this); onmouseout=outMenu(this); >좋아요 정보 조회</span>
				<ul style="display:none; padding:0; background-color:GhostWhite;">
					<li>공연정보</li>
					<li>동영상</li>			
				</ul>
			</li>
		</ul>
	</div>
</div>
<div class="col-sm-9">

</div>

<div class="container col-sm-3" style="float:left;">
	<form action="${initParam.rootPath }/registerArtistView.do">
		<button class="btn btn-default">아티스트 등록.</button>
	</form>
	<form action="${initParam.rootPath }/registerSupplierView.do">
		<button class="btn btn-default">공연장공급자 등록.</button>
	</form>
	<form action="${initParam.rootPath }/updateMemberInfo.do">
		<sec:csrfInput/>
		<button class="btn-default">회원정보수정</button>
	</form>
	<form action="${initParam.rootPath }/member/out_member_form.do" class="form-group">
		<sec:csrfInput/>
		<button id="dropBtn" class="btn-default">회원탈퇴</button>
	</form>
	
	<form action="${initParam.rootPath }/member/myFollowInfo.do" method="post" class="form-group">
		<sec:csrfInput/>
		<button class="btn-default">팔로우 정보 조회</button>
	</form>
	<form action="${initParam.rootPath }/member/myLikeInfo.do" method="post" class="form-group">
		<sec:csrfInput/>
		<button>좋아요 정보 조회</button>
	</form>
</div>



<form>
	<input type="hidden" >
</form>
	<a class="btn btn-default" href="${initParam.rootPath }/updateMemberInfo.do" role="button">회원정보수정</a><br>
	<a class="btn btn-default" href="${initParam.rootPath }/member/out_member_form.do" role="button">회원탈퇴</a>
