package net.therap.enrollmentmanagement.validator;

import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.service.EnrollmentService;
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
public class EnrollmentValidator implements Validator {

    @Autowired
    private EnrollmentService enrollmentService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Enrollment.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Enrollment enrollment = (Enrollment) target;

        long userId = enrollment.getUser().getId();
        long courseId = enrollment.getCourse().getId();

        Enrollment duplicateEnrollment = enrollmentService.findByUserAndCourse(userId, courseId);

        if (Objects.nonNull(duplicateEnrollment) && !enrollment.equals(duplicateEnrollment)) {
            errors.rejectValue("user", "error.msg.duplicate.enrollment");
        }
    }
}
