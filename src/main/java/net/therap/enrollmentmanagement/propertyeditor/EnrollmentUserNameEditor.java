package net.therap.enrollmentmanagement.propertyeditor;

import net.therap.enrollmentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

/**
 * @author rumi.dipto
 * @since 9/28/21
 */
public class EnrollmentUserNameEditor extends PropertyEditorSupport {

    @Autowired
    UserService userService;

    @Override
    public void setAsText(String userName) throws IllegalArgumentException {
        setValue(userService.findByName(userName));
    }
}
