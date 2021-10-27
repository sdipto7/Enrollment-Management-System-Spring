package net.therap.enrollmentmanagement.validator;

import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 10/27/21
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User duplicateUser = userService.findByUserName(user.getUserName());

        if (Objects.nonNull(duplicateUser) && !user.equals(duplicateUser)) {
            errors.rejectValue("userName", "error.msg.duplicate.userName");
        }
    }
}
