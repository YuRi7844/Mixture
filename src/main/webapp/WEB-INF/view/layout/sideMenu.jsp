<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
.side_menu{
	float:left;
	height:100%;
	padding:0;
	background-color:ghostWhite;
	
}

</style>

<div class="col-sm-12" style="float:left;">
	<ul class="col-sm-12">
		<li class="side_menu col-sm-12">내 정보관리
			<ul class="side_sub_menu">
				<li><a href="${initParam.rootPath }/updateMemberInfo.do">회원정보관리</a>
					<ul>
						<li>일반회원정보수정</li>
						
						<sec:authorize access="hasRole('ROLE_ARTIST')">
							<li>아티스트정보수정</li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_PRODUCER')">
							<li>공연장정보수정</li>
						</sec:authorize>
					</ul>				
				</li>
				<li><a href="${initParam.rootPath }/member/out_member_form.do">회원탈퇴</a></li>
			</ul>		
		</li>
		<li class="side_menu col-sm-12">제휴자등록하기
			<ul class="side_sub_menu">
				<li><a href="${initParam.rootPath }/registerArtistView.do">아티스트 등록하기</a></li>
				<li><a href="${initParam.rootPath }/registerSupplierView.do">공연장 등록하기</a></li>
			</ul>
		</li>
		<li class="side_menu col-sm-12">내 활동정보보기
			<ul class="side_sub_menu">
				<li><a href="${initParam.rootPath }/member/myFollowInfo.do">팔로우 정보 보기</a></li>
				<li><a href="${initParam.rootPath }/member/myLikeInfo.do">좋아요 정보 보기</a></li>			
			</ul>
		</li>
	</ul>
</div>

