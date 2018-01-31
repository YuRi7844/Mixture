<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function changeStageState(stateCode){
	var x = document.createElement("INPUT");
    x.setAttribute("type", "hidden");
    x.setAttribute("name", "stageState");
    x.setAttribute("value", stateCode);
    document.getElementById("changeStageState").appendChild(x);
	document.getElementById("changeStageState").submit();
}

$(document).ready(function(){
	$("#goUserDetail").on("click",function(){
		$.ajax({
			"url":"${initParam.rootPath}/producer/readReservationUserDetail.do",
			"type":"get",
			"data":{"optionNo":$('#goUserDetail').val()},
			"dataType":"json",
			"success":function(user){
				var data = "<br><p>";
				data += "신청자 이름 : "+user.userName;
				data += "<br>신청자 연락처 : "+user.userPhoneNum+"</p>";
				data += "<br><button type='button' onclick='removeUserInfo()' class='btn btn-default'>닫기</button>"
				$("#userInfo").append('<div id="userDetail">'+data+'</div>');
			},
			"error":function(a,b,c){
				alert(b);
				alert(c);
			}
		});
	});	
});

function removeUserInfo(){
	document.getElementById("userDetail").remove();
}
</script>

<div class="container-inline">
	<c:forEach items="${requestScope.map.imageList }" var="image">
		<img src="${initParam.rootPath }/supplierImage/${image }" onerror='this.src="${initParam.rootPath }/stageImage/no-image.png"'
			style="width:300px; height:300px;">
	</c:forEach>
</div>
<table class="table">
	<thead>
		<tr>
			<th>사업장번호</th>
			<th>사업자번호</th>
			<th>장소명</th>
			<th>주소</th>
			<th>면적</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${requestScope.map.premiumStage.establishNum }</td>
			<td>${requestScope.map.premiumStage.operatorNo }</td>
			<td>${requestScope.map.premiumStage.stageName }</td>
			<td>${requestScope.map.premiumStage.stageLocation }</td>
			<td>${requestScope.map.premiumStage.stageArea }</td>
		</tr>
	</tbody>
</table>

<table class="table">
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
			<th>예약상태</th>
			<th>수락/거절</th>
			<th>예약신청자</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.map.optionList }" var="option">
			<tr>
				<td><fmt:formatDate value="${option.stageRentalDate }" pattern="yyyy/MM/dd"/></td>
				<td>
					<c:forEach items="${option.timeList }" var="timeOption">
								${timeOption}시 - ${timeOption+1}시<br>
					</c:forEach>
				</td>
				<td>${option.stageCost }</td>
				<td>
					<c:choose>
						<c:when test="${option.stageState == 2}">대관완료</c:when>
						<c:when test="${option.stageState == 1}">수락대기</c:when>
						<c:otherwise>신청대기</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${option.stageState == 2}">대관완료</c:when>
						<c:when test="${option.stageState ==1}">
							<form action="${initParam.rootPath }/changePremiumStageState.do" method="post" id="changeStageState">
								<sec:csrfInput/>
								<button type="button" class="btn btn-defalut" id="acceptance" onclick="changeStageState('2')">수락</button>
								<button type="button" class="btn btn-defalut" id="rejection" onclick="changeStageState('0')">거절</button>
								<input type="hidden" name="optionNo" value="${option.optionNo }">
								<input type="hidden" name="establishNo" value="${option.establishNo}"> 
							</form>
						</c:when>
					</c:choose>
				</td>
				<td>
					<div id="userInfo">
						<c:if test="${option.stageState != 0 }">
							<button type="button" class="btn btn-default" id="goUserDetail" name="goUserDetail" value="${option.optionNo }">신청자 정보보기</button>
						</c:if>	
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<form action="${initParam.rootPath }/producer/goPremiumStageEnterDate.do" method="get">
	<sec:csrfInput/>
	<input type="hidden" name="establishNo" value="${requestScope.map.premiumStage.establishNum }">
	<button class="btn btn-default" type="submit">대관일 등록/수정 으로 이동</button>
</form>