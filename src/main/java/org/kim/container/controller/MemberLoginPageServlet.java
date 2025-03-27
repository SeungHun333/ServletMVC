package org.kim.container.controller;

import org.kim.container.domain.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class MemberLoginPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member loginMember = (Member) req.getSession().getAttribute("loginMember");
        System.out.println("로그인 창 로드");
        if(loginMember != null) {
            System.out.println("세션 유지중 !!");
            req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
        } else {
            System.out.println("세션 만료 !!");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
