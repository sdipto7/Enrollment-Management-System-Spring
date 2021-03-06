package net.therap.enrollmentmanagement.helper;

import net.therap.enrollmentmanagement.domain.Role;
import net.therap.enrollmentmanagement.domain.User;
import org.springframework.stereotype.Component;

import javax.xml.ws.WebServiceException;

/**
 * @author rumi.dipto
 * @since 10/26/21
 */
@Component
public class AccessChecker {

    public void checkAdminAccess(User user) {
        if (!Role.ADMIN.equals(user.getRole())) {
            throw new WebServiceException();
        }
    }
}
