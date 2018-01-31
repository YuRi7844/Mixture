<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<form action="${initParam.rootPath }/producer/goStageUpdateView.do" method="post">
	<h3>공연장 선택</h3>
	<select name="establishNum">
		<c:forEach items="${requestScope.list }" var="stage">
			<option value="${stage.establishNum }">${stage.stageName }</option>	
		</c:forEach>
	</select>
	<sec:csrfInput/>
	<button>공연장 선택</button>
</form>