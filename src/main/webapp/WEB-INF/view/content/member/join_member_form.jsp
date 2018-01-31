<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#duplicatedCheckBtn").on("click",function(){
		var id = $("#id").val();
		
		$.ajax({
			"url":"${initParam.rootPath }/idDuplicatedCheck.do",
			"type":"get",
			"data":{
				"id":$('#id').val(),
				"${_csrf.parameterName}":"${_csrf.token}"		
			},
			"dataType":"text",
			"beforeSend":function(){
				var flag = idCheck();
				if(flag==false){
					return false;
				}
			},
			"success":function(message){
				/* alert(message); */
				if(message=='new'){
					alert("사용가능한 아이디 입니다.");
					$("#id").addClass("disable");
					$("#idCheck").val("true");
					document.getElementById("id").readOnly=true;
				} else if(message=='duplicated'){
					alert('이미 사용중인 아이디 입니다.');
					document.getElementById("id").value='';
					$("#idCheck").val("false");
				} else if(message=='empty'){
					alert('사용할 아이디를 입력해주세요');
					document.getElementById("id").value='';
					$("#idCheck").val("false");
				}
			},
			"error":function(a,b,c){
				alert(a);
				alert(b);
				alert(c);
			}
		});
	});
	
	$("#joinBtn").on("click",function(){
		if($("#idCheck").val()=='false'){
			alert("ID 중복체크를 해주세요");
		} else if(!nameCheck()){
			return false;
		} else if(!phonenumCheck()){
			return false;
		} else{
			$("#submitForm").submit();
		}
	});
});

// id : 한글 / 특수문자 / 공백 체크
function idCheck(){
	var str = $("#id").val();
	
	var pattern1 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	var pattern2 = /[~!@#$%^&*()_+|<>?:{}]/;
	var pattern3 = /\s/;
	
	if(pattern1.test(str)){
		alert("한글은 사용하실 수 없습니다.");
		$("#id").val("");
		return false;
	}
	
	if(pattern2.test(str)){
		alert("특수문자는 사용하실 수 없습니다.");
		$("#id").val("");
		return false;
	}
	if(pattern3.test(str)){
		alert("공백은 사용하실 수 없습니다.");
		$("#id").val("");
		return false;
	}
}

function nameCheck(){
	var str = $("#name").val();
	
	var pattern1 = /[^(가-힣a-zA-Z)]/;
	var pattern2 = /[a-z]/;
	var pattern3 = /[\s]/;
	
	if(!(pattern1.test(str)||pattern2.test(str))){
		alert("이름은 한글과 영문으로만 입력해주세요.");
		$("#name").val("");
		$("#name").focus();
		return false;
	} else if(pattern3.test(str)){
		alert("이름에 공백이 있습니다.");
		$("#name").val("");
		$("#name").focus();
		return false;
	}
	
}

function phonenumCheck(){
	var str = $("#phoneNumber").val();
	var pattern = /^[0-9]*$/;
	
	if(!pattern.test(str)){
		alert("번호는 숫자로만 입력해주세요.");
		$("#phoneNumber").val("");
		$("#phoneNumber").focus();
	} else if (!(str.length>=10 && str.length<=11)) {
		alert("휴대폰번호는 10자리 혹은 11자리로 입력해주세요!");
		$("#phoneNumber").val("");
		$("#phoneNumber").focus();
	}
}

</script>
<input type="hidden" value="false" id="idCheck">
<br><br>
<div class="container text-center" style="margin-top:15%;">
	<h1>회원가입</h1>
	<form action="${initParam.rootPath }/join_member.do" id="submitForm" method="post">
		<div class="form-group">
			<label for="id">ID</label>
			<input type="text" name="userId" id="id" required="required" class="form-control">
			<br>
			<button type="button" id="duplicatedCheckBtn" class="btn-primary">id 중복체크</button>
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> 
			<input type="password" name="password" required="required" class="form-control">
		</div>
		<div class="form-group">
			<label for="name">이름</label> 
			<input type="text" name="userName" id="name" required="required" class="form-control">
		</div>
		<div class="form-group">
			<label for="address">주소</label> 
			<input type="text" name="userAddress" id="address" required="required" class="form-control">
		</div>
	
		<div class="form-group">
			<label for="phoneNumber">핸드폰번호</label> 
			<input type="text" name="userPhoneNum" id="phoneNumber" required="required" class="form-control">
		</div>
		<div class="form-group">
			<label for="email">이메일주소</label> 
			<input type="text" name="email" id="email" class="form-control" required="required">
		</div>
		<%-- csrf 토큰 --%>
		<sec:csrfInput />
		<button id="joinBtn" type="button" class="btn-primary">가입</button>
	</form>
</div>