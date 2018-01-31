<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<form action="${initParam.rootPath }/producer/selectViewPremiumStage.do" method="post">
	<sec:csrfInput/>
	<button>내 공연장 조회하기</button>
	<input type="hidden" name="userId" value='<sec:authentication property="principal.userId"/>'>
</form>

<!-- 수정하는 페이지에서 삭제 까지 같이 하게 만들예정 ★ -->
<form action="${initParam.rootPath }/producer/selectEditPremiumStage.do" method="post">
	<sec:csrfInput/>
	<button>내 공연장 정보 수정하기</button>
	<input type="hidden" name="userId" value='<sec:authentication property="principal.userId"/>'>
</form>

<form action="${initParam.rootPath }/producer/goAddPremiumStage.do" method="post">
	<sec:csrfInput/>
	<button>공연장 추가등록하기</button>
	<input type="hidden" name="userId" value='<sec:authentication property="principal.userId"/>'>
</form>
