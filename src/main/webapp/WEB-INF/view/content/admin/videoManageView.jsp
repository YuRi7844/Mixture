<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
	
	<div class="col-sm-6">
		<h2>공연영상정보 관리</h2>
	</div>
	<!-- 검색 div -->
	<div class="col-sm-6">
		<form action="${initParam.rootPath }/admin/searchPerformance.do" method="post">
			<sec:csrfInput />
			<select id="searchCategory" name="category">
				<option value="">-----</option>
				<option value="name">공연이름</option>
				<option value="location">장소</option>
				<option value="date">날짜</option>
				<option value="userId">ID</option>
			</select> 
			
			<input id="search" type="text" name="search"> 
			<input style="display:none;" type="date" name="sDate" id="sDate"> 
			<input style="display:none;" type="date" name="eDate" id="eDate">
			
			<button type="button" id="searchBtn" class="btn btn-default">검색</button>
		</form>
	</div>
	
	<div>
		<table class="table text-center">
			<thead>
				<tr class="text-center">
					<th>이미지</th>
					<th>영상제목</th>
					<th>공연날짜</th>
					<th>장소</th>
					<th>조회수</th>
					<th>ID</th>
					<th>상세보기</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody class="text-center">
				<c:forEach items="${requestScope.list }" var="video">
					<tr>
						<td>
							<img style="width:50px; height:50px;" src="https://img.youtube.com/vi/${video.videoLink }/hqdefault.jpg">
						</td>
						<td>${video.videoTitle }</td>
						<td><fmt:formatDate value="${video.videoDate }" pattern="yyyy-MM-dd"/> </td>
						<td>${video.videoLocation }</td>
						<td>${video.videoHits }</td>
						<td>${video.videoUserId }</td>
						<td>
							<form action="${initParam.rootPath }/readVideoByVideoNo.do" method="post">
								<sec:csrfInput/>
								<input type="hidden" name="videoNo" value="${video.videoNo }">
								<button class="btn btn-default">상세보기</button>
							</form>
						</td>
						<td>
							<form action="${initParam.rootPath }/videoChangeInfoView.do" method="post">
								<sec:csrfInput/>
								<input type="hidden" name="videoNo" value="${video.videoNo }">
								<button class="btn btn-primary">수정</button>
							</form>
						</td>
						<td>
							<form action="${initParam.rootPath }/admin/deleteVideo.do" method="post">
								<sec:csrfInput/>
								<input type="hidden" name="videoNo" value="${video.videoNo }">
								<button class="btn btn-warning">삭제</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>