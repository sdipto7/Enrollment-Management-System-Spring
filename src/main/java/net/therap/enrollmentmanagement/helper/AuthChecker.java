package net.therap.enrollmentmanagement.helper;

import net.therap.enrollmentmanagement.domain.User;
import org.springframework.stereotype.Component;

/**
 * @author rumi.dipto
 * @since 10/26/21
 */
@Component
public class AuthChecker {

    public Boolean check(User user, String password) {
        return password.equals(user.getCredential().getPassword());
    }
}
