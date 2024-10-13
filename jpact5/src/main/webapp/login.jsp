<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="login" method="post">
		<input type="text" name="username" id="username"placeholder="Nhap username" required> 
		<input type="text" name="password" id="password" placeholder="Nhap mat khau" required>
		<input type="submit" value="Dang Nhap">
        <input type="checkbox" id="remember" name="remember" autocomplete="off">
	</form>
</body>
</html>