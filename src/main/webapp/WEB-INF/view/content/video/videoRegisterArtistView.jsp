<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#registBtn").on("click",function(){
			var txt = rink($("#videoLink").val());
			$("#videoLink").val(txt);
			document.getElementById("registForm").submit();
		});
	
		$("#selectStage").on("click", function(){
			
		});
		
		$("#searchStage").on("click", function(){
			var address = "${initParam.rootPath}/searchStageByAddress.do"; //doGet()방식으로 요청
			left1 = (screen.availWidth - 600) / 2;
			top1 = (screen.availHeight - 500) / 2;
			window.open(address, "newWin", 'width=800 ,height=600 ,top=' + top1 + ',left=' + left1 + ',resizable=no');
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

<h2>아티스트 홍보영상 등록</h2>
<form action="${initParam.rootPath }/createVideo.do" id="registForm" method="post">
	<sec:csrfInput/>
	<div class="form-group">
		<label for="videoTitle">영상 제목</label> <input type="text"
			name="videoTitle" id="videoTitle" class="form-control"
			required="required">
	</div>
	<div class="form-group">
		<label for="videoRink">영상 링크</label> <input type="text"
			name="videoLink" id="videoLink" class="form-control"
			required="required">
	</div>
	
	<div class="form-group">
			<label>공연장 주소</label>
			<input type="text" id="performanceLocation" name="videoLocation" class="form-control" required="required" readonly="readonly" placeholder="버튼을 통해 장소를 검색해 주세요.">
			<%--<input type="button" id="selectStage" value="대관한 장소 선택" class="btn btn-default col-sm-2"> --%>
			<input type="button" id="searchStage" value="직접 검색" class="btn btn-default col-sm-1"><br>
		</div>
	
	<div class="form-group">
		<label for="videoDate">공연날짜</label> <input type="date"
			name="videoDate" id="videoDate" class="form-control"
			required="required">
	</div>
	<div class="form-group">
		<label for="videoArtist">아티스트명</label> <input type="text"
			name="videoArtist" id="videoArtist" class="form-control"
			required="required">
	</div>
	<div class="form-group">
		<input type="hidden" id ="videoCategory" name="videoCategory" value="artist" class="form-control">
	</div>
	<label>추가 정보 입력</label>
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
	<button id="registBtn" class="btn btn-default">등록</button>
	<button type="button" class="btn btn-default"
		onclick="location.href='${initParam.rootPath}/videoListCategory.do?category=artist'">취소</button>
</form>
