package com.zx.card.utils;

import com.zx.card.system.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

public class ShiroUtils {

    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static User getUser() {
        Object object = getSubjct().getPrincipal();
        return (User)object;
    }
    public static Integer getUserId() {
        return getUser().getId();
    }

    public static String getSalt() {
        return getUser().getSalt();
    }

    public static void logout() {
        getSubjct().logout();
    }

    public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }
}
