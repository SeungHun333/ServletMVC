package org.kim.container.servlet;

import org.kim.container.dao.MemberDao;
import org.kim.container.dao.factory.DaoFactory;
import org.kim.container.domain.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logProcess")
public class MemberLoginOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate(); // 세션 무효화
        resp.sendRedirect("index.html"); // 메인으로 이동
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDao dao = new DaoFactory().memberDao();
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        Member member = dao.memberSelectForLogin(id, password);

        resp.setContentType("text/plain;charset=UTF-8");

        if (member != null) {
            req.getSession().setAttribute("loginMember", member);
            req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login?result=error");
        }
    }
}
