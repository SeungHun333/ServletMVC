package org.kim.container.controller;

import org.kim.container.dao.MemberDao;
import org.kim.container.dao.factory.DaoFactory;
import org.kim.container.domain.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteMember")
public class MemberDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = (Member)req.getSession().getAttribute("loginMember");
        if (member == null) {
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            return;
        }

        boolean isEqualsPassword = member.getPassword().equals(req.getParameter("password"));

        if (isEqualsPassword) {
            MemberDao dao = new DaoFactory().memberDao();
            boolean success = dao.memberDelete(member.getUserNo());
            if(success) {
                req.getSession().invalidate();
                resp.sendRedirect("index.html");
                return;
            } else {
                req.setAttribute("result", "error");
            }
        } else {
            req.setAttribute("result", "deleteFail");
        }

        req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
    }
}
