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
	<h1>Upload Status</h1>
	<p>${message}</p>
	<!-- This will display the success message -->
	<div class="container">
		<h2>Danh sach nhan vien</h2>
		<p>
			<a class="btn btn-success" href="User?action=AddOrEdit">Them nhan
				vien moi</a>
		</p>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Ảnh</th>
					<th>Mã nhân viên</th>
					<th>Tên nhân viên</th>
					<th>Action</th>
					<!-- Closed the <th> tag properly -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listuser}" var="p">
					<tr>
						<td width="100"><img
							src="${pageContext.request.contextPath}/uploads/${p.images}"
							width="80" height="70" /></td>
						<td>${p.id}</td>
						<td>${p.username}</td>
						<td><a class="btn btn-primary btn-sm"
							href="User?action=AddOrEdit&id=${p.id}">Edit</a> 
							<a
							class="btn btn-danger btn-sm"
							href="User?action=Delete&id=${p.id}">Del</a> <!-- Changed action for Delete -->
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>