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

        System.out.println("로그인 정보 입력값:");
        System.out.println("id: " + id);
        System.out.println("password: " + password);
        System.out.println("name: " + name);
        System.out.println("email: " + email);
        System.out.println("phone: " + phone);
        System.out.println("birth_date: " + date);
        System.out.println("gender: " + gender);
        System.out.println("address: " + address);
        System.out.println("joinDate: " + joinDate);

        Member member = new Member(id, password, name, email, phone, date, gender, address, joinDate, joinDate);
        MemberDao dao = new DaoFactory().memberDao();

        MemberService service = new MemberService(dao);

        if(service.isAvailableId(member.getId())) {
            return;
        }

        dao.memberInsert(member);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
