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
	<h2 class="myclass">Them nhan vien moi</h2>
	<form action="User?action=${ACTION}" method="post"
		enctype="multipart/form-data">
		<div class="form-group">
			<label>Ma nhan vien</label> <input type="text" class="form-control"
				name="id" value="${USER.id}" placeholder="Enter id">
		</div>
		<div class="form-group">
			<label>Ho ten</label> <input type="text" class="form-control"
				name="username" value="${USER.username}" placeholder="Enter name">
		</div>
		<div class="form-group">
			<label>Anh nhan vien</label> <br />
			<c:if test="${USER.images !=null}">
				<img src="<c:url value="/uploads/${USER.images}"/>" width="80"
					height="70" />
			</c:if>
			<input type="file" class="form-control" name="file"
				value="${USER.images}" placeholder="Enter photo">
		</div>
		<button type="submit" class="btn btn-primary">Luu</button>
		<button type=reset class="btn btn-primary">Huy</button>
	</form>
</body>
</html>