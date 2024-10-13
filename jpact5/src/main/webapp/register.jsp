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
	<div class="col-md-9 col-sm-9">
		<h1>Register</h1>
		<div class="content-form-page">
			<div class="row">
				<div class="col-md-7 col-sm-7">
					<form class="form-horizontal form-without-legend" action="register"
						method="post" role="form">
						<!-- Phần thông báo nếu có lỗi hoặc cảnh báo -->
						<c:if test="${alert != null}">
							<h3 class="alert alert-danger">${alert}</h3>
						</c:if>

						<div class="form-group">
							<label for="username" class="col-lg-4 control-label">Username
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="username"
									name="username" placeholder="Nhập username" required>
							</div>
						</div>

						<div class="form-group">
							<label for="password" class="col-lg-4 control-label">Password
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="Nhập mật khẩu" required>
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="col-lg-4 control-label">Email <span
								class="require">*</span></label>
							<div class="col-lg-8">
								<input type="email" class="form-control" id="email" name="email"
									placeholder="Nhập email" required>
							</div>
						</div>

						<div class="form-group">
							<label for="fullname" class="col-lg-4 control-label">Full
								Name <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="fullname"
									name="fullname" placeholder="Nhập họ và tên" required>
							</div>
						</div>

						<div class="row">
							<div
								class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
								<button type="submit" class="btn btn-primary">Đăng Ký</button>
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-4 col-sm-4 pull-right">
					<div class="form-info">
						<h2>
							<em>Important</em> Information
						</h2>
						<p>Duis autem vel eum iriure at dolor vulputate velit esse vel
							molestie at dolore.</p>
						<button type="button" class="btn btn-default">More
							details</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>