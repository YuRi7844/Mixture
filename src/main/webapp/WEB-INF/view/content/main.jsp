<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>


function goVideoDetail(root, no){
	document.location.href= root+'/readVideoByVideoNo.do?videoNo='+no;
}

</script>

<style>

#carousel-performance-generic{
	height:300px;
	width:400px;
}

#carousel-video-generic{
	height:300px;
	width:400px;
}

</style>

<div class="container">
	<div class="row">
		<div class="col-sm-5">
			
			
			<div>
				<h2 class="text-center">추천 공연정보</h2>
			</div>
			
			<!-- 공연정보 좋아요 많은 순으로 짤라서 image 보여주기. -->
			<div id="carousel-performance-generic" class="carousel slide center-block" data-ride="carousel">
			
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-performance-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-performance-generic" data-slide-to="1"></li>
					<li data-target="#carousel-performance-generic" data-slide-to="2"></li>
				</ol>
			
				<div class="carousel-inner" role="listbox">
					<c:forEach items="${requestScope.map.performance }" var="performance" varStatus="num">
						<c:choose>
							<c:when test="${num.index eq 0}">
								<!-- 일반 공연 -->
								<c:if test="${performance.performanceCode eq 0 }">
									<div class="item active">
										<a href="${initParam.rootPath }/performanceDetailView.do?performanceNo=${performance.performanceNo }">
											<img style="height:300px; width:400px;" src="${initParam.rootPath }/performanceImage/${performance.performanceImage }"
											onerror='this.src="${initParam.rootPath }/performanceImage/no-image.png"'>
										</a>
										<div class="carousel-caption">
												<label>${performance.performanceName }</label>
										</div>
									</div>
								</c:if>
								<!-- 아티스트공연 -->
								<c:if test="${performance.performanceCode eq 1 }">
									<div class="item active">
										<a href="${initParam.rootPath }/artistPerformanceDetailView.do?performanceNo=${performance.performanceNo }">
											<img style="height:300px; width:400px;" src="${initParam.rootPath }/performanceImage/${performance.performanceImage }"
											onerror='this.src="${initParam.rootPath }/performanceImage/no-image.png"'>
										</a>
										<div class="carousel-caption">
											<label>${performance.performanceName }</label>
										</div>
									</div>
								</c:if>
							</c:when>
							<c:otherwise>
								<!-- 일반 -->
								<c:if test="${performance.performanceCode eq 0 }">
									<div class="item">
										<a href="${initParam.rootPath }/performanceDetailView.do?performanceNo=${performance.performanceNo }">
											<img style="height:300px; width:400px;" src="${initParam.rootPath }/performanceImage/${performance.performanceImage }"
											onerror='this.src="${initParam.rootPath }/performanceImage/no-image.png"'>
										</a>
										<div class="carousel-caption">
											<label>${performance.performanceName }</label>
										</div>
									</div>
								</c:if>
								<!-- 아티스트 -->
								<c:if test="${performance.performanceCode eq 1 }">
									<div class="item">
										<a href="${initParam.rootPath }/artistPerformanceDetailView.do?performanceNo=${performance.performanceNo }">
											<img style="height:300px; width:400px;" src="${initParam.rootPath }/performanceImage/${performance.performanceImage }"
											onerror='this.src="${initParam.rootPath }/performanceImage/no-image.png"'>
										</a>
										<div class="carousel-caption">
											<label>${performance.performanceName }</label>
										</div>
									</div>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			
				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-performance-generic" role="button" data-slide="prev"> 
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> 
				<a class="right carousel-control" href="#carousel-performance-generic" role="button" data-slide="next"> 
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 
					<span class="sr-only">Next</span>
				</a>
			
			</div>
			<div>
				<h2 class="text-center">추천 공연영상</h2>
			</div>
			
			<!-- 공연영상 -->
			<div id="carousel-video-generic" class="carousel slide center-block" data-ride="carousel">
			
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-video-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-video-generic" data-slide-to="1"></li>
					<li data-target="#carousel-video-generic" data-slide-to="2"></li>
				</ol>
			
				<div class="carousel-inner" role="listbox">
					<c:forEach items="${requestScope.map.video }" var="video" varStatus="num">
						<c:choose>
							<c:when test="${num.index eq 0}">
								<div class="item active">
									<a href="${initParam.rootPath }/readVideoByVideoNo.do?videoNo=${video.videoNo }">
										<img style="height:300px; width:400px;" src="https://img.youtube.com/vi/${video.videoLink }/hqdefault.jpg">
									</a>
									<div class="carousel-caption">
										<label>${video.videoTitle }</label>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="item">
									<a href="${initParam.rootPath }/readVideoByVideoNo.do?videoNo=${video.videoNo }">
										<img style="height:300px; width:400px;" src="https://img.youtube.com/vi/${video.videoLink }/hqdefault.jpg">
									</a>
									<div class="carousel-caption">
										<label>${video.videoTitle }</label>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			
				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-video-generic" role="button" data-slide="prev"> 
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> 
				<a class="right carousel-control" href="#carousel-video-generic" role="button" data-slide="next"> 
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 
					<span class="sr-only">Next</span>
				</a>
			
			</div>
		</div>
		
		<div class="col-sm-7">
			<br><br>
			
			<!-- 아티스트 추천 -->
			<h2 class="text-center">추천 아티스트</h2>
			
			<!-- 추천아티스트 프로필 -->
			
			<div class="row bg-warning">
				<div class="col-sm-3">
					<img style="width:100px; height:100px;" 
					class="img-circle" src="${initParam.rootPath }/artistImage/${requestScope.map.artist.artistImage }"
					onerror='this.src="${initParam.rootPath }/artistImage/no-image.png"'>
				</div>
				
				<div class="col-sm-9 center-block">
					<div class="col-sm-3">
						<mark class="text-center">${requestScope.map.artist.artistName }</mark><br><br>
						<span class="text-center">${requestScope.map.artist.performance }</span><br><br>
						<span class="text-center"><a href="${requestScope.map.artist.artistSns }">SNS</a></span>
					</div>
					<div class="col-sm-3">
						<label class="text-center">${requestScope.map.registCount }</label>
						<br>
						<span class="text-center">게시물</span>
					</div>
					<div class="bg-warning col-sm-2">
						<label class="text-center">${requestScope.map.artist.followCount }</label>
						<br>
						<span class="text-center">팔로우</span>
					</div>
					<div class="bg-warning col-sm-4">
						<form class="form-group" action="${initParam.rootPath }/artistInfo.do" method="post">
							<sec:csrfInput/>
							<input class="form-control" type="hidden" name="artistId" value="${requestScope.map.artist.artistId }">
							<c:if test="${requestScope.map.artist }==''">
								<button class="form-control btn-primary">상세정보</button>
							</c:if>
						</form>
					</div>
				</div>
			</div>
			<br>
			<br>
			
			<!-- 프리미엄 공연장. -->
			<h2 class="text-center">이런 공연장 어떠세요?</h2>
			<c:forEach items="${requestScope.map.premium }" var="PremiumStage">
				<div class="thumbnail col-sm-6">
					<a href="${initParam.rootPath }/goPremiumStageDetailView.do?establishNo=${PremiumStage.establishNum }">
						<img style="width:230px; height:230px" src="${initParam.rootPath }/stageImage/${PremiumStage.stageImage }">
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
