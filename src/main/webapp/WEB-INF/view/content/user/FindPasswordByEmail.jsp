<%@ page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#checkBtn").on("click",function(){
		$.ajax({
			"url":"${initParam.rootPath }/findPasswordByEmail.do",
			"type":"get",
			"data":{
				"email" : $("#email").val(),
				"id" : $("#id").val()
			},
			"success":function(txt){
				if(txt=='success'){
					var text = '메일로 임시 비밀번호를 발송하였습니다.<br> 로그인 후 회원정보를 수정하세요.';
					$("#view").html(text);
				} else if(txt=='idNotFound') {
					alert("존재하지 않는 ID입니다.");				
				} else if(txt=='emailNotFound'){
					alert("존재하지 않는 Email주소입니다.");
				}
			},
			"error":function(a,b,c){
				alert(a);
				alert(b);
				alert(c);
			}
		});		
	});
});

</script>

<div class="container" id="view">
	<h2>비밀번호 찾기</h2><br>
	<span>ID</span>
	<input type="text" name="id" id="id"><br>
	<span>E-mail</span>
	<input type="text" name="email" id="email"><br>
	<button id="checkBtn">확인</button>
</div>