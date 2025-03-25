<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.kim.container.domain.Member" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>회원 목록</title>
</head>
<body>
<h1>회원 목록</h1>
<table border="1">
    <tr>
        <th>회원번호</th>
        <th>아이디</th>
        <th>이름</th>
        <th>이메일</th>
        <th>전화번호</th>
        <th>가입일</th>
    </tr>
    <%
        List<Member> memberList = (List<Member>) request.getAttribute("memberList");
        if (memberList != null) {
            for (Member member : memberList) {
    %>
    <tr>
        <td><%= member.getUserNo() %></td>
        <td><%= member.getId() %></td>
        <td><%= member.getName() %></td>
        <td><%= member.getEmail() %></td>
        <td><%= member.getPhone() %></td>
        <td><%= member.getJoinDate() %></td>
    </tr>
    <%
            }
        } else {
    %>
    <tr><td colspan="6">조회할 회원이 없습니다.</td></tr>
    <%
        }
    %>
</table>
<a href="/mywebapp/index.html">홈으로 돌아가기</a>
</body>
</html>
