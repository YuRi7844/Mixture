<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h2>영상등록 카테고리 선택</h2>
<form action="${initParam.rootPath }/artist/selectArtistVideoCategory.do" method="post">
	<sec:csrfInput/>
	<button type="submit">아티스트 홍보 영상</button>
	<%-- <button type="submit" onclick="location.href='${initParam.rootPath}/member/selectPerformanceVideoCategory.do'">공연 영상</button>
	<button type="submit" onclick="location.href='${initParam.rootPath}/member/selectMemberVideoCategory.do'">개인 연습 영상</button> --%>
</form>
<p>
<form action="${initParam.rootPath }/member/selectPerformanceVideoCategory.do" method="post">
	<sec:csrfInput/>
	<button type="submit">공연 영상</button>
</form>
<p>
<form action="${initParam.rootPath }/member/selectMemberVideoCategory.do" method="post">
	<sec:csrfInput/>
	<button type="submit">개인 연습 영상</button>
</form>
<p>

