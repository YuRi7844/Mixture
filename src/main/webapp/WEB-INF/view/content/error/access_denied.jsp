<%--
권한 이 없는 작업을 요청시 응답할 Error 페이지
 --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h2 class="text-danger"><sec:authentication property="principal.userId"/>님은 접근 권한이 없습니다. 프로필 등록을 통해 이용등급을 변경해 주세요!</h2>
<p>
<sec:authorize access="hasRole('ROLE_PRODUCER')">
	<form action="${initParam.rootPath }/registerArtistView.do">
		<button type="submit" class="btn btn-default">아티스트 등록하러 가기</button>
	</form>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ARTIST')">
	<form action="${initParam.rootPath }/registerSupplierView.do">
		<button type="submit" class="btn btn-default">프리미엄 공연장 등록하러 가기</button>
	</form>
</sec:authorize>
<a href="${initParam.rootpath }/index">홈페이지</a>
