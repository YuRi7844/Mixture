<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery.timepicker.min.js"></script>
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/jquery.timepicker.min.css">
<script>
$(document).ready(function(){
	$(document).on("click", "#addImage", function(){
		$('<div><div style="text-align: center;"><br><img id="imgView" src="#" alt="img" style="height: 300px;"/></div><input type="file" name="imgs" class="form-control" id="inputImage"><button type="button" id="addImage" class="btn btn-default">추가</button><button id="deleteImage" class="btn btn-default" type="button">삭제</button></div>').appendTo("#img_box");
	});
	$(document).on("click", "#deleteImage", function(){
		$(this).parent().remove();
	});

	$("#selectStage").on("click", function(){
		
	});
	
	$("#searchStage").on("click", function(){
		var address = "${initParam.rootPath}/searchStageByAddress.do"; //doGet()방식으로 요청
		left1 = (screen.availWidth - 600) / 2;
		top1 = (screen.availHeight - 500) / 2;
		window.open(address, "newWin", 'width=600 ,height=500 ,top=' + top1 + ',left=' + left1 + ',resizable=no');
	});
	
	$(document).on("click", "#delBtn", function(){
		$(this).parent().prev().attr("name", "delImage");
		$(this).parent().remove();
	});
	
	$('input.timepicker').timepicker({
		
	});
	
	$("#stageStartTime").timepicker({
		timeFormat: 'HH:mm',
	    interval: 60,
	    minTime: '00',
	    maxTime: '23:00',
	    defaultTime: '11',
	    startTime: '00:00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});
	
	$("#stageEndTime").timepicker({
		timeFormat: 'HH:mm',
	    interval: 60,
	    minTime: '00',
	    maxTime: '23:00',
	    defaultTime: '11',
	    startTime: '00:00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});
});

$(function(){
	$(document).on("change", "#inputImage", function(){
		readURL(this);
	});
});

function readURL(input){
	if(input.files && input.files[0]){
		var reader = new FileReader();
		
		reader.onload = function(e){
			$(input).prev().children().attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
</script>

<form action="${initParam.rootPath }/admin/updateStageChange.do" method="post" enctype="multipart/form-data">

	<div class="form-group">
		<label>공급자 아이디</label>
		<input type="text" name="stageSellerId" value="${requestScope.stage.stageSellerId }" class="form-control" readonly="readonly">
	</div>

	<div class="form-group">
		<label>공연장 번호</label>
		<input type="text" name="stageNo" class="form-control" value="${param.stageNo }" readonly>
	</div>
	
	<div class="form-group">
		<label>공연장 이름</label>
		<input type="text" name="stageName" class="form-control" value="${requestScope.stage.stageName }">
	</div>
		
	<div class="form-group">
		<label>공연장 주소</label>
		<input type="text" id="performanceLocation" name="stageLocation" class="form-control" value="${requestScope.stage.stageLocation }" readonly="readonly">
		<input type="button" id="selectStage" value="대관한 장소 선택" class="btn btn-default col-sm-2">
		<input type="button" id="searchStage" value="직접 검색" class="btn btn-default col-sm-1"><br>
	</div>
		
	<div class="form-group">
		<label>가격</label>
		<input type="number" name="stageCost" class="form-control" value="${requestScope.stage.stageCost }">
	</div>
		
	<div class="form-group">
		<label>면적</label>
		<input type="number" name="stageArea" class="form-control" value="${requestScope.stage.stageArea }">
	</div>
		
	<div class="form-group">
		<label>구비된 악기</label>
		<input type="text" name="instrument" class="form-control" value="${requestScope.stage.stageInstrument }">
	</div>
	<div class="form-group" id="img_box">
		<label>이미지</label><br><button type="button" id="addImage" class="btn btn-default">추가</button>
			<c:forEach var="img" items="${requestScope.stage.stageImage }">
				<input type="hidden" class="form-control" value="${img.stageImageNo }">
				<div>					
					<div style="text-align: center;">
						<img src="${initParam.rootPath }/stageImage/${img.stageImageLocation}.jpg" id="imgView" style="height: 300px;" onerror="this.src='${initParam.rootPath }/performanceImage/no-image.png;'">
					</div>
					<button type="button" id="addImage" class="btn btn-default">추가</button>
					<button type="button" class="btn btn-default" id="delBtn">삭제</button>
				</div>
		</c:forEach>
	</div>
	
	<div class="form-group">
		<label>추가 내용 입력</label>
		<textarea rows="15" cols="150" name="stageContent">${requestScope.stage.stageContent }</textarea>
	</div>

	<div class="form-group">
		<label>주차장 유무</label><br>
		
		<c:set var="parking" value="${requestScope.stage.stageParking }"/>
		<c:choose>
			<c:when test="${parking eq 1}">
				<label style="font-weight: normal;"><input type="radio" name="stageParking" value="1" checked="checked">주차장 완비</label>
				<label style="font-weight: normal;"><input type="radio" name="stageParking" value="0">주차장 미비</label>
			</c:when>
			<c:otherwise>
				<label style="font-weight: normal;"><input type="radio" name="stageParking" value="1">주차장 완비</label>
				<label style="font-weight: normal;"><input type="radio" name="stageParking" value="0" checked="checked">주차장 미비</label>
			</c:otherwise>
		</c:choose>
	</div>
		
	<div class="form-group">
		<label>음주가능 여부</label><br>
		
		<c:set var="drinking" value="${requestScope.stage.stageDrinking }"/>
		<c:choose>
			<c:when test="${drinking eq 1}">
				<label style="font-weight: normal;"><input type="radio" name="stageDrinking" value="1" checked="checked">음주 가능</label>
				<label style="font-weight: normal;"><input type="radio" name="stageDrinking" value="0">음주 불가</label>
			</c:when>
			<c:otherwise>
				<label style="font-weight: normal;"><input type="radio" name="stageDrinking" value="1">음주 가능</label>
				<label style="font-weight: normal;"><input type="radio" name="stageDrinking" value="0" checked="checked">음주 불가</label>
			</c:otherwise>
		</c:choose>	
	</div>
		
	<div class="form-group">
		<label>음식 (유료)제공 여부</label><br>
		
		<c:set var="foodSell" value="${requestScope.stage.stageFoodSell }"/>
		<c:choose>
			<c:when test="${foodSell eq 1}">
				<label style="font-weight: normal;"><input type="radio" name="stageFoodSell" value="1" checked="checked">음식 제공</label>
				<label style="font-weight: normal;"><input type="radio" name="stageFoodSell" value="0">음식 미제공</label>
			</c:when>
			<c:otherwise>
				<label style="font-weight: normal;"><input type="radio" name="stageFoodSell" value="1">음식 제공</label>
				<label style="font-weight: normal;"><input type="radio" name="stageFoodSell" value="0" checked="checked">음식 미제공</label>
			</c:otherwise>
		</c:choose>
	</div>
		
	<div class="form-group">
		<label>외부음식 반입 가능 여부</label><br>
		
		<c:set var="foodRestriction" value="${requestScope.stage.stageFoodRestriction }"/>
		<c:choose>
			<c:when test="${foodRestriction eq 1}">
				<label style="font-weight: normal;"><input type="radio" name="stageFoodRestriction" value="1" checked="checked">반입 가능</label>
				<label style="font-weight: normal;"><input type="radio" name="stageFoodRestriction" value="0">반입 불가</label>
			</c:when>
			<c:otherwise>
				<label style="font-weight: normal;"><input type="radio" name="stageFoodRestriction" value="1">반입 가능</label>
				<label style="font-weight: normal;"><input type="radio" name="stageFoodRestriction" value="0" checked="checked">반입 불가</label>
			</c:otherwise>
		</c:choose>
	</div>
	
	<c:set var="reservation" value="${requestScope.stage.stageReservation }"/>
	<c:choose>
		<c:when test="${reservation eq 1}">
			<input type="hidden" name="stageReservation" value="1">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="stageReservation" value="0">
		</c:otherwise>
	</c:choose>
	
	<div class="form-group">
		<label>대관일</label>
		<input type="Date" name="stageRentalDate" id="stageRentalDate" value='<fmt:formatDate value="${requestScope.stage.stageRentalDate }" pattern="yyyy-MM-dd"/>'>
	</div>
	<div>
		<label>시작 시간</label> 
		<input type="timepicker" name="stageStartTime" id="stageStartTime" required="required" value="${requestScope.stage.stageStartTime }">
		<!--	<button type="button" id="dateBtn">날짜 확인</button>
			<input type="datetime" readonly="readonly" name="stageSDate" id="stageSDate" required="required"> -->
	</div>
	<div>
		<label>끝나는 시간</label> 
		<input type="timepicker" name="stageEndTime" id="stageEndTime" required="required" value="${requestScope.stage.stageEndTime }">
		<!--	<button type="button" id="dateBtn2">날짜 확인</button>
			<input type="datetime" readonly="readonly" name="stageEDate" id="stageEDate" required="required"> -->
	</div>
	<br>
	
	<button type="submit" class="btn btn-default">수정완료</button>
	<button type="button" class="btn btn-default" onclick="history.back();">취소</button>
	
	<sec:csrfInput /><%-- csrf 토큰 --%>
</form>