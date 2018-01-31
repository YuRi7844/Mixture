<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${initParam.rootPath}/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
window.onload = function followSearch(){
	$.ajax({
		"type":"post",
		"url":"${initParam.rootPath }/searchFollow.do",
		"data":{
			'${_csrf.parameterName}':'${_csrf.token}',
			'userId':'${requestScope.map.userId }',
			'artistId':'${requestScope.map.artist.artistId }'
		},
		"dataType":"text",
		"success":function(txt){
			if(txt=='follow'){
				$("#follow").html('');
				// follow 했으면 follow 버튼
				txt='<button type="button" id="followBtn" class="btn btn-primary">팔로우취소</button>';
				$("#follow").html(txt);
			} else if (txt=='notFollow'){
				
				// follow 하지 않으면 unfollow 버튼
				txt='<button type="button" id="followBtn" class="btn btn-primary">팔로우</button>';					
				$("#follow").html(txt);
			}
		},
		'error':function(a,b,c){
			alert(a);
			alert(b);
			alert(c);
		}
	});
}

$(document).ready(function(){
	$("#follow").on("click","#followBtn", function(){
		if($("#followBtn").text()=='팔로우취소'){
			$.ajax({
				"type":"post",
				"url":"${initParam.rootPath }/member/unfollow.do",
				"data":{
					'${_csrf.parameterName}':'${_csrf.token}',
					'userId':'${requestScope.map.userId }',
					'artistId':'${requestScope.map.artist.artistId }'
				},
				"dataType":"text",
				"success":function(txt){
					alert(txt);
					txt='<button id="followBtn" class="btn btn-primary">팔로우</button>';
					$("#follow").html(txt);
				},
				"error":function(a,b,c){
					alert(a);
					alert(b);
					alert(c);
				}
				
			});
		} else {
			$.ajax({
				"type":"post",
				"url":"${initParam.rootPath }/member/follow.do",
				"data":{
					'${_csrf.parameterName}':'${_csrf.token}',
					'userId':'${requestScope.map.userId }',
					'artistId':'${requestScope.map.artist.artistId }'
				},
				"dataType":"text",
				"success":function(txt){
					alert(txt);
					txt='<button id="followBtn" class="btn btn-primary">팔로우취소</button>';
					$("#follow").html(txt);
				},
				"error":function(a,b,c){
					alert(a);
					alert(b);
					alert(c);
				}
			});
		}
	});
	
});

function goDetail(root, no){
	document.location.href= root+'/artistPerformanceDetailView.do?performanceNo='+no
}

function goVideoDetail(root, no){
	document.location.href= root+'/readVideoByVideoNo.do?videoNo='+no
}

</script>
<style>

img{
	height:50%;
	width:50%;
}

h2 {
    color: pink;
    text-align:center;
    font-family: "Times New Roman", Times, serif;
    font-weight:bold;
}

span{
	font-size:130%;
}

th, th{
	cursor:pointer;
	padding-left:60px;
	text-align:center;
	word-break: keep-all;	
}

tr{
	text-align:center;
	word-break: keep-all;	
}

tr:nth-child(even) {
	background-color: #ffe6ee ;
}

