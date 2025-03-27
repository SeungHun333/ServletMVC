package org.kim.container.service;

import org.junit.jupiter.api.Test;
import org.kim.container.dao.MemberDao;
import org.kim.container.dao.factory.DaoFactory;
import org.kim.container.domain.Member;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    @Test
    void isAvailableId() {
    }

    @Test
    void isAvailableEmail() {
        MemberDao dao = new DaoFactory().memberDao();

        Member member = dao.memberSelectByEmail("noExisting@email.com");

        // 이메일이 없다면 사용 가능하므로 null이어야 함
        assertNull(member, "해당 이메일은 이미 존재합니다.");
    }

    @Test
    void isNotAvailableEmail() {
        MemberDao dao = new DaoFactory().memberDao();

        Member member = dao.memberSelectByEmail("existing@email.com");

        assertNotNull(member, "이메일이 없다고 나왔지만 실제로는 있어야 합니다.");
    }
}