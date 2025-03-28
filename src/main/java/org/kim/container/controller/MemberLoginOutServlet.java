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

        try {
            Member member = dao.memberSelectForLogin(id, password);

            resp.setContentType("text/plain;charset=UTF-8");
            if (member != null) {
                System.out.println("로그인 성공!!");
                req.getSession().setAttribute("loginMember", member);
                req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
            } else {
                System.out.println("로그인 실패!!");
                resp.sendRedirect("login?result=error");
            }
        } catch (Exception e) {
            e.printStackTrace(); // 콘솔에 예외 로그 출력!
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
