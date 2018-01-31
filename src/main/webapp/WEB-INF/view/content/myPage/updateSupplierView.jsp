<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript">
function backFtn(){
	
}
</script>

<div>
	<form action="${initParam.rootPath }/producer/updateSupplier.do" method="post" enctype="multipart/form-data">
		<sec:csrfInput/>
		<!-- 사업장번호 -->
		<span>사업장번호</span>
		<input type="text" name="establishNum" value="${requestScope.supplier.establishNum }" required="required">
		<br>
		<!-- 사업자번호 -->
		<span>사업자번호</span>
		<input type="text" name="operatorNum" value="${requestScope.supplier.operatorNum }" required="required">
		<br>
		<!-- 장소명 -->
		<span>장소명</span>
		<input type="text" name="stageName" value="${requestScope.supplier.stageName }" required="required">
		<br>
		<!-- 주소 -->
		<span>주소</span>
		<input type="text" name="stageLocation" value="${requestScope.supplier.stageLocation }" required="required">
		<br>
		<!-- 면적 -->
		<span>면적</span>
		<input type="text" name="stageArea" value="${requestScope.supplier.stageArea }" required="required">
		<br>
		<!-- 사진 -->
		<span>사진</span>
		<input type="file" name="multiImage" value="${requestScope.supplier }">
		<br>
		
		
		<!-- 사용자정보 : hidden -->
		<input type="hidden" name="userId" value='<sec:authentication property="principal.userId"/>'>
		
		<button>수정</button>
		<button onclick=backFtn(); >취소</button>
	</form>
</div>
