<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery.timepicker.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#regBtn").on("click", function(){
		$("#performanceDate").val($("#performanceDay").val() + " " + $("#performanceTime").val());
		$("#stageRegForm").submit();
	});	
	$("#searchStage").on("click", function(){
		var address = "${initParam.rootPath}/searchStageByAddress.do"; //doGet()방식으로 요청
		left1 = (screen.availWidth - 600) / 2;
		top1 = (screen.availHeight - 500) / 2;
		window.open(address, "newWin", 'width=800 ,height=600 ,top=' + top1 + ',left=' + left1 + ',resizable=no');
	});
	
	$("#performanceTime").timepicker({
		timeFormat: 'HH:mm',
	    interval: 60,
	    minTime: '00',
	    maxTime: '23:00',
	    startTime: '00:00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});
});

$(function(){
	$("#multiImage").on("change", function(){
		readURL(this);
	});
});

function readURL(input){
	if (input.files && input.files[0]){
		var reader = new FileReader();
		
		reader.onload = function(e){
			$("#imgView").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

</script>
<link rel="stylesheet" href="${initParam.rootPath }/resource/css/jquery.timepicker.min.css">
<h4 style="margin-left: 20px;"><b>공연정보 등록</b></h4>
<hr>
<div style="width: 100%;" class="center-block">
	<form action="${initParam.rootPath }/member/performanceRegister.do" method="post" 
		class="performance_register_form center-block"
		enctype="multipart/form-data" 
		id="stageRegForm"
		style="width: 60%;">
		
		<%-- 공연id hidden --%>
		<div class="form-group">
			<input type="hidden" name="performanceNo" id="performanceNo" class="form-control" required="required" value="0">
		</div>
		
		<div class="form-group">
			<label for="performanceName">공연 이름</label>
			<input type="text" name="performanceName" id="performanceName" class="form-control" required="required">
		</div>
		<div class="form-group">
			<label for="performanceTitle">글제목</label>
			<input type="text" name="performanceTitle" id="performanceTitle" class="form-control" required="required">
		</div>
		<div class="form-group">
			<label for="performanceLocation">공연장소</label>
			<input type="text" name="performanceLocation" id="performanceLocation" class="form-control" required="required" readonly="readonly" placeholder="버튼을 통해 장소를 검색해 주세요.">  
			<input type="button" id="searchStage" value="직접 검색" class="btn btn-default col-sm-2"><br>
		</div>
		<br>
			<div class="form-group col-sm-6">	
				<label class="col-sm-6">공연일시</label><input type="date" name="performanceDay" id="performanceDay" required="required" class="form-control">
			</div>
			<div class="form-group col-sm-6">
				<label class="col-sm-6">공연시간</label><input class="form-control" type="Timepicker" name="performanceTime" placeholder="시간선택" id="performanceTime" required size="8" maxlength="5" required="required" readonly="readonly" style="background-color: #fff;">
			</div>
			<div class="form-group" style="display: none;">
				<input type="datetime" readonly="readonly" name="performanceDate" id="performanceDate" class="form-control col-sm-3" required="required">
			</div>
			<label class="col-sm-12">추가 정보 입력</label> 
			<div class="form-group">
				<textarea rows="15" class="form-control col-sm-12" name="performanceContent" placeholder="공연에 대한 정보를 입력하세요." id="content" style="resize: none;"></textarea>
			</div>
			
			<div class="form-group">
				<label for="multiImage">이미지</label>
				<input type="file" name="multiImage" id="multiImage" class="form-control">
				<div style="text-align: center;">
					<label>이미지 미리보기</label><br>
					<img id="imgView" src="#" alt="img" style="height: 300px;"/>
				</div>
			</div>
			
			<%-- 사용자 id hidden --%>
			<div class="form-group">
				<sec:authorize access="isAuthenticated()">
				<input type="hidden" name="performanceUserId" id="performanceUserId" class="form-control" value='<sec:authentication property="principal.userId"/>'>
				</sec:authorize>
			</div>
		
		
		<!-- 공연장 번호 -->
		<button type="button" class="btn btn-default" id="regBtn">등록</button> 
		<button type="button" class="btn btn-default" onclick="location.href='${initParam.rootPath}/performanceView.do'">취소</button>
		<sec:csrfInput/><%-- csrf 토큰 --%>
	</form>
</div>