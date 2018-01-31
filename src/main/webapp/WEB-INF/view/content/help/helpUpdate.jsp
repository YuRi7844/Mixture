<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery.timepicker.min.js"></script>



<body>
	<form action="${initParam.rootPath }/updateHelp.do"
		method="post" enctype="multipart/form-data">
	
 		<div class="form-group">
			<label for="no">게시글 번호</label> <input type="text"
				name="helpNum" id="no" class="form-control"
				value="${param.helpNum}" readonly>
		</div>
		
		<div class="form-group">
			<label for="id">수정할 카테고리 </label>
			<select name="helpCategory" class="form-control">
				<option>신고합니다</option>
				<option>문의합니다</option>
			</select>
		</div>
		
		<div class="form-group">
			<label for="title">수정할 게시글 제목 </label> <input type="text"
				name="helpTitle" id="title" class="form-control"
				value="${requestScope.help.helpTitle }">
		</div>
		
		<div class="form-group">
			<label for="content">수정할 공연 내용 </label>
			<textarea rows="15" cols="150" name="helpContent" >${requestScope.help.helpContent}</textarea>
		</div>

		<div class="form-group">
			<label for="helpImage">이미지</label> <input type="file"
				name="multiImage" id="multiImage" class="form-control">
				<input type="file"
				name="multiImage2" id="multiImage2" class="form-control">
		</div>

		<div class="form-group" style="display: none;">
			 <label for="userId">사용자 id </label> 
			<input type="text"
				name="helpUserId" id="helpUserId" class="form-control"
				value="<sec:authentication property="principal.userId"/>" readonly>
		</div>


		<button type="submit" class="btn btn-default">정보수정</button>
		<button type="button" onclick="history.back();">취소</button>

		<sec:csrfInput />
		<%-- csrf 토큰 --%>

	</form>