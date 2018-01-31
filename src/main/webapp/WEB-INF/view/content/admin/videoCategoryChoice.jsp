<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>
$(document).ready(function(){
	$("#categoryBtn").on("click",function(){
		if($("#category").val()==''){
			alert("조회할 내용을 선택해주세요.");
			return;
		} else {
			$(this).parent().submit();
		}
	});
});

</script>

<form action="${initParam.rootPath }/admin/videoList.do" method="post">
	<sec:csrfInput/>
	<select id="category" name="category">
		<option value="">조회할 영상 종류를 선택해주세요.</option>
		<option value="artist">홍보영상</option>
		<option value="performance">공연영상</option>
		<option value="practice">연습영상</option>
	</select>
	<button type="button" id="categoryBtn" class="btn btn-primary">조회</button>
</form>