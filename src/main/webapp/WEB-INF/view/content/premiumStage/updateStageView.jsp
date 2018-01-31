<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
	var txt = '<div class="form-group"><input class="form-control" type="file" name="multiImage" id="multiImage" required="required"></div>';
	$("#image").append(txt);
}

function submitChk(){
	// 사업자번호 체크.
	var pattern = /^[0-9]*$/;
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

function deleteImage(form){
	$(form).parent().remove();
	addPicture();
}

</script>

<div id="text">

</div>

<div>
	<form class="form-inline col-sm-offset-1" action="${initParam.rootPath }/producer/premiumStageUpdate.do" method="post" enctype="multipart/form-data" id="registerForm">
		<sec:csrfInput/>
		
		<input type="hidden" name="establishNum" value="${requestScope.map.premiumStage.establishNum }" required="required"><br>
		
		<div class="form-group">
			<label for="operatorNo">사업자번호</label><br>
			<input class="form-control" id="operatorNo" type="text" name="operatorNo" value="${requestScope.map.premiumStage.operatorNo }" required="required"><br>
		</div>
		
		<br>
		
		<div class="form-group">
			<label for="stageName">장소이름</label><br>
			<input class="form-control" id="stageName" type="text" name="stageName" value="${requestScope.map.premiumStage.stageName }" required="required"><br>
		</div>
		
		<br>
		
		<div class="form-group">
			<label for="performanceLocation">주소</label><br>
			<input type="text" name="stageLocation" id="performanceLocation" class="form-control" required="required" readonly="readonly" placeholder="버튼을 통해 장소를 검색해 주세요."><br>  
			<input type="button" id="searchStage" value="직접 검색" class="btn btn-default">
		</div>
		
		<br>
		
		<div class="form-group">
			<label for="stageArea">면적(m^2)</label><br>
			<input class="form-control" type="number" id="stageArea" name="stageArea" value="${requestScope.map.premiumStage.stageArea }" required="required"><br>
		</div>
		
		<br>
		
		<div class="form-group">
			<label for="stageInstrument">구비된 악기</label>
			<input class="form-control" type="text" id="stageInstrument" name="stageInstrument" value="${requestScope.map.premiumStage.stageInstrument }">
		</div>
		
		<br>
		
		<div class="form-group">
			<label for="stageContent">글 내용</label>
			<textarea class="form-control" id="stageContent" name="stageContent">${requestScope.map.premiumStage.stageContent }</textarea>
		</div>
		
		<br>
		
		<input type="hidden" name="operatorUserId" value='<sec:authentication property="principal.userId"/>'>
		
		<p><hr><p>
		
		<!-- ################################################################### -->
		
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
		
		<!-- 사진 -->
		<hr>
		<button class="btn btn-primary" type="button" onclick=addPicture(); >사진 추가 등록</button>
		<hr>
		
		
		
		<div class="inline col-sm-offset-1" id="image">
			<c:forEach items="${requestScope.map.imageList }" var="image" varStatus="number">
				<div class="row">
					<img style="width:200px; height:200px;" src="${initParam.rootPath }/supplierImage/${image}" onerror='this.src="${initParam.rootPath }/supplierImage/no-image.png"'>
					<input type="hidden" name="oldImage" value="${image }">
					<br> 
					<button class="btn-danger" type="button" id="imageDelete" onclick=deleteImage(this);>삭제</button>
					<br>
				</div>
			</c:forEach>
		</div>
		
		<hr>
		
		<button class="btn btn-primary" type="button" onclick=submitChk(); >등록</button>
		
		<hr>
		
	</form>
	
	<br>
	
	<form action="${initParam.rootPath }/producer/deleteStage.do" method="post">
		<sec:csrfInput/>
		<input type="hidden" name="userId" value='<sec:authentication property="principal.userId"/>'>
		<input type="hidden" name="establishNum" value="${requestScope.map.premiumStage.establishNum }">
		<button class="btn btn-danger" type="submit" id="deleteBtn">삭제</button>
	</form>
		
</div>