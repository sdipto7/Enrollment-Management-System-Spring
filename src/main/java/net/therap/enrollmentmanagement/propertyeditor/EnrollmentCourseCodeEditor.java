package net.therap.enrollmentmanagement.propertyeditor;

import net.therap.enrollmentmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

/**
 * @author rumi.dipto
 * @since 9/28/21
 */
public class EnrollmentCourseCodeEditor extends PropertyEditorSupport {

    @Autowired
    CourseService courseService;

    @Override
    public void setAsText(String courseCode) throws IllegalArgumentException {
        setValue(courseService.findByCourseCode(courseCode));
    }
}
