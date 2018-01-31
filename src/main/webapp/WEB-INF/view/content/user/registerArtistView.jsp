<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row">
	<div class="col-sm-6 col-sm-offset-3">
		<form class="form-inline" action="${initParam.rootPath }/member/registArtist.do" method="post" enctype="multipart/form-data">
			<!-- 권한 토큰 -->
			<sec:csrfInput/>
			
			<h2>아티스트 등록</h2>
			<div class="form-group"> 
				<label for="name">이름</label><br>
				<input class="form-control" type="text" id="name" name="artistName" required="required">
			</div>
			<br>
			<div class="form-group">
				<label for="performance">장르</label><br>
				<input class="form-control" id="performance" type="text" name="performance" required="required">
			</div>
			<br>
			<div class="form-group">
				<label for="profile">프로필</label><br>
				<input class="form-control" id="profile" type="text" name="profile" required="required">
			</div>
			<br>
			<div class="form-group">
				<label for="image">사진</label><br>
				<input class="form-control" id="image" type="file" name="multiImage" required="required">
			</div>
			<br>
			<div class="form-group">
				<label for="members">멤버이름</label><br>
				<input class="form-control" id="members" type="text" name="artistMembers" required="required">
			</div>
			<br>
			<div class="form-group">
				<label for="sns">SNS주소</label><br>
				<input class="form-control" id="sns" type="text" name="sns" required="required"><br>
			</div>
			<br>
			<div class="form-group">
				<sec:authorize access="isAuthenticated()">
					<input type="hidden" name="artistId" id="performanceUserId" class="form-control" value='<sec:authentication property="principal.userId"/>'>
				</sec:authorize>
			</div>
			<br>
			<button class="btn btn-primary">등록</button>
			<button type="button" class="btn btn-danger" onclick="history.back();">취소</button>
		</form>
	</div>
</div>