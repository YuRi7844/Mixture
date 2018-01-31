<%@page import="org.springframework.web.context.annotation.RequestScope"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.buskstop.vo.User"%>
<%@page import="com.buskstop.vo.Video"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	upVideoHits();
	likeChk();
	
	$("#enterVideoCommentBtn").on("click",function() {
		var videoNo= "${requestScope.map.video.videoNo }";
	
		$.ajax({
			"url": "${initParam.rootPath}/member/enterVideoComment.do",
			"type":"POST",
			"data":{"videoNo":"${requestScope.map.video.videoNo }",
					"videoComment":$("#videoComment").val(),
					"${_csrf.parameterName}":"${_csrf.token}"
			},
			"dataType":"json",
			"beforeSend":function(){
				if($("#videoComment").val()==''){
					alert("댓글을 입력해주세요.");
					document.getElementById("#videoComment").focus();
					return false;
				}
			},
			
			"success":function(list){
				var txt="";
				$.each(list,function(){
					txt+="<div id='"+this.videoCommentUserId+"'>";
					txt+=" / 작성자 : "+this.videoCommentUserId;
					txt+=" / 등록 일자 : "+this.videoCommentRegTime;
					if(this.videoCommentUserId=='${requestScope.map.userId }'){
						txt+='<a onclick="editCommentText('+this.videoCommentNo+',\''+this.videoComment+'\');"> 수정 </a>';
						txt+='<a onclick="deleteComment('+this.videoCommentNo+');"> 삭제 </a>';
					}txt+='<div class="videoComment'+this.videoCommentNo+'"> <p> 내용 : '+this.videoComment +'</p></div>';
						txt+="<input type='hidden' name='videoNo' value='${requestScope.map.video.videoNo}'>";
					
					txt+="</div></div>";
				});
				$("#videoCommentList").html(txt);
				$("#videoCommentList").removeClass('hidden');		
    			$("#providerBtn").text('댓글숨기기');
    			document.getElementById("videoComment").value="";
			}
		});		
	});
   
    $("#providerBtn").on("click",function(){
    	alert($("#providerBtn").text());
    	
		if($("#providerBtn").text()=='댓글보기'){
			$.ajax({
	    		"url":"${initParam.rootPath }/readVideoComment.do",
	    		"type":"POST",
	    		"data":{
	    			"videoNo":"${requestScope.map.video.videoNo }",
	    			"${_csrf.parameterName}":"${_csrf.token}"
	    		},
	    		"dataType":"json",
	    		"success":function(list){
	    			var txt="";
					$.each(list,function(){
						txt+="<div id='"+this.videoCommentUserId+"'>";
						txt+=" / 작성자 : "+this.videoCommentUserId;
						txt+=" / 등록 일자 : "+this.videoCommentRegTime;
						if(this.videoCommentUserId=='${requestScope.map.userId }'){
							txt+='<a onclick="editCommentText('+this.videoCommentNo+',\''+this.videoComment+'\');"> 수정 </a>';
							txt+='<a onclick="deleteComment('+this.videoCommentNo+');"> 삭제 </a>';
							txt+='<div class="videoComment'+this.videoCommentNo+'"> <p> 내용 : '+this.videoComment +'</p></div>';
							txt+="<input type='hidden' name='videoNo' value='${requestScope.map.video.videoNo}'>";
						}
						txt+="</div></div>";
					});
	    			$("#videoCommentList").html(txt);
	    			
	    			$("#videoCommentList").removeClass("hidden");		
	    			$("#providerBtn").text('댓글숨기기');
	    		},
	    		"error":function(a, b, c){
	    			alert(c); 
	    		}
	    	});
		} else {
			$("#videoCommentList").addClass("hidden");
			$("#providerBtn").text('댓글보기');
		}
    });
    
    // 좋아요 버튼 ajax.
    $("#likeBtn").on("click",function(){
		$.ajax({
			"url":"${initParam.rootPath }/member/videoLike.do",
			"type":"post",
			"data":{
				"videoNo":"${requestScope.map.video.videoNo }",
				"${_csrf.parameterName}":"${_csrf.token}"
			},
			"success":function(num){
				if(num=='1'){
					$("#likeBtn").css("color","red");
				} else if(num=='0'){
					$("#likeBtn").css("color","black");
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

function refreshComment(){
	if($("#providerBtn").text()=='댓글보기'){
		$.ajax({
    		"url":"${initParam.rootPath }/readVideoComment.do",
    		"type":"POST",
    		"data":{
    			"videoNo":"${requestScope.map.video.videoNo }",
    			"${_csrf.parameterName}":"${_csrf.token}"
    		},
    		"dataType":"json",
    		"success":function(list){
    			var txt="";
				$.each(list,function(){
					txt+="<div id='"+this.videoCommentUserId+"'>";
					txt+=" / 작성자 : "+this.videoCommentUserId;
					txt+=" / 등록 일자 : "+this.videoCommentRegTime;
					if(this.videoCommentUserId=='${requestScope.map.userId }'){
						txt+='<a onclick="editCommentText('+this.videoCommentNo+',\''+this.videoComment+'\');"> 수정 </a>';
						txt+='<a onclick="deleteComment('+this.videoCommentNo+');"> 삭제 </a>';
						txt+='<div class="videoComment'+this.videoCommentNo+'"> <p> 내용 : '+this.videoComment +'</p></div>';
						txt+="<input type='hidden' name='videoNo' value='${requestScope.map.video.videoNo}'>";
					}
					txt+="</div></div>";
				});
    			$("#videoCommentList").html(txt);
    			
    			$("#videoCommentList").removeClass("hidden");		
    			$("#providerBtn").text('댓글숨기기');
    		},
    		"error":function(a, b, c){
    			alert(c); 
    		}
    	});
	} else {
		$("#videoCommentList").addClass("hidden");
		$("#providerBtn").text('댓글보기');
	}
}

function deleteComment(videoCommentNo){
	$.ajax({
		"url":"${initParam.rootPath}/member/deleteComment.do",
		"type":"post",
		"data":{
			"videoCommentNo":videoCommentNo,
			'${_csrf.parameterName}':'${_csrf.token}'
		},
		"dataType":"text",
		"success": function(list){
			alert("댓글이 삭제되었습니다.");
			var txt="";
			$.each(list,function(){
				txt+="<div id='"+this.videoCommentUserId+"'>";
				txt+=" / 작성자 : "+this.videoCommentUserId;
				txt+=" / 등록 일자 : "+this.videoCommentRegTime;
				if(this.videoCommentUserId=='${requestScope.map.userId }'){
					txt+='<a onclick="editCommentText('+this.videoCommentNo+',\''+this.videoComment+'\');"> 수정 </a>';
					txt+='<a onclick="deleteComment('+this.videoCommentNo+');"> 삭제 </a>';
					txt+='<div class="videoComment'+this.videoCommentNo+'"> <p> 내용 : '+this.videoComment +'</p></div>';
					txt+="<input type='hidden' name='videoNo' value='${requestScope.map.video.videoNo}'>";
				}
				txt+="</div></div>";
			});
			$("#videoCommentList").html(txt);
			$("#videoCommentList").addClass("hidden");
			$("#providerBtn").text('댓글보기');
			refreshComment();
		},
        "error":function(){
        	alert("에러 뜸 ㅠㅠ");
        }
	});
}

function editCommentText(videoCommentNo,videoComment){
	
    var output ="";
    	output += '<div class="input-group">';
    	output += '<input type="text" class="form-control" name="videoComment'+videoCommentNo+'" value="'+videoComment+'"/>';
    	output += '<span class="input-group-btn">'
    	output += '<button class="btn btn-default" type="button" onclick="editComment('+videoCommentNo+');">수정</button>';
    	output += '<button class="btn btn-default" type="button" onclick="listComment();">수정 취소</button>';
    	output += ' </span></div>';
       
       $(".videoComment"+videoCommentNo).html(output);
}

function listComment(){
	$.ajax({
		"url": "${initParam.rootPath}/member/enterVideoComment.do",
		"type":"POST",
		"data":{"videoNo":"${requestScope.map.video.videoNo }",
				"videoComment":$("#videoComment").val(),
				"${_csrf.parameterName}":"${_csrf.token}"
		},
		"dataType":"json",
		
		"success":function(list){
			var txt="";
			$.each(list,function(){
				txt+="<div id='"+this.videoCommentUserId+"'>";
				txt+=" / 작성자 : "+this.videoCommentUserId;
				txt+=" / 등록 일자 : "+this.videoCommentRegTime;
				if(this.videoCommentUserId=='${requestScope.map.userId }'){
					txt+='<a onclick="editCommentText('+this.videoCommentNo+',\''+this.videoComment+'\');"> 수정 </a>';
					txt+='<a onclick="deleteComment('+this.videoCommentNo+');"> 삭제 </a>';
					txt+='<div class="videoComment'+this.videoCommentNo+'"> <p> 내용 : '+this.videoComment +'</p></div>';
					txt+="<input type='hidden' name='videoNo' value='${requestScope.map.video.videoNo}'>";
				}
				txt+="</div></div>";
			});
			$("#videoCommentList").html(txt);
			$("#videoCommentList").removeClass('hidden');		
			$("#providerBtn").text('댓글숨기기');
			document.getElementById("videoComment").value="";
		}
	});
}

function editComment(videoCommentNo){
	var updatevideoComment = $("[name=videoComment"+videoCommentNo+"]").val();
	$.ajax({
		"url": "${initParam.rootPath }/member/editVideoComment.do",
	    "type": "post",
	    "data" : {
	    	"videoCommentNo":videoCommentNo,
	    	"videoComment":updatevideoComment,
	    	'${_csrf.parameterName}':'${_csrf.token}'
	    },
	    "dataType":"text",
	    "success" : function(list){
	    	alert("댓글이 수정되었습니다.");
	    	$("#videoCommentList").addClass("hidden");
			$("#providerBtn").text('댓글보기');
			refreshComment();
	    },
	    "error":function(){
	    	alert("댓글을 입력해주세요.");
	    }
		
	});
}

// user의 영상 좋아요 체크.
function likeChk(){
	$.ajax({
		"type":"post",
		"url":"${initParam.rootPath }/member/likeCheck.do",
		"data":{
			"videoNo":"${requestScope.map.video.videoNo }",
			"${_csrf.parameterName}":"${_csrf.token}"
		},
		"dataType":"text",
		"success":function(number){
			if(number =='0'){
				$("#likeBtn").css("color","black");
			} else if (number == '1') {
				$("#likeBtn").css("color","red");
			}
		},
		"error":function(a,b,c){
			alert(a);
			alert(b);
			alert(c);
		}
	});
}

// 조회수 올리기
function upVideoHits(){
	$.ajax({
		"type":"post",
		"url":"${initParam.rootPath }/updateVideoHits.do",
		"data":{
			"videoNo":"${requestScope.map.video.videoNo }",
			"${_csrf.parameterName}":"${_csrf.token}"
		},
		"dataType":"text",
		"success":function(txt){
		},
		"error":function(a,b,c){
			alert(a);
			alert(b);
			alert(c);
		}
	
	});
}

</script>
<style type="text/css">
/* table, td {
	border: 1px solid black;
}

table {
	width: 100%;
	border-collapse: collapse;
}

td {
	padding: 5px;
	text-align: center;
} */

select {
	width: 150px;
	height: 30px;
}

#container {
	width: 960px;
	margin: 0 auto;
}

#provider : hover{
	cursor:point;
	color:purple;
}

.hidden{
	display:none;
}

.bold{
	font-weight:bold;
}

div{
	text-align:center;
}
</style>
<div id="container">
	<hr>
	<hr>
	<div class="row"> 
		<div class="col-sm-12" style="font-size:30px; text-color:#01153F;">${requestScope.map.video.videoTitle}</div>
	</div>
	<br><br>
	<hr>
	<div class="row">
		<table class="table">
			<thead>
				<tr>
					<th class="text-center">게시자</th>
					<th class="text-center">등록일자</th>
					<th class="text-center">조회수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-center">${requestScope.map.video.videoUserId}</td>
					<td class="text-center"><fmt:formatDate value="${requestScope.map.video.videoRegTime}" pattern="yyyy-MM-dd"/></td>
					<td class="text-center">${requestScope.map.video.videoHits}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	
	<%-- <iframe width="800" height="750" src="${requestScope.video.videoLink }" frameborder="0" gesture="media" allow="encrypted-media" allowfullscreen></iframe> --%>
	<div class="row">
		<!-- youtube -->
		<div class="col-sm-9" style="float:left; ">
			<div id="player">
				<input type="hidden" id="link" value="${requestScope.map.video.videoLink }">
			</div> 
		</div>
	
		
	<!-- youtube iframe api -->
	<script>
      // 2. This code loads the IFrame Player API code asynchronously.
      var tag = document.createElement('script');

      tag.src = "https://www.youtube.com/iframe_api";
      var firstScriptTag = document.getElementsByTagName('script')[0];
      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

      // 3. This function creates an <iframe> (and YouTube player)
      //    after the API code downloads.
      var player;
      function onYouTubeIframeAPIReady() {
        player = new YT.Player('player', {
          height: '400',
          width: '600',
          videoId: document.getElementById("link").value,
          events: {
            'onReady': onPlayerReady,
            
          }
        });
      }

      // 4. The API will call this function when the video player is ready.
      function onPlayerReady(event) {
        event.target.playVideo();
      }

    </script>
	<!-- youtube END -->
	
	<!-- 내용 table -->
		<div class="col-sm-3">
			<h2 class="text-center">아티스트 정보</h2>
			<hr>
			<hr>
			<table class="table" id="product_tb" >
				
				<tbody>
					<!-- 아티스트 프로필을 보여주면 좋지 않을까? -->
					<tr>
						<td></td>
					</tr>
					<tr>
						<td>아티스트</td> 
						<td>${requestScope.map.video.videoArtist}</td>
					</tr>
					<c:choose>
						<c:when test="${requestScope.map.video.videoCategory=='practice' }">
							<tr>
								<td>연습장소</td> 
								<td>${requestScope.map.video.videoLocation }</td>
							</tr>
							<tr>
								<td>연습 날짜</td>
								<td><fmt:formatDate value="${requestScope.map.video.videoDate }" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>공연장소</td> 
								<td>${requestScope.map.video.videoLocation }</td>
							</tr>
							<tr>
								<td>공연 날짜</td>
								<td><fmt:formatDate value="${requestScope.map.video.videoDate }" pattern="yyyy-MM-dd"/> </td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			
			
		</div>
	</div>
</div>


<!-- 내용 -->
<div class="row">
	<div class="col-sm-12">
		<p style="border: 1px solid #e5e5e5; color:#515151; font-size: 16px; padding:20px;">
			${requestScope.map.video.videoContent}
		</p>
	</div>
</div>


<div id="like">
	<a id="likeBtn" style="cursor:pointer; font-size: 18px; margin-left: 10px; text-decoration: none;"><span class='glyphicon glyphicon-heart'></span></a>
</div>

<!-- Comment -->
<div>
	<div style="border: 1px solid #e5e5e5; height:0 auto; padding: 10px">
		<!-- **로그인 한 회원에게만 댓글 작성폼이 보이게 처리 -->

		
		<sec:authorize access="isAuthenticated()">			 
			<div class="row">
				<input class="col-sm-5 col-sm-offset-2 input-control" id="videoComment" name="videoContent" placeholder="댓글을 입력하세요." required="required">
				<div class="col-sm-1"></div>
				<button class="col-sm-2 btn btn-default" id="enterVideoCommentBtn" type="button">등록</button>
			</div>
		</sec:authorize>
		
		<!-- 댓글을 펼치고 숨기기 위한 곳. -->
		<div class="row">
			<div class="col-sm-12" id="provider">
				<button class="btn btn-default" id="providerBtn">댓글보기</button>
			</div>
			<!-- 댓글목록이 보여질 div -->
			<div class="col-sm-12" id="videoCommentList" style="border-bottom:1px solid darkgray; margin-bottom: 15px; background-color:ghostWhite;">
			
			</div>
		</div>
	</div>
</div>
<!-- Comment End-->

<!-- button -->
<div class="row">
	<!-- 게시글 수정 & 삭제 -->
	<!-- 수정 -->
	<div class="col-sm-1 col-sm-offset-8">
		<form action="${initParam.rootPath }/videoListCategory.do">
			<input type="hidden" name="category" value="${requestScope.map.video.videoCategory }">
			<button class="btn btn-default">목록으로</button>
		</form>
		<!-- <button style="padding:10px">이전 글로</button>
		<button style="padding:10px">다음 글로</button> -->
	</div>
	<c:if test="${requestScope.map.video.videoUserId eq requestScope.map.userId }">
		<div class="col-sm-1">
			<form action="${initParam.rootPath }/videoChangeInfoView.do" method="post">
				<sec:csrfInput/>
				<input type="hidden" name="videoNo" value="${requestScope.map.video.videoNo }">
				<button class="btn btn-warning">수정</button>
			</form>
		</div>
		<!-- 삭제 -->
		<div class="col-sm-1">
			<form action="${initParam.rootPath }/deleteVideo.do" method="post">
				<sec:csrfInput/>
				<input type="hidden" name="videoNo" value="${requestScope.map.video.videoNo }">
				<input type="hidden" name="category" value="${requestScope.map.video.videoCategory }">
				<button class="btn btn-danger">삭제</button>
			</form>
		</div>
	</c:if>
</div>
	


