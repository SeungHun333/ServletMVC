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
import java.util.stream.StreamSupport;

@WebServlet("/updateMemberInfo")
public class MemberUpdateInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member loginMember = (Member) req.getSession().getAttribute("loginMember");
        if (loginMember == null) {
            resp.sendRedirect("login");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/jsp/updateMemberInfo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        Member loginMember = (Member) req.getSession().getAttribute("loginMember");
        if (loginMember == null) {
            resp.sendRedirect("login");
            return;
        }

        loginMember.setPassword(password);
        loginMember.setName(name);
        loginMember.setEmail(email);
        loginMember.setPhone(phone);
        loginMember.setAddress(address);

        MemberDao dao = new DaoFactory().memberDao();
        boolean result = dao.memberUpdate(loginMember);

        if(result) {
            req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
        } else {
            System.out.println("정보 업데이트에 실패했습니다.");
            req.getRequestDispatcher("/WEB-INF/jsp/updateMemberInfo.jsp").forward(req, resp);
        }
    }
}
