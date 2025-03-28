<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.kim.container.domain.Member" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
    <script>
        function showDeleteForm() {
            const form = document.getElementById('deleteForm');
            form.style.display = form.style.display === 'none' ? 'block' : 'none';
        }

        window.onload = function() {
            <%
                String result = (String)request.getAttribute("result");
                if ("error".equals(result)) {
            %>
                alert("회원 탈퇴에 실패했습니다. 나중에 다시 시도하세요.");
            <% } else if ("deleteFail".equals(result)) { %>
                alert("비밀번호가 일치하지 않습니다.");
            <% } %>
        }
    </script>
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

<button type="button" onclick="showDeleteForm()">회원 탈퇴</button>

<form id="deleteForm" action="deleteMember" method="post" style="display:none;"
      onsubmit="return confirm('정말 회원을 탈퇴하시겠습니까?');">
    <label for="password">회원 탈퇴를 위해 비밀번호를 입력하세요:</label>
    <input type="password" id="password" name="password" required>
    <button type="submit">확인</button>
</form>

</body>
</html>
