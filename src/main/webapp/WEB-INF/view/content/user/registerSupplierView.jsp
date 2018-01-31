<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#searchStage").on("click", function(){
		var address = "${initParam.rootPath}/searchStageByAddress.do"; //doGet()방식으로 요청
		left1 = (screen.availWidth - 600) / 2;
		top1 = (screen.availHeight - 500) / 2;
		window.open(address, "newWin", 'width=800 ,height=600 ,top=' + top1 + ',left=' + left1 + ',resizable=no');
	});
});

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

<div class="row">
	<h2 class="text-center">공연장을 처음 등록합니다!</h2>
	<br><hr><br>
	<form class="col-sm-6 col-sm-offset-3" action="${initParam.rootPath }/member/registSupplier.do"
		method="post" enctype="multipart/form-data" id="registerForm"
		class="form-inline">
		<sec:csrfInput />
		
		<!-- ######################## 필수입력 칸 ######################## -->
		<div class="form-group">
			<label for="establishNo">사업장번호</label><br>
			<input class="form-control" type="text" name="establishNum" id="establishNo" required="required"><br>
		</div>
		
		<div class="form-group">
			<label for="operatorNo">사업자번호</label><br>
			<input class="form-control" type="text" name="operatorNo" id="operatorNo" required="required"><br>
		</div>
		
		<div class="form-group">
			<label for="stageName">장소명</label><br>
			<input class="form-control" type="text" name="stageName" id="stageName" required="required"><br>
		</div>
		
		<div class="form-group">
			<label for="performanceLocation">주소</label><br>
			<input type="text" name="stageLocation" id="performanceLocation" class="form-control" required="required" readonly="readonly" placeholder="버튼을 통해 장소를 검색해 주세요."><br>  
			<input type="button" id="searchStage" value="직접 검색" class="btn btn-default">
		</div>
		
		
		<div class="form-group">
			<label for="stageArea">면적(m^2)</label><br>
			<input class="form-control" type="number" name="stageArea" id="stageArea" required="required"><br>
		</div>
		
		<div class="form-group">
			<label for="instrument">구비된 악기</label>
			<input class="form-control" type="text" name="stageInstrument" id="instrument">
		</div>
		
		<div class="form-group">
			<label for="content">글 내용</label><br>
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
					<label>O : </label><input type="radio" name="stageParking" id="stageParking" value="0">
					<label>X : </label><input type="radio" name="stageParking" id="stageParking" value="1">
				</td>
				<td>
					<label>O : </label><input type="radio" name="stageDrinking" id="stageDrinking" value="0">
					<label>X : </label><input type="radio" name="stageDrinking" id="stageDrinking" value="1">
				</td>
				<td>
					<label>O : </label><input type="radio" name="stageFoodSell" id="stageFoodSell" value="0">
					<label>X : </label><input type="radio" name="stageFoodSell" id="stageFoodSell" value="1">
				</td>
				<td>
					<label>O : </label><input type="radio" name="stageFoodRestriction" id="foodRestriction" value="0">
					<label>X : </label><input type="radio" name="stageFoodRestriction" id="foodRestriction" value="1">
				</td>
			</tr>
		</table>
		
		<div id="image">
			<label>사진등록</label>
			<input type="file" name="multiImage" id="multiImage">
		</div>
		<hr>
		<button class="btn btn-primary" type="button" onclick=addPicture(); >사진 추가 등록</button>
		<hr>
		<button class="btn btn-primary" type="submit" onclick=submitChk(); >등록</button>
	</form>
	
</div>
