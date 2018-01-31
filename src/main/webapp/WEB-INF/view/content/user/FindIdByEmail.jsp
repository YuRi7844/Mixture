<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#checkBtn").on("click",function(){
		$.ajax({
			"type":"get",
			"url":"${initParam.rootPath }/findIdByEmail.do",
			"data":{
				"email":$("#email").val()
			},
			"dataType":"text",
			"success":function(txt){
				txt+="<br><a href='${initParam.rootPath}/findPassword.do'>비밀번호 찾기</a>";
				$("#view").html(txt);				
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
	<span class="text-center">가입할 때 등록한 Email을 입력해주세요.</span>
	<input type="text" name="email" id="email">
	<button id="checkBtn">확인</button>
</div>
