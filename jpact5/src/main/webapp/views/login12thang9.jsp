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
	<c:if test="${alert !=null}">
		<h3 class="alert alertdanger">${alert}</h3>
	</c:if>


	<div class="imgcontainer">
		<img src="img_avatar2.png" alt="Avatar" class="avatar">
	</div>

	<!-- BEGIN CONTENT -->
	<div class="col-md-9 col-sm-9">
		<h1>Login</h1>
		<div class="content-form-page">
			<div class="row">
				<div class="col-md-7 col-sm-7">
					<form class="form-horizontal form-without-legend" action="login"
						method="post" role="form">
						<div class="form-group">
							<label for="username" class="col-lg-4 control-label">Username
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="username"
									name="username">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-lg-4 control-label">Password
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="password"
									name="password">
							</div>
						</div>
						<div class="row">
							<div class="col-lg-8 col-md-offset-4 padding-left-0">
								<label> <input type="checkbox" checked="checked"
									name="remember"> Remember me
								</label> <a href="forgotPassword" style="margin-left: 20px;">Forget
									Password?</a>
							</div>
						</div>
						<div class="row">
							<div
								class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
								<button type="submit" class="btn btn-primary">Login</button>
							</div>
						</div>
						<!-- Thêm thẻ a cho liên kết "Đăng ký ngay" -->
						<div class="row">
							<div
								class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-10">
								<a href="register" class="btn btn-link">Đăng ký ngay</a>
							</div>
						</div>
						<div class="row">
							<div
								class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-10 padding-right-30">
								<hr>
								<div class="login-socio">
									<p class="text-muted">or login using:</p>
									<ul class="social-icons">
										<li><a href="#" data-original-title="facebook"
											class="facebook" title="facebook"></a></li>
										<li><a href="#" data-original-title="Twitter"
											class="twitter" title="Twitter"></a></li>
										<li><a href="#" data-original-title="Google Plus"
											class="googleplus" title="Google Plus"></a></li>
										<li><a href="#" data-original-title="Linkedin"
											class="linkedin" title="LinkedIn"></a></li>
									</ul>
								</div>
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
	<!-- END CONTENT -->

</body>
</html>