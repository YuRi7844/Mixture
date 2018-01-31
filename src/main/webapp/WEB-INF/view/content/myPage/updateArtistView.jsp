<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container">
	<form action="${initParam.rootPath }/artist/updateArtist.do" method="post" enctype="multipart/form-data">
		<!-- 토큰보내기 -->
		<sec:csrfInput/>
	
		<!-- hidden으로 artistId request -->
		<div>
			<input type="hidden" name="artistId" value="<sec:authentication property="principal.userId"/>">
		</div>
		
		<!-- 아티스트명 -->
		<div>
			<span>아티스트명 : </span>
			<input type="text" name="artistName" required="required" value="${requestScope.artist.artistName }">
		</div>
		<!-- 장르 -->
		<div>
			<span>장르 : </span>
			<input type="text" name="performance" required="required" value="${requestScope.artist.performance }">
		</div>
		
		<!-- 프로필 -->
		<div>
			<span>프로필</span>
			<input type="text" name="profile" required="required" value="${requestScope.artist.profile }">
		</div>
		
		<!-- 사진 -->
		<div>
			<span>프로필사진</span>
			<input type="file" name="multiImage" required="required">
		</div>
		
		<!-- 멤버이름 -->
		<div>
			<span>멤버 이름</span>
			<input type="text" name="artistMembers" value="${requestScope.artist.artistMembers }">
		</div>
		
		<!-- SNS -->		
		<div>
			<span>SNS</span>
			<input type="text" name="artistSns" value="${requestScope.artist.artistSns }">
		</div>
		
		<button>수정</button>
		<button>뒤로가기</button>
	</form>
</div>