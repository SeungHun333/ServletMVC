<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate(); // 세션 무효화 (로그아웃 처리)
    response.sendRedirect("/mywebapp/index.html"); // 홈으로 리다이렉트
%>
