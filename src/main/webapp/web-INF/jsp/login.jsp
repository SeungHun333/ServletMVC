<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
<h1>로그인</h1>

<% String error = request.getParameter("result"); %>
<% if ("error".equals(error)) { %>
<p style="color:red;">❌ 아이디 또는 비밀번호가 틀렸습니다.</p>
<% } %>

<form action="logProcess" method="post">
    아이디: <input type="text" name="id" required><br><br>
    비밀번호: <input type="password" name="password" required><br><br>
    <input type="submit" value="로그인">
</form>

<br>
<a href="index.html">메인으로</a>
</body>
</html>
