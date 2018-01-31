<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>



</script>
<div class="row text-center">
	<h2 class="text-center col-sm-12">회원 목록</h2>
</div>
<div class="row">
	<form action="${initParam.rootPath }/admin/searchMember.do" method="post" class="form-group">
		<sec:csrfInput/>
		<select name="authority">
			<option value="ROLE_MEMBER">일반회원</option>
			<option value="ROLE_ARTIST">아티스트</option>
			<option value="ROLE_PRODUCER">공급자</option>
		</select>
		<input class="input-group" type="text" name="search" id="search" placeholder="ID를 입력해주세요." style="padding:0;">
		<button class="input-group btn-default">검색</button>
	</form>
</div>

<table class="table col-sm-12" style="background-color:ghostWhite;">
	<thead>
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>주소</th>
			<th>핸드폰번호</th>
			<th>이메일</th>
			<th>회원등급</th>
			<th>탈퇴버튼</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${requestScope.list }" var="user">
		<c:if test="${user.authority!='ROLE_ADMIN' }">
			<c:if test="${user.outCheck ne 1 }">
				<tr>
					<!-- 아이디 -->
					<td>${user.userId }</td>
					<!-- 이름-->
					<td>${user.userName }</td>
					<!-- 주소-->
					<td>${user.userAddress }</td>
					<!-- 핸드폰번호 -->
					<td>${user.userPhoneNum }</td>
					<!-- 이메일 -->
					<td>${user.email }</td>
					<!-- 권한 -->
					<c:choose>
						<c:when test="${user.authority=='ROLE_MEMBER' }">
							<td>일반회원</td>
						</c:when>
						<c:when test="${user.authority=='ROLE_ARTIST' }">
							<td>아티스트</td>
						</c:when>
						<c:when test="${user.authority=='ROLE_PRODUCER' }">
							<td>공급자</td>
						</c:when>
					</c:choose>			
					<td>
						<form action="${initParam.rootPath }/admin/dropUser.do" method="post">
							<sec:csrfInput/>
							<input type="hidden" name="userId" value="${user.userId }">
							<button id="dropBtn" class="btn btn-warning">탈퇴시키기</button>
						</form>
					</td>
				<tr>
			</c:if>
		</c:if>
	</c:forEach>
	</tbody>
	
</table>