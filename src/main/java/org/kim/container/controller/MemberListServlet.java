package org.kim.container.controller;

import org.kim.container.dao.factory.DaoFactory;
import org.kim.container.dao.MemberDao;
import org.kim.container.domain.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/member-list")
public class MemberListServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("[MemberListServlet] init() 호출됨 — 서블릿 초기화 완료");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("회원 정보 불러오기");

        MemberDao dao = new DaoFactory().memberDao();
        List<Member> memberList = dao.memberSelectAll();

        req.setAttribute("memberList", memberList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/member-list.jsp");
        dispatcher.forward(req, resp);
    }
}
