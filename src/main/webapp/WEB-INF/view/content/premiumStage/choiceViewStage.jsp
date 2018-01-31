<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="establishList">
	<h2 class="text-center">나의 공연장</h2>
	<table class="table table-border table-hover">
		<c:forEach items="${requestScope.list }" var="stage">
			<tr>
				<td class="text-center">${stage.stageName }</td>
				<td>
					<form action="${initParam.rootPath }/producer/myStageDetail.do" method="post">
							<sec:csrfInput/>
							<button class="btn btn-primary" id="establishNum" name="establishNum" value="${stage.establishNum }">상세보기</button>
					</form>
				</td>
				<td>
					<form action="${initParam.rootPath }/producer/goStageUpdateView.do" method="post">
							<sec:csrfInput/>
							<button class="btn btn-warning">수정하기</button>
							<input type="hidden" name="establishNum" value='${stage.establishNum }'>
					</form>
				</td>	
			</tr>
		</c:forEach>
	</table>
</div>

<br><hr><br>

<div class="row">
	<div class="col-sm-12 center-block">
		<form class="form-group" action="${initParam.rootPath }/producer/goAddPremiumStage.do" method="post">
			<sec:csrfInput/>
			<button class="btn-default form-control">공연장 추가등록하기</button>
			<input type="hidden" name="userId" value='<sec:authentication property="principal.userId"/>'>
		</form>
	</div>
</div>