<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">


function submitChk(){
	// 비밀번호 체크.
	var pw = $("#firstInsert").val();
	var pwck = $("#secondInsert").val();
	if(pw!=pwck){
		alert("비밀번호가 일치하지 않습니다.");
		$("#firstInsert").val("");
		$("#secondInsert").val("");
		$("#firstInsert").focus();
		return false;
	}
	// 이름체크
	var name = $("#name").val();
	
	var pattern1 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	var pattern2 = /[a-z]/;
	var pattern3 = /[\s]/;
	
	if(!pattern1.test(name) && !pattern2.test(name)){
		alert("이름은 한글과 영문으로만 입력해주세요.");
		$("#name").val("");
		$("#name").focus();
		return false;
	} else if(pattern3.test(name)){
		alert("이름에 공백이 있습니다.");
		$("#name").val("");
		$("#name").focus();
		return false;
	}
	
	// 전화번호 체크.
	var phonenum = $("#phoneNumber").val();
	
	var pattern = /^[0-9]*$/;
	
	if(!pattern.test(phonenum)){
		alert("번호는 숫자로만 입력해주세요.");
		$("#phoneNumber").val("");
		$("#phoneNumber").focus();
		return false;
	} else if (!(phonenum.length>=10 && phonenum.length<=11)) {
		alert("휴대폰번호는 10자리 혹은 11자리로 입력해주세요!");
		$("#phoneNumber").val("");
		$("#phoneNumber").focus();
		return false;
	}

	$("#updateForm").submit();
}



// mypage로 돌아가는 function
function backMyPage(){
	document.location.href="${initParam.rootPath }/member/passwordCheck.do";
}
</script>

<style>

#insert{
	text-align:center;
}

</style>
<div class="container">
	<div class="row">
		<h2 class="text-center">${requestScope.user.userId }님의 회원 정보 수정</h2>
	</div>
	<br><br>
	<!-- 전송 form -->
	<form class="center-block" id="updateForm" action="${initParam.rootPath }/member/updateMember.do" method="post">
	
		<sec:csrfInput/>
		<input type="hidden" name="userId" value="${requestScope.user.userId }">
		
		<div class="form-group row" id="insert">
			<label class="col-sm-3" for="firstInsert">새로운 비밀번호 : </label> 
			<input class="col-sm-3 form-control" type="password" id="firstInsert" name="newpassword" required="required">
		</div>
		
		<div class="form-group row" id="insert">
			<label class="col-sm-3" for="secondInsert">비밀번호 재입력 : </label> 
			<input class="col-sm-3 form-control" type="password" id="secondInsert" name="checkPassword" required="required">
			<div id="checkPassword">
			
			</div>
		</div>
		
		
		<!-- Username -->
		<div class="form-group row" id="insert">
			<label class="col-sm-3" for="name">이름 : </label>
			<input class="col-sm-3 form-control" type="text" id="name" name="userName" required="required" placeholder="${requestScope.user.userName }">
		</div>
		
		<!-- userAddress -->
		<div class="form-group row" id="insert">
			<label class="col-sm-3" for="address">주소 : </label>
			<input class="col-sm-3 form-control"type="text" id="address" name="userAddress" required="required" placeholder="${requestScope.user.userAddress }">
		</div>
		
		<!-- userPhoneNum -->
		<div class="form-group row" id="insert">
			<label class="col-sm-3" for="phoneNumber">전화번호 : </label>
			<input class="col-sm-3 form-control" type="text" id="phoneNumber" name="userPhoneNum" required="required" placeholder="${requestScope.user.userPhoneNum }">
		</div>
		
		<div class="form-group row" id="insert">
			<label class="col-sm-3" for="email">이메일 : </label>
			<input class="col-sm-3 form-control" type="text" id="email" name="email" required="required" placeholder="${requestScope.user.email }">	
		</div>
		
		<button class="btn btn-warning" type="button" onclick=submitChk();>수정</button>
		<button class="btn btn-danger" id="backBtn" onclick=backMyPage(); >취소</button>
		
	</form>
</div>