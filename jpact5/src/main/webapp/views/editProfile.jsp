<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 class="myclass">Chinh sua thong tin cua ban</h2>
	<form action="editProfile?id=${user.id}" method="post"enctype="multipart/form-data">
		<div class="form-group">
			<label>Phone</label> <input type="text" class="form-control" id="phone"
				name="phone" value="${user.phone}" placeholder="Enter phone">
		</div>
		<div class="form-group">
			<label>Full name</label> <input type="text" class="form-control" id="fullname"
				name="fullname" value="${user.fullname}" placeholder="Enter full name">
		</div>
		<div class="form-group">
			<label>Anh dai dien</label> <br />
			<c:if test="${user.images !=null}">
			<c:url value="/image?fname=${user.images}" var="imgUrl"></c:url>
				<img src="${imgUrl}" width="80"
					height="70" />
			</c:if>
			<input type="file" class="form-control" name="file"
				value="${user.images}" placeholder="Enter photo">
		</div>
		<button type="submit" class="btn btn-primary">Luu</button>
		<button type=reset class="btn btn-primary">Huy</button>
	</form>
</body>
</html>

