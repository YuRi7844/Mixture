<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- 
	관리자가 프리미엄 공연장 수정하는 페이지.
 -->

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

function addPicture(){
	var txt = '<input type="file" name="multiImage" id="multiImage">';
	$("#image").append(txt);
}

function submitChk(){
	document.getElementById("registerForm").submit();	
}


function deleteImage(form){
	$(form).parent().remove();
}



</script>


<div>
	<form class="" action="${initParam.rootPath }/admin/updatePremium.do" method="post" enctype="multipart/form-data" id="registerForm">
		<sec:csrfInput/>
		
		<input type="hidden" name="establishNum" value="${requestScope.map.premiumStage.establishNum }" required="required"><br>
		
		<span>사업자번호</span><br>
		<input type="text" name="operatorNo" value="${requestScope.map.premiumStage.operatorNo }" required="required"><br>
		
		<span>장소이름</span><br>
		<input type="text" name="stageName" value="${requestScope.map.premiumStage.stageName }" required="required"><br>
	
		<span>주소</span><br>
		<input type="text" name="stageLocation" value="${requestScope.map.premiumStage.stageLocation }" required="required"><br>
		
		<span>면적(m^2)</span><br>
		<input type="number" name="stageArea" value="${requestScope.map.premiumStage.stageArea }" required="required"><br>
		
		<span>구비된 악기</span>
		<input type="text" name="stageInstrument" value="${requestScope.map.premiumStage.stageInstrument }">
		
		<span>글 내용</span>
		<input type="text" name="stageContent" value="${requestScope.map.premiumStage.stageContent }">
	
		<!-- ################################################################### -->
		<input type="hidden" name="operatorUserId" value='<sec:authentication property="principal.userId"/>'>
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
		
		<div class="inline" id="image">
			<c:forEach items="${requestScope.map.imageList }" var="image" varStatus="number">
				<div>
				${image }
					<img src="${initParam.rootPath }/supplierImage/${image}" onerror='this.src="${initParam.rootPath }/supplierImage/no-image.png"'>
					<input type="hidden" name="oldImage" value="${image }">
					<br> 
					<button type="button" id="imageDelete" onclick=deleteImage(this);>삭제</button>
					<br>
				</div>
			</c:forEach>
		</div>
		
		<hr>
		
		<button class="btn btn-primary" type="button" onclick=addPicture(); >사진 추가 등록</button>
		
		<hr>
		
		<button class="btn btn-primary" type="button" onclick=submitChk(); >수정</button>
		
		<hr>
		
	</form>
</div>