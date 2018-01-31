<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#modifyBtn").on("click",function(){
			var txt = rink($("#videoLink").val());
			$("#videoLink").val(txt);
			document.getElementById("registForm").submit();
		});
	});
	
	function rink(txt){
		var video_id = txt.split('v=')[1];
	    var ampersandPosition = video_id.indexOf('&');
	    if(ampersandPosition != -1) {
	      video_id = video_id.substring(0, ampersandPosition);
	    }
	    return video_id;
	}
</script>

<h2>아티스트홍보영상 수정</h2>
<div class="row">
	<form class="col-sm-8 center-block" action="${initParam.rootPath }/updateVideoInfo.do" id="registForm" method="post">
		<sec:csrfInput />
		<!-- 
			상세보기 페이지에서 수정으로 넘어올 때 컨트롤러를 통해 기존 video 정보를 받아서 넣어준다.
			영상 번호를 controller로 넘겨서 기존 video 정보를 받아서 정보를 set를 통해 바꾸어서 sevice, dao를 해준다.
		 -->
		<div class="form-group">
			<input type="hidden" name="videoNo" value="${requestScope.video.videoNo }" class="form-control">
			<input type="hidden" name="videoCategory" value="${requestScope.video.videoCategory }" class="form-control">
			<input type="hidden" name="videoArtist" value="${requestScope.video.videoArtist }" class="form-control">
			<input type="hidden" name="videoRegTime" value=
				"<fmt:formatDate value="${requestScope.video.videoRegTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
			class="form-control">
			<input type="hidden" name="videoHits" value="${requestScope.video.videoHits }" class="form-control">
		</div>
		<div class="form-group">
			<label for="videoTitle">영상 제목</label> 
			<input type="text" name="videoTitle" id="videoTitle" class="form-control" required="required">
		</div>
		<div class="form-group">
			<label for="videoLink">영상 링크</label> 
			<input type="text" name="videoLink" id="videoLink" class="form-control" required="required">
		</div>
		<div class="form-group">
			<label for="videoLocation">공연장소</label> 
			<input type="text" name="videoLocation" id="videoLocation" class="form-control" required="required">
		</div>
	
		<div class="form-group">
			<label for="videoDate">공연날짜</label> 
			<input type="date" name="videoDate" id="videoDate" class="form-control" required="required">
		</div>
		
			
		
		<label>내용 입력</label>
		<div class="form-group">
			<textarea rows="15" cols="150" name="videoContent"
				placeholder="영상에 대한 정보를 입력하세요."></textarea>
		</div>
	
		<%-- 사용자 id --%>
		<div class="form-group">
			<sec:authorize access="isAuthenticated()">
				<input type="hidden" name="videoUserId" id="videoUserId"
					class="form-control"
					value='<sec:authentication property="principal.userId"/>'>
			</sec:authorize>
		</div>
		<button id="modifyBtn" class="btn btn-default">수정</button>
		<button type="button" class="btn btn-default"
			onclick="location.href='${initParam.rootPath}/index.do'">취소</button>
	</form>
</div> 

<form action="${initParam.rootPath }/deleteVideo.do" method="post">
	<sec:csrfInput/>
	<h1>영상 삭제 폼</h1>
	<input type="hidden" name="videoNo" value="${requestScope.video.videoNo }">
	<button>삭제</button>
</form>
</body>
</html>