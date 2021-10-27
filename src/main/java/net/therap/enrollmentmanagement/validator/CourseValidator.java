package net.therap.enrollmentmanagement.validator;

import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.service.CourseService;
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
public class CourseValidator implements Validator {

    @Autowired
    private CourseService courseService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Course.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Course course = (Course) target;
        Course duplicateByCourseCode = courseService.findByCourseCode(course.getCourseCode());
        Course duplicateByCourseTitle = courseService.findByCourseTitle(course.getCourseTitle());

        if (Objects.nonNull(duplicateByCourseCode) && !course.equals(duplicateByCourseCode)) {
            errors.rejectValue("courseCode", "error.msg.duplicate.courseCode");
        } else if (Objects.nonNull(duplicateByCourseTitle) && !course.equals(duplicateByCourseTitle)) {
            errors.rejectValue("courseTitle", "error.msg.duplicate.courseTitle");
        }
    }
}
