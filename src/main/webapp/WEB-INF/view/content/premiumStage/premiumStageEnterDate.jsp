<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
var optionNo = 0;
$(document).ready(function(){
	$("#selectTime").on("click",function(){
		$.ajax({
			"url":"${initParam.rootPath}/producer/readPremiumStageReservationTimeByStageRentalDate.do",
			"type":"get",
			"data":{"reservationDate":$("#reservationDate").val()
			},
			"dataType":"json",
		    "async": "false",
		    "beforsend":function(){
		    	$("#timeCodeList").remove();
		    },
			"success":function(list){
				$("#timeCodeList").remove();
				var n = 1;
				var reg = 0;
				var i=0;
				if(list.length != 0){
					$("#reservationTime").append('<div id="timeCodeList"></div>');
					for(i=0; i<24; i++){
						reg = 0;
						for(var j=0; j<list.length; j++){
							n = 1 + i;
							if(i == list[j]){
								reg = 1;
								j = list.length;
							}
						}
						if(reg == 0){
							$("#timeCodeList").append('<label>'+i+' ~ '+n+'시<input type="checkbox" id="timeCode" name="timeCode" value="'+i+'"></label><br>');
						}
					}
				}else{
					$("#reservationTime").append('<div id="timeCodeList"></div>');
					for(var i=0; i<24; i++){
						n = 1 + i;
						$("#timeCodeList").append('<label>'+i+' ~ '+n+'시<input type="checkbox" id="timeCode" name="timeCode" value="'+i+'"></label><br>');
					}
				}
			},
			"error":function(){
				alert("error");
			}
		});
	});
});
var index = 0;
function addOption(){
	var reservationDate = $("#reservationDate").val();
	var timeCode = new Array();
	$("input[name='timeCode']:checked").each(function(){
		timeCode.push($(this).val());
	});
	var registerDate = new Array();
	var registerTime = new Array();
	if(index != 0){
		$("input[id='registerDate']").each(function(){
			registerDate.push($(this).val());
		});
		/* $("input[id='registerTime']").each(function(){
			registerTime.push($(this).val());
		}); */
		for(var i=0; i<registerDate.length; i++){
			if(registerDate[i] == reservationDate){
				registerTime = $("input[name='optionList["+i+"].timeList']").val();
				for(var j=0; j<registerTime.length; j++){
					for(var r=j; r<timeCode.length; r++){
						if(registerTime[j] == timeCode[r]){
							alert("시간중복! 날짜별 시간을 확인해주세요.");
							$("#timeCodeList").remove();
							return;
						}
					}
				}
			}
		}
	}
	
	var reservationCost = $("#reservationCost").val();
	if(timeCode.length==0){
		alert("시간을 선택해주세요");
		return;
	}else{
		$("#timeCodeList").remove();
		var date = "날짜 : "+(reservationDate);
    	date += "/ 시간 : "+(timeCode);
    	date += "/ 가격 : "+(reservationCost);
    	date += "<input type='hidden' id='establishNo' name='optionList["+index+"].establishNo' value="+${requestScope.map.establishNo}+">"
		date += "<input type='hidden' id='registerDate' name='optionList["+index+"].stageRentalDate' value="+reservationDate+">"
		date += "<input type='hidden' id='registerTime' name='optionList["+index+"].timeList' value="+timeCode+">"
		date += "<input type='hidden' id='registerCost' name='optionList["+index+"].stageCost' value="+reservationCost+"><hr>"
		$("#reservationOption").append(date);
		index ++;
	}
}

</script>

<h2>공연장 대관일 등록</h2>
<div class="form-group">
	<label for="reservationDate">대관날짜</label> 
	<input type="date" name="reservationDate" id="reservationDate" 
		class="form-control" required="required">
</div>
<button id="selectTime" class="btn btn-default">선택</button>
<div class="form-group">
	<label for="reservationTime">대관시간</label>
	<div id="reservationTime">
		<div></div>
	</div> 
</div>
<div class="form-group">
	<label for="reservationCost">가격 (시간 당)</label>
	<input type="number" name="reservationCost" id="reservationCost" required="required">
</div>
<button id="addOption" class="btn btn-default" onclick="addOption()">추가</button>
<form action="${initParam.rootPath }/producer/enterPremiumStageOption.do" id="registerOption" method="post">
	<div class="form-group">
		<label for="reservationOption">옵션</label> 
		<div id="reservationOption">
		</div>
	</div>
	<button id="enterOption" type="submit" class="btn btn-default">등록</button>
	<sec:csrfInput/>
</form>
<div class="form-group">
	<label for="reservationOptionLsit">내 공연장 대관 옵션목록</label> 
	<table class="table">
		<thead>
			<tr>
				<th>대관날짜</th>
				<th>대관시간</th>
				<th>예약상태</th>
				<th>가격</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.map.optionList }" var="option">
				<tr>
					<td><fmt:formatDate value="${option.stageRentalDate }" pattern="yyy/MM/dd"/></td>
					<td>
						<c:forEach items="${option.timeList }" var="timeOption">
							${timeOption}시 - ${timeOption+1}시<br>
						</c:forEach>
					</td>
					<td>${option.stageState }</td>
					<td>${option.stageCost }</td>
					<td>
						<c:if test="${option.stageState == 0}">
							<form action="${initParam.rootPath }/producer/deletePremiumStageOption.do" method="post">
								<sec:csrfInput/>
								<input type="hidden" id="setablishNo" name="establishNo" value="${option.establishNo }">
								<button type="submit" id="optionNo" name="optionNo" class="btn btn-default" value="${option.optionNo }">삭제</button>
							</form>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<form action="${initParam.rootPath }/producer/myStageDetail.do" method="post">
	<sec:csrfInput/>
	<input type="hidden" name="establishNum" value="${requestScope.map.establishNo }">
	<button class="btn btn-default" type="submit">공연장 상세보기로 이동</button>
</form>
