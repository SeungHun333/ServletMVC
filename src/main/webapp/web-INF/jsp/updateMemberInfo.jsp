<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.kim.container.domain.Member" %>
<%
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (loginMember == null) {
        response.sendRedirect("login");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <script>
        function validateForm() {
            const pw = document.getElementById("password").value;
            const confirmPw = document.getElementById("confirm-password").value;

            if (pw !== confirmPw) {
                alert("비밀번호가 일치하지 않습니다.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h1>회원 정보 수정</h1>

<form action="updateMemberInfo" method="post" onsubmit="return validateForm();">
    <input type="hidden" name="userNo" value="<%= loginMember.getUserNo() %>">

    아이디: <strong><%= loginMember.getId() %></strong><br><br>

    비밀번호: <input type="password" id="password" name="password" required><br><br>

    비밀번호 확인: <input type="password" id="confirm-password" required><br><br>

    이름: <input type="text" name="name" value="<%= loginMember.getName() %>" required><br><br>

    이메일: <input type="email" name="email" value="<%= loginMember.getEmail() %>"><br><br>

    전화번호: <input type="text" name="phone" value="<%= loginMember.getPhone() %>"><br><br>

    주소: <input type="text" name="address" value="<%= loginMember.getAddress() %>"><br><br>

    <input type="submit" value="수정하기">
</form>

<br>
<a href="main">메인으로</a>
</body>
</html>
