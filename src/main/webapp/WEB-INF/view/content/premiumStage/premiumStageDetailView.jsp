<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	if($("#regist").val()=='success'){
		alert("공연장 등록완료. 재로그인해주세요.")
	}
	
});

</script>
<input type="hidden" id="regist" value="${requestScope.map.regist }">

<div class="container-inline">
	<div class="thumbnail row">
		<c:forEach items="${requestScope.map.imageList }" var="image">
			<img class="img-thumbnail col-sm-4" src="${initParam.rootPath }/supplierImage/${image }" onerror='this.src="${initParam.rootPath }/stageImage/no-image.png"'
				style="width:300px; height:300px;">
		</c:forEach>
	</div>
</div>
<table class="table table-condensed table-bordered">
	<thead>
		<tr>
			<th>장소명</th>
			<th>주소</th>
			<th>면적</th>
			<th>설명</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${requestScope.map.premiumStage.stageName }</td>
			<td>${requestScope.map.premiumStage.stageLocation }</td>
			<td>${requestScope.map.premiumStage.stageArea }</td>
			<td>${requestScope.map.premiumStage.stageContent }</td>
		</tr>
	</tbody>
</table>

<table class="table table-condensed table-bordered">
	<thead>
		<tr>
			<th>주차가능여부</th>
			<th>음주가능여부</th>
			<th>음식판매여부</th>
			<th>외부음식 반입가능여부</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>
				<c:choose>
					<c:when test="${requestScope.map.premiumStage.stageParking eq 0 }">O</c:when>
					<c:otherwise>X</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${requestScope.map.premiumStage.stageDrinking eq 0 }">O</c:when>
					<c:otherwise>X</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${requestScope.map.premiumStage.stageFoodSell eq 0 }">O</c:when>
					<c:otherwise>X</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${requestScope.map.premiumStage.stageFoodRestriction eq 0 }">O</c:when>
					<c:otherwise>X</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</tbody>
</table>

<table class="table">
	<thead>
		<tr>
			<th>대관날짜</th>
			<th>대관시간</th>
			<th>가격</th>
			<th>신청</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.map.optionList }" var="option">
			<c:if test="${option.stageState == 0 }">
				<tr>
					<td><fmt:formatDate value="${option.stageRentalDate }" pattern="yyyy/MM/dd"/></td>
					<td>
						<c:forEach items="${option.timeList }" var="timeOption">
									${timeOption}시 - ${timeOption+1}시<br>
						</c:forEach>
					</td>
					<td>${option.stageCost }</td>
					<td>
						<form action="${initParam.rootPath }/member/createPremiumStageReservation.do" >
							<sec:csrfInput/>
							<sec:authorize access="isAuthenticated()">
								<input type="hidden" name="reservationUserId" id="reservationUserId"
									class="form-control"
									value='<sec:authentication property="principal.userId"/>'>
							</sec:authorize>
							<input type="hidden" name="establishNo" value="${option.establishNo }">
							<input type="hidden" name="optionNo" value="${option.optionNo }">
							<button type="submit" class="btn btn-default">신청</button>
						</form>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>
