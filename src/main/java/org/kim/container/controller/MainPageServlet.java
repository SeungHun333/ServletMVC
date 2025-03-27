package org.kim.container.controller;

import org.kim.container.domain.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member loginMember = (Member) req.getSession().getAttribute("loginMember");

        if (loginMember == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
    }
}