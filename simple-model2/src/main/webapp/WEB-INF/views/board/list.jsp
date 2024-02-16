<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" ></script>
<title>모델2</title>
</head>
<body>
<c:set var="menu" value="게시판" />
<%@ include file="../common/navbar.jsp" %>
<div class="container">
	<div class="row mb-3">
		<div class="col-12">
			<h1>게시글 목록</h1>
			
			<%-- home.do에서 게시판을 들어가는 경우, 현재 페이지가 표시되지 않는다.
				 EL은 값이 없으면 아무것도 표현하지 않는다. default 속성을 사용하여 기본값을 지정할 수 있다. --%>
			<p>현재 페이지 : <c:out value="${param.page }" default="1" /></p>
			<table class="table">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>조회수</th>
						<th>작성자</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody class="table-group-divider">
					<%-- 배열이나 콜렉션 안에 들어있는 수만큼 반복됨 --%>
					<%-- <c:forEach var="b" items="${boardList }">
						<tr>
							<td>${b.no }</td>
							<td>
								${b.title }
								<c:if>는 로그인 여부에 따른 버튼 표시, 로그인한 사용자가 글작성자인지 등에 사용한다.
								<c:when>과 <c:otherwise>는 반드시 <c:choose>안에 작성해야 한다.
									 그 외의 태그(p 태그 등)를 <c:choose>안에 작성할 수 없다.
									 EL 표현식 연산자 ge : 크거나 같다
								<c:choose>
									<c:when test="${b.readCount ge 50 }">
										<i class="bi bi-hand-thumbs-up-fill"></i>
									</c:when>
									<c:when test="${b.readCount ge 25 }">
										<i class="bi bi-hand-thumbs-up"></i>
									</c:when>
								</c:choose>
							</td>
							<td>${b.readCount }</td>
							<td>${b.user.name }</td>
							<td><fmt:formatDate value="${b.createdDate }" pattern="yyyy/M/d"/></td>
						</tr>
					</c:forEach> --%>
					<c:choose>
						<c:when test="${empty boardList }">
							<tr>
								<td colspan="5" class="text-center">데이터가 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<%-- items="${boardList } " 
								 공백이 있으면 EL 표현식으로 생각하지 않고 문자열로 생각한다.--%>
							<c:forEach var="board" items="${boardList }">
								<tr>
									<td>${board.no }</td>
									<td>${board.title }</td>
									<td>${board.readCount }</td>
									<td>${board.user.name }</td>
									<td><fmt:formatDate value="${board.createdDate }" pattern="yyyy-M-d"/></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="row mb-3">
		<div class="col-12">
			<nav>
				<ul class="pagination justify-content-center">
					<c:forEach var="num" begin="1" end="5">
						<li class="page-item ${param.page eq num ? 'active' : '' }">
							<a href="list.do?page=${num }" class="page-link">${num }</a>
						</li>
					</c:forEach>
				</ul>
			</nav>
		</div>
	</div>
</div>
</body>
</html>