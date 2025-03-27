package org.kim.container.controller;

import org.kim.container.dao.MemberDao;
import org.kim.container.dao.factory.DaoFactory;
import org.kim.container.domain.Member;
import org.kim.container.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/register")
public class MemberRegisterServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Date date = Date.valueOf(req.getParameter("birth_date"));
        char gender = req.getParameter("gender").charAt(0);
        String address = req.getParameter("address");
        Date joinDate = new Date(System.currentTimeMillis());

        Member member = new Member(id, password, name, email, phone, date, gender, address, joinDate, joinDate);
        MemberDao dao = new DaoFactory().memberDao();

        MemberService service = new MemberService(dao);

        if(service.isAvailableId(member.getId())) {
            return;
        }

        boolean result = dao.memberInsert(member);

        if (result) {
            // 회원가입 성공 → 로그인 페이지로 이동
            resp.sendRedirect("login.jsp");
        } else {
            // 실패 시 에러 페이지 또는 다시 회원가입 페이지
            resp.sendRedirect("index.html");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
