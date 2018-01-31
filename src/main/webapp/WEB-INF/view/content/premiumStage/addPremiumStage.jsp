<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function addPicture(){
	var txt = '<input type="file" name="multiImage" id="multiImage">';
	$("#image").append(txt);
}


function submitChk(){
	// 사업자번호 체크.
	var establishNo = $("#establishNo").val();
	var pattern = /^[0-9]*$/;
	if(!pattern.test(establishNo)){
		alert("사업장번호는 숫자로만 입력해주세요.");
		$("#establishNo").val("");
		$("#establishNo").focus();
		return false;
	} else if(!(establishNo.length==10)) {
		alert("사업장 번호는 10자리로 입력해주세요.");
		$("#establishNo").val("");
		$("#establishNo").focus();
		return false;
	}
	
	var operatorNo = $("#operatorNo").val();
	if(!pattern.test(operatorNo)){
		alert("사업자번호는 숫자로만 입력해주세요.");
		$("#operatorNo").val("");
		$("#operatorNo").focus();
		return false;
	} else if(!(operatorNo.length==10)){
		alert("사업자 번호는 10자리로 입력해주세요.");
		$("#operatorNo").val("");
		$("#operatorNo").focus();
		return false;
	}
	
	document.getElementById("registerForm").submit();
}


</script>

<div>
	<form class="form-inline col-sm-offset-1" action="${initParam.rootPath }/producer/addPremiumStage.do" method="post" enctype="multipart/form-data" id="registerForm">
		<sec:csrfInput />
		<input type="hidden" name="operatorUserId" value="${requestScope.userId }">
		
		<!-- ######################## 필수입력 칸 ######################## -->
		
		<div class="form-group">
			<label>사업장번호</label><br>
			<input class="form-control" type="text" name="establishNum" id="establishNo" required="required"><br>
		</div>
		<br>
		
		<div class="form-group">
			<label>사업자번호</label><br>
			<input class="form-control" type="text" name="operatorNo" id="operatorNo" required="required"><br>
		</div>
		
		<br>
		<div class="form-group">
			<label>장소이름</label><br>
			<input class="form-control" type="text" name="stageName" id="stageName" required="required"><br>
		</div>
		<br>
		<div class="form-group">
			<label>주소</label><br>
			<input class="form-control" type="text" name="stageLocation" id="stageLocation" required="required"><br>
		</div>
		<br>
		<div class="form-group">
			<label>면적(m^2)</label><br>
			<input type="number" name="stageArea" id="stageArea" required="required"><br>
		</div>
		<br>
		<div class="form-group">
			<label>구비된 악기</label>
			<input class="form-control" type="text" name="stageInstrument" id="instrument">
		</div>
		<br>
		<div class="form-group">
			<label>글 내용</label>
			<textarea class="form-control" name="stageContent" id="content"></textarea>
		</div>
		<!-- ################################################################### -->
		<p><hr><p>
		<table class="table">
			<tr>
				<th>주차가능여부</th>
				<th>음주가능여부</th>
				<th>음식판매여부</th>
				<th>외부음식 반입가능여부</th>
			</tr>
			<tr>
				<td>
					<span>O : </span><input type="radio" name="stageParking" id="stageParking" value="0">
					<span>X : </span><input type="radio" name="stageParking" id="stageParking" value="1">
				</td>
				<td>
					<span>O : </span><input type="radio" name="stageDrinking" id="stageDrinking" value="0">
					<span>X : </span><input type="radio" name="stageDrinking" id="stageDrinking" value="1">
				</td>
				<td>
					<span>O : </span><input type="radio" name="stageFoodSell" id="stageFoodSell" value="0">
					<span>X : </span><input type="radio" name="stageFoodSell" id="stageFoodSell" value="1">
				</td>
				<td>
					<span>O : </span><input type="radio" name="stageFoodRestriction" id="foodRestriction" value="0">
					<span>X : </span><input type="radio" name="stageFoodRestriction" id="foodRestriction" value="1">
				</td>
			</tr>
		</table>
		
		<div id="image">
			<span>사진등록</span>
			<input type="file" name="multiImage" id="multiImage">
		</div>
		<hr>
		<button class="btn btn-primary" type="button" onclick=addPicture(); >사진 추가 등록</button>
		<hr>
		<button class="btn btn-primary" type="submit" onclick=submitChk(); >등록</button>
	</form>
</div>