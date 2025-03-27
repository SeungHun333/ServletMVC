package org.kim.container.util;

import org.kim.container.domain.Member;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthUtil {
    public static boolean isLoggedIn(HttpServletRequest req) throws IOException {
        Member loginMember = (Member) req.getSession().getAttribute("loginMember");
        return loginMember != null;
    }
}
