package net.therap.enrollmentmanagement.propertyeditor;

import net.therap.enrollmentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/28/21
 */
public class EnrollmentUserNameEditor extends PropertyEditorSupport {

    @Autowired
    UserService userService;

    @Override
    public void setAsText(String userName) throws IllegalArgumentException {
        if (Objects.nonNull(userName)) {
            setValue(userService.findByName(userName));
        }
    }
}
