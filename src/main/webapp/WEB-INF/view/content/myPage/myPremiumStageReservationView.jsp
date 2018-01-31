<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<table class="table">
	<thead>
		<tr>
			<th>신청한 대관일</th>
			<th>시간</th>
			<th>가격</th>
			<th>상태</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.myReservationList }" var="reservation">
			<tr>
				<td>
					<fmt:formatDate value="${reservation.option.stageRentalDate }" pattern="yyy/MM/dd"/>
				</td>
				<td>
					<c:forEach items="${reservation.option.timeList }" var="timeOption">
								${timeOption}시 - ${timeOption+1}시<br>
					</c:forEach>
				</td>
				<td>${reservation.option.stageCost}</td>
				<td>
					<c:choose>
						<c:when test="${reservation.option.stageState == 0}">공연장 대관이 거절되었습니다.</c:when>
						<c:when test="${reservation.option.stageState ==1}">
							공연장 대관 신청중입니다.
							<form action="${initParam.rootPath }/changePremiumStageState.do" method="post">
								<sec:csrfInput/>
								<input type="hidden" name="optionNo" value="${reservation.optionNo}">
								<input type="hidden" name="stageState" value="0" }>
								<button type="submit" class="btn btn-default">신청취소</button>
							</form>
						</c:when>
						<c:otherwise>공연장 대관이 완료되었습니다.</c:otherwise>
					</c:choose>
				</td>
				<td>
					<form action="${initParam.rootPath }/goPremiumStageDetailView.do" method="post">
						<sec:csrfInput/>
						<input type="hidden" name="establishNo" value="${reservation.establishNo }">
						<button type="submit" class="btn btn-default">공연장 정보 상세보기</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
