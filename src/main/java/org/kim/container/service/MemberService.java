package org.kim.container.service;

import org.kim.container.dao.MemberDao;
import org.kim.container.domain.Member;

public class MemberService {
    private MemberDao dao;

    public MemberService(MemberDao dao) {
        this.dao = dao;
    }

    public boolean isAvailableId(String id) {
        Member member = dao.memberSelectById(id);

        return member != null;
    }

    public boolean isAvailableEmail(String email) {
        Member member = dao.memberSelectByEmail(email);

        return member != null;
    }
}

