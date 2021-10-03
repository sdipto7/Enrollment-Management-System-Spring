package net.therap.enrollmentmanagement.editor;

import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/28/21
 */
@Component
public class UserEditor extends PropertyEditorSupport {

    @Autowired
    UserService userService;

    @Override
    public void setAsText(String userName) {
        if (Objects.nonNull(userName)) {
            User user = userService.findByName(userName);
            System.out.println(user.getName());
            setValue(userService.findByName(userName));
        }
    }
}
