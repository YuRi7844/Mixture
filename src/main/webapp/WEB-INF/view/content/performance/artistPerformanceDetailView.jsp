<%@page import="com.buskstop.vo.Performance"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${initParam.rootPath}/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2cf9bb3da4e98eebd3e7696702b01439&libraries=services"></script>
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
				txt='<button type="button" id="followBtn" class="btn btn-default">팔로우취소</button>';
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
	
	listComment();
	$("#btnComment").on("click",function(){
        
		$.ajax({                
            "url": "${initParam.rootPath }/performanceCommentInsert.do",
            "type": "get",
            "data" : {
            	"performanceNo":"${requestScope.map.performance.performanceNo}",
            	"performanceComment":$("#performanceComment").val(),
            	'${_csrf.parameterName}':'${_csrf.token}'
            },
            "dataType":"text",
            success: function(){
                alert("댓글이 등록되었습니다.");
                listComment();
                document.getElementById("performanceComment").value="";
            },
           	"error":function(){
           		alert("오류 발생");
           		
           	}
        });
     });
	
	
	
	$(".likeBtn").on("click", function(){
		$.ajax({
			"type":"POST",
			"url":"${initParam.rootPath}/performanceLike.do",
			"data":{
				"num":"${requestScope.map.performance.performanceNo}",
				'${_csrf.parameterName}':'${_csrf.token}'
			},
			"dataType":"text",
			"success":function(count){
				$(".likeBtn").html("<span class='glyphicon glyphicon-heart'></span>"+count);
			}
		});
	});
	
	//####################################################
	// 	팔로우 정보 조회, 팔로우, 팔로우 취소
	//####################################################
	
	
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
					txt='<button id="followBtn" class="btn btn-default">팔로우취소</button>';
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
	
	$("#artistImage").on("click",function(){
		var form = document.createElement("form");
		form.setAttribute("charset","UTF-8");
		form.setAttribute("method","post");
		form.setAttribute("action","${initParam.rootPath }/artistInfo.do");
		
		var token = document.createElement("input");
		token.setAttribute("type","hidden");
		token.setAttribute("name","${_csrf.parameterName}");
		token.setAttribute("value","${_csrf.token}");
		
		var field = document.createElement("input");
		field.setAttribute("type","hidden");
		field.setAttribute("name","artistId");
		field.setAttribute("value","${requestScope.map.artist.artistId }");
		
		
		form.appendChild(field);
		form.appendChild(token);
		document.body.appendChild(form);
		
		form.submit();
	});
});	

function listComment(){
    $.ajax({
        //"dataType":"json",
        "url" : "/buskstop/performanceCommentList.do",
        "type": "get",
        "data" : {"performanceNo":"${requestScope.map.performance.performanceNo}"},
        "dataType" : "json",
        "success" : function(result){
            var output = "";
            $.each(result, function(){ 
            	output += '<div class="performanceComment" style="border-top:1px solid darkgray; margin-bottom: 15px;">';
                output += '<div class="listComment'+this.performanceCommentNo+'">'+'작성자 : '+this.performanceCommentUserId+' / 등록 일자 : '+this.performanceCommentRegTime;
                output += '<sec:authorize access="isAuthenticated()">'
                if(this.performanceCommentUserId=='${requestScope.map.userId}'){
                output += '<a onclick="updateCommentText('+this.performanceCommentNo+',\''+this.performanceComment+'\');"> 수정 </a>';
                output += '<a onclick="deleteComment('+this.performanceCommentNo+');"> 삭제 </a>';
                } output += '</sec:authorize><div class="pComment'+this.performanceCommentNo+'"> <p> 내용 : '+this.performanceComment +'</p>';
                output += '</div></div></div>';
            });
            $("#performanceCommentList").html(output);
        },
        "error":function(){
       		//alert("오류 발생");
       	}
    });
}
   
function deleteComment(performanceCommentNo){
    
	$.ajax({                
        "url": "${initParam.rootPath }/performanceCommentDelete.do",
        "type": "post",
        "data" : {
        	"performanceCommentNo":performanceCommentNo,
        	'${_csrf.parameterName}':'${_csrf.token}'
        },
        "dataType":"text",
        "success": function(){
            alert("댓글이 삭제되었습니다.");
            listComment();
        },
        "error":function(){
        	alert("에러 뜸 ㅠㅠ");
        }
    });
 }
 
 
function updateCommentText(performanceCommentNo,performanceComment){
	
    var output ="";
    	output += '<div class="input-group">';
    	output += '<input type="text" class="form-control" name="pComment'+performanceCommentNo+'" value="'+performanceComment+'"/>';
    	output += '<span class="input-group-btn">'
    	output += '<button class="btn btn-default" type="button" onclick="updateComment('+performanceCommentNo+');">수정</button>';
    	output += '<button class="btn btn-default" type="button" onclick="listComment();">수정 취소</button>';
    	output += ' </span></div>';
       
       $(".pComment"+performanceCommentNo).html(output);
}

function updateComment(performanceCommentNo){
		var UpdatePerformanceComment = $("[name=pComment"+performanceCommentNo+"]").val();
	$.ajax({
		"url": "${initParam.rootPath }/performanceCommentUpdate.do",
	    "type": "get",
	    "data" : {
	    	"performanceCommentNo":performanceCommentNo,
	    	"UpdatePerformanceComment":UpdatePerformanceComment,
	    	'${_csrf.parameterName}':'${_csrf.token}'
	    },
	    "dataType":"text",
	    "success" : function(){
	    	alert("댓글이 수정되었습니다.");
	    	listComment();
	    },
	    "error":function(){
	    	alert("댓글을 입력해주세요.");
	    }
		
	});
	
}
   
   
function updatePerformance(){	
	var output = "";
	output+=location.href='${initParam.rootPath }/performanceUpdate3.do?performanceNo=${param.performanceNo}';
	
}

function deletePerformance(performanceNo){
	
	var output = "";
	output+=location.href='${initParam.rootPath }/deletePerformance.do?performanceNo=${param.performanceNo}';
	
}


</script>

<style type="text/css">
	table, td{
		border: 1px solid black;
	}
	table{
		width:100%;
		border-collapse: collapse;
	}
	td{
		padding: 5px;
		text-align: center;
		
	}
	select{
		width:150px;height: 30px;
	}
	#container{
		width:960px;
		margin : 0 auto;
	}
	.likeBtn{
		cursor: pointer;
	}
	
	#artistImage{
		cursor : pointer;
	}
	
	textArea{
		outline: #ffe6ee solid 3px;
    	resize:none;
    	width:90%;
    	height:30px;
    	}

</style>
</head>
<body>
<div id="container" style="top-margin:10px">

<h1>DETAIL VIEW - 아티스트 공연 정보 글 읽기</h1>
<hr>

