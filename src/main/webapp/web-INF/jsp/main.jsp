<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.kim.container.domain.Member" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
</head>
<body>

<%
    Member loginMember = (Member) session.getAttribute("loginMember");

    if (loginMember == null) {
        response.sendRedirect("/mywebapp/login");
        return;
    }
%>

<h1><%= loginMember.getName() %>님 환영합니다! 🎉</h1>

<ul>
    <li><a href="/mywebapp/member-list">회원 목록 조회</a></li>
    <li><a href="/mywebapp/logProcess">로그아웃</a></li>
    <li><a href="/mywebapp/updateMemberInfo">회원정보변경</a></li>
</ul>

</body>
</html>
