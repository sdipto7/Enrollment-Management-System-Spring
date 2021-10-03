package net.therap.enrollmentmanagement.editor;

import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/28/21
 */
public class CourseEditor extends PropertyEditorSupport {

    @Autowired
    CourseService courseService;

    @Override
    public void setAsText(String id) {
        if (Objects.nonNull(id)) {
            Course course = new Course();
            course.setId(Long.parseLong(id));
            setValue(course);
        }
    }
}
