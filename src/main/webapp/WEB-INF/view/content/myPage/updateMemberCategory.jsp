<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- 일반회원수정폼 -->
<sec:authorize access="isAuthenticated()">
	<form action="${initParam.rootPath }/member/passwordCheck.do">
		<sec:csrfInput/>
		<input type="hidden" name="category" value="user">
		<button>일반회원정보수정</button>
	</form>
	
	<!-- 아티스트회원정보수정폼 -->
	<form action="${initParam.rootPath }/artist/passwordCheck.do" method="post">
		<sec:csrfInput/>
		<input type="hidden" name="category" value="artist">
		<button>아티스트정보수정</button>
	</form>
	
	<!-- 공연장대관공급자수정폼 -->
	<!-- premiumStageController로 이동 -->
	<form action="${initParam.rootPath }/producer/menu.do" method="get">
		<sec:csrfInput/>
		<button>공연장대관정보수정</button>
	</form>
</sec:authorize>
