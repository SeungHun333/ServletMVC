package org.kim.container.dao.factory;

import org.kim.container.dao.MemberDao;
import org.kim.container.dao.connection.MemberConnectionProtocol;

public class DaoFactory {
    public MemberDao memberDao() {
        return new MemberDao(new MemberConnectionProtocol());
    }
}
