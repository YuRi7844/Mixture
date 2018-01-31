	<%@page import="com.buskstop.vo.Performance"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="${initParam.rootPath}/resource/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2cf9bb3da4e98eebd3e7696702b01439&libraries=services"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	listComment();
	$("#btnComment").on("click",function(){
        
		$.ajax({                
            "url": "${initParam.rootPath }/performanceCommentInsert.do",
            "type": "post",
            "data" : {
            	"performanceNo":"${requestScope.map.performance.performanceNo}",
            	"performanceComment":$("#performanceComment").val(),
            	'${_csrf.parameterName}':'${_csrf.token}'
            },
            "dataType":"text",
            success: function(message){
                //alert("댓글이 등록되었습니다.");
                if(message=="empty"){
                	alert("댓글을 입력해주세요")
                }
                listComment();
                document.getElementById("performanceComment").value="";
            },
           	"error":function(a,b,c){
           		alert(a);
            	alert(b);
            	alert(c);
           		
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
			},
			"error":function(a,b,c){
				alert(a);
	        	alert(b);
	        	alert(c);
			}
		});
	});
	
});	

function listComment(){
    $.ajax({
        //"dataType":"json",
        "url" : "/buskstop/performanceCommentList.do",
        "type": "post",
        "data" : {"performanceNo":"${requestScope.map.performance.performanceNo}",
			      	'${_csrf.parameterName}':'${_csrf.token}'},
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
                }output += '</sec:authorize><div class="pComment'+this.performanceCommentNo+'"> <p> 내용 : '+this.performanceComment +'</p>';
                output += '</div></div></div>';
            });
            $("#performanceCommentList").html(output);
        },
        "error":function(a,b,c){
        	alert(a);
        	alert(b);
        	alert(c);
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
            //alert("댓글이 삭제되었습니다.");
            listComment();
        },
        "error":function(a,b,c){
        	alert(a);
        	alert(b);
        	alert(c);
        }
    });
 }
 
 
function updateCommentText(performanceCommentNo,performanceComment){
	
    var output ="";
    	output += '<div class="input-group">';
    	output += '<input type="text" class="form-control" name="pComment'+performanceCommentNo+'" value="'+performanceComment+'" required="required">';
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
	    "type": "post",
	    "data" : {
	    	"performanceCommentNo":performanceCommentNo,
	    	"UpdatePerformanceComment":UpdatePerformanceComment,
	    	'${_csrf.parameterName}':'${_csrf.token}'
	    },
	    "dataType":"text",
	    "success" : function(message){
	    	//alert("댓글이 수정되었습니다.");
	    	if(message=="success"){
	    		listComment();
	    	}else{
	    		alert("댓글을 입력해주세요");
	    	}
	    	
	    },
	    "error":function(a,b,c){
	    	alert(a);
        	alert(b);
        	alert(c);
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
	 .btn_comm {
	 	float:left;display:block;width:70px;height:27px;background:url(http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/sample_button_control.png) no-repeat
	}
    
	.btn_linkMap {
		background-position:0 0;
	}
    .btn_resetMap {
    	background-position:-69px 0;
    }
    .btn_linkRoadview {
    	background-position:0 0;
    }
    .btn_resetRoadview {
   		background-position:-69px 0;
    }
    
    textarea{
    	outline: #ffe6ee solid 3px;
    	resize:none;
    	width:90%;
    	height:30px;
    	 border-color:white;
    }
</style>
</head>
<body>
<div id="container">

<h1>DETAIL VIEW - 공연 정보 글 읽기</h1>
<hr>

<h2>공연정보 게시판</h2>
<hr>

	<!-- Board Content -->
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

	<div><hr>
			<div style="float:left; margin-right:5px;">공연 시간</div>
			<div style="float:left;"><fmt:formatDate value="${requestScope.map.performance.performanceDate }" pattern="yyyy-MM-dd HH시mm분"/></div>
		</div>
	<div>
		<p style="color:#515151; font-size: 16px; padding:20px;">
			<img style="width:800px;height:600px;" src="${initParam.rootPath }/performanceImage/${requestScope.map.performance.performanceImage }" onerror="this.src='${initParam.rootPath }/performanceImage/no-image.png;'">
		</p>
	</div>
	<div>
		<hr style="float:bottom">
		<p style="color:#515151; font-size: 16px; padding:20px;">
			공연 내용 : ${requestScope.map.performance.performanceContent}
		</p>
		<hr>
	</div>
		<hr>
	<div style="border-bottom: 1px solid #e5e5e5; overflow : hidden; padding : 5px; background: #f9f9f9; ">
		<div style="position:static; float:left;">
			<div style="float:left; margin-right:5px; width:100%;">공연장소</div> 
			<%-- <div style="float:left; margin-right:20px;">${requestScope.performance.performanceLocation }</div> --%>
			<div id="map" style="position:static; width:900px; height:400px"></div>
			<div class="wrap_button">
            <a class="btn_comm btn_linkMap" target="_blank" onclick="moveDaumMap(this)"></a> <!-- 지도 크게보기 버튼입니다 -->
            <a class="btn_comm btn_resetMap" target="_blank" onclick="resetDaumMap()"></a> <!-- 지도 크게보기 버튼입니다 -->
        	</div>
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
				    	// 지도의 현재 레벨을 얻어옵니다
						var level = map.getLevel();
						var resultDiv = document.getElementById('result');  
						
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
	</div>
	<hr>
	<div class="button_box" style="width: 100%;">
		<div style="float: left;">
			좋아요<a class="likeBtn" style="font-size: 18px; margin-left: 10px; text-decoration: none; color: red;"><span class='glyphicon glyphicon-heart'></span>${requestScope.map.performance.likeCount }</a>
		</div>
		<!-- Board Content End-->
		<br><br><div>
			<button type="button" style="float:right;" onclick="location.href='${initParam.rootPath }/selectPerformance.do'">목록</button>
			<sec:authorize access="isAuthenticated()">
				<c:if test="${requestScope.map.performance.performanceUserId eq requestScope.map.userId}">
					<input type="submit" style="float:right;" value="수정" onclick="updatePerformance();">
					<input type="submit" style="float:right;" value="삭제" onclick="deletePerformance();">
				</c:if>
		<br><br>
		<div style="float: left; width: 100%;">
			<textarea name="content" id="performanceComment" 
			cols="20" rows="5" placeholder="댓글을 쓰세요" style="float: left;" required="required"></textarea>
			<button type="button" id="btnComment" style="float:right;
    	background-color: #ffe6ee;
    	border: none;
    	color: white;
    	padding:5px;
    	text-align: center;
    	color: #e085c2;
    	font-size : 16px;">댓글 등록</button>
		<br><br>
		</div>
			</sec:authorize>
		</div>
		<div id="performanceCommentList" style="float: left; width: 100%;"></div>
	</div>
</div>
