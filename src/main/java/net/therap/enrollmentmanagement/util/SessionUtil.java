package net.therap.enrollmentmanagement.util;

import net.therap.enrollmentmanagement.domain.User;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/16/21
 */
public class SessionUtil {

    public static User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("currentUser");
    }

    public static boolean checkInvalidLogin(HttpSession session) {
        return Objects.isNull(getLoggedInUser(session));
    }
}