<h2>공연정보 게시판</h2>
<hr>

	<!-- 아티스트 정보 -->
	<div class="container-inline">
		<h3>아티스트 정보</h3>
		<div>
			<img style="width:200px;height:200px;" src="${initParam.rootPath }/artistImage/${requestScope.map.artist.artistImage }" id="artistImage" style="width:100px; height:100px;" onerror="this.src='/buskstop/performanceImage/no-image.png;'">
			<div class="text-center">
				<span>${requestScope.map.artist.artistName }</span><br>
				<span>${requestScope.map.artist.performance }</span><br>
				<span>${requestScope.map.artist.artistSns }</span><br>
			</div>
			<c:if test="${requestScope.map.artist.artistId ne requestScope.map.userId }">
				<sec:authorize access="isAuthenticated()">	
					<div class="text-right" id="follow" style="overflow:hidden">
						<button type="button" id="followBtn"></button>
					</div>
				</sec:authorize>
			</c:if>
		</div>
	</div>

	<!-- 아티스트 지도 -->
	<div style="border-top: 1px solid #e5e5e5; border-bottom: 1px solid #e5e5e5; overflow : hidden; position: relative">
		<div>
			<h3>Title : ${requestScope.map.performance.performanceTitle}</h3>
		</div>
		<div style="float:right; position: absolute; bottom: 10px; right: 0;">
			<div>
				<div style="float:right; margin-left:5px;">${requestScope.map.performance.performanceHits}</div>
				<div style="float:right; margin-left:20px;">조회
			</div>
			<div style="float:right; margin-left:20px;"></div><fmt:formatDate value="${requestScope.map.performance.performanceRegTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </div>
			<div style="float:right;">${requestScope.map.performance.performanceUserId}<strong>님</strong></div>
		</div>
	</div>
	
	<div>
		<p style="color:#515151; font-size: 16px; padding:20px;">
			<img style="width:800px;height:600px;" src="${initParam.rootPath }/performanceImage/${requestScope.map.performance.performanceImage }" onerror="this.src='${initParam.rootPath }/performanceImage/no-image.png;'">
		</p>
		<p style="color:#515151; font-size: 16px; padding:20px;">
		${requestScope.map.performance.performanceContent}
		</p>
	</div>

	<div style="border-bottom: 1px solid #e5e5e5; overflow : hidden; padding : 5px; background: #f9f9f9; ">
		<div style="position:static; float:left;">
			<div style="float:left; margin-right:5px; width:100%;">공연장소</div> 
			<%-- <div style="float:left; margin-right:20px;">${requestScope.performance.performanceLocation }</div> --%>
			<div id="map" style="position:static; width:900px; height:400px"></div>
			<script type="text/javascript" src="${initParam.rootPath}/resource/jquery/jquery-3.2.1.min.js"></script>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2cf9bb3da4e98eebd3e7696702b01439&libraries=services"></script>
			<script type="text/javascript">
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = {
			        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			        level: 3, // 지도의 확대 레벨
			        scrollwheel : false
			    };  
	
				// 지도를 생성합니다    
				var map = new daum.maps.Map(mapContainer, mapOption); 
		
				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new daum.maps.services.Geocoder();
				// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
				var mapTypeControl = new daum.maps.MapTypeControl();
				map.addControl(mapTypeControl, daum.maps.ControlPosition.RIGHT);
				// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
				var zoomControl = new daum.maps.ZoomControl();
				map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
				var currentTypeId;
				var resulty;
				var resultx;
		
				// 주소로 좌표를 검색합니다
				geocoder.addressSearch('${requestScope.map.performance.performanceLocation}', function(result, status) {
		
				    // 정상적으로 검색이 완료됐으면 
				     if (status === daum.maps.services.Status.OK) {
		
				        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
				        resulty = result[0].y;
						resultx = result[0].x;
				    	
				        // 결과값으로 받은 위치를 마커로 표시합니다
				        var marker = new daum.maps.Marker({
				            map: map,
				            position: coords
				        });
		
				        // 인포윈도우로 장소에 대한 설명을 표시합니다
				        var infowindow = new daum.maps.InfoWindow({
				            content: '<div style="width:150px;text-align:center;padding:6px 0;">${requestScope.map.performance.performanceName}</div>'
				        });
				        infowindow.open(map, marker);
		
				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map.setCenter(coords);
				    } 
				});    
				//지도 이동 이벤트 핸들러
				function moveDaumMap(self){
				    
				    var center = map.getCenter(), 
				        lat = center.getLat(),
				        lng = center.getLng();

				    self.href = 'http://map.daum.net/link/map/' + encodeURIComponent('${requestScope.map.performance.performanceName}') + ',' + lat + ',' + lng; //Daum 지도로 보내는 링크
				}

				//지도 초기화 이벤트 핸들러
				function resetDaumMap(){
					map.setCenter(new daum.maps.LatLng(resulty, resultx));; //지도를 초기화 했던 값으로 다시 셋팅합니다.
				    map.setLevel(mapOption.level);
				}

			</script>
		</div>
		<hr>
	</div>
		<div>
			<div style="float:left; margin-right:5px">공연 시간</div>
			<div style="float:left;"><fmt:formatDate value="${requestScope.map.performance.performanceDate }" pattern="yyyy-MM-dd HH시mm분"/></div>
		</div>
<hr>
	
	<div class="button_box" style="width: 100%;">
		<div style="float: left;">
			좋아요<a class="likeBtn" style="font-size: 18px; margin-left: 10px; text-decoration: none"><span class='glyphicon glyphicon-heart'></span>${requestScope.map.performance.likeCount }</a>
		</div>
	<!-- Board Content End-->
	<div>
	<sec:authorize access="isAuthenticated()">
		<c:if test="${requestScope.map.userId eq requestScope.map.performance.performanceUserId }">
			<input type="submit" style="float:right;" value="수정" onclick="updatePerformance();">
			<input type="submit" style="float:right;" value="삭제" onclick="deletePerformance();">
		</c:if>
		<button type="button" style="float:right;" onclick="location.href='${initParam.rootPath }/selectArtistPerformance.do'">목록</button>

	<div style="float: left; width: 100%;">
		<textarea name="content" id="performanceComment" 
		cols="20" rows="5" placeholder="댓글을 쓰세요" style="float: left;"></textarea>
		<button type="button" id="btnComment">댓글 등록</button>
	</div>
	</sec:authorize>
	</div>
	<div id="performanceCommentList" style="float: left; width: 100%;"></div>
	
	</div>
</div>
</body>