</style>
<!-- 아티스트 정보 (프로필) -->
<div class="container-inline" style="padding-top:40px; padding-left:40px;">
	<h2>${requestScope.map.artist.artistName } 프로필</h2>
	<div class="row">
		<div class="col-sm-5" style="vertical-align:middel;text-align:center;">
			<!-- 아티스트 프로필 사진 -->
			<img style="height:200px; width:280px;" onerror='this.src="${initParam.rootPath }/artistImage/no-image.png"' src="${initParam.rootPath }/artistImage/${requestScope.map.artist.artistImage }">
		</div>
		<div class="col-sm-3" style="vertical-align:middle;padding-top:25px;">
			<!-- 아티스트 정보 뿌리기 -->
			<span>아티스트 명 : ${requestScope.map.artist.artistName }</span><br>
			<span>장르 : ${requestScope.map.artist.performance }</span><br>
			<span>프로필 : ${requestScope.map.artist.profile }</span><br>
			<span>
			<c:choose> 
			<c:when test="${requestScope.map.artist.artistMembers!=null}">그룹명 : ${requestScope.map.artist.artistMembers }</c:when>
			<c:otherwise></c:otherwise></c:choose></span><br>
			
			<span><c:choose>
			<c:when test="${requestScope.map.artist.artistSns!=null}">아티스트 SNS : ${requestScope.map.artist.artistMembers }</c:when>
			<c:otherwise>아티스트 SNS : 없음</c:otherwise></c:choose></span><br>
			<span>팔로우 수 : ${requestScope.map.followerCount }명</span>	
		</div>
		
		<c:if test="${requestScope.map.artist.artistId ne requestScope.map.userId }">
			<sec:authorize access="isAuthenticated()">
				<div class="col-sm-2" style="padding-top:80px;" id="follow">
					<!-- Follow Button -->
					<button id="followBtn" class="btn btn-primary">팔로우</button>
		</div>
			</sec:authorize>
		</c:if>
	</div>
</div>
<hr>

<!-- 아티스트 공연정보 조회 -->
<div class="container" style="padding:40px;">
	<h2>${requestScope.map.artist.artistName }님이 올린 공연</h2>
	<table style="border:solid orange 1px; padding: 15px;">
	<tr style="bottom-border:solid orange 1px;">
		<th>공연제목</th>
		<th>장소</th>
		<th>공연날짜</th>
		<th>공연내용</th>
	</tr>
	<br>
			<c:forEach items="${requestScope.map.performanceList }" var="performance">
			<tr>
				<td class="col-sm-2" onclick="goDetail('${initParam.rootPath }',${performance.performanceNo })">
					<img src="${initParam.rootPath }/performanceImage/${performance.performanceImage }" onerror='this.src="${initParam.rootPath }/performanceImage/no-image.png"' style="width:100px; height:100px;"></td>
				<td class="col-sm-2" onclick="goDetail('${initParam.rootPath }',${performance.performanceNo })">${performance.performanceTitle }</td>
				<td class="col-sm-2" onclick="goDetail('${initParam.rootPath }',${performance.performanceNo })">${performance.performanceLocation }</td>
				<td class="col-sm-2" onclick="goDetail('${initParam.rootPath }',${performance.performanceNo })"><fmt:formatDate value="${performance.performanceDate }" pattern="yyyy-MM-dd HH시mm분"/></td>
			</tr>
		</c:forEach>
	</table>
</div>
<hr>



<!-- 아티스트 공연홍보영상 -->
<div class="container" style="padding:40px;">
	<h2>${requestScope.map.artist.artistName }님이 올린 영상</h2>
	<br>
	<table style="border:solid orange 1px; width:100%;">
		<tr>
			<th class="col-sm-2">영상 미리보기</th>
			<th class="col-sm-2">제목</th>
			<th class="col-sm-2">장소</th>
			<th class="col-sm-2">등록시간</th>
		</tr>
		<c:forEach items="${requestScope.map.videoList }" var="video">
			<tr onclick="goVideoDetail('${initParam.rootPath}',${video.videoNo })">
				<td class="col-sm-3">
					<img src="https://img.youtube.com/vi/${video.videoLink }/hqdefault.jpg" onerror='this.src="${initParam.rootPath }/performanceImage/no-image.png"' style="width:100px; height:100px;">
				</td>	
				<td class="col-sm-2">
					${video.videoTitle }
				</td>
				<td class="col-sm-2">
					${video.videoLocation }
				</td>
				<td class="col-sm-2"><fmt:formatDate value="${video.videoRegTime }" pattern="yyyy-MM-dd   HH시 mm분"/></td>
			</tr>
		</c:forEach>
	</table>
</div>
<br>
