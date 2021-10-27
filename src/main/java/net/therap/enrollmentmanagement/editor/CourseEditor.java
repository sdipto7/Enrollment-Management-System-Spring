package net.therap.enrollmentmanagement.editor;

import net.therap.enrollmentmanagement.domain.Course;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/28/21
 */
public class CourseEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String id) {
        if (Objects.nonNull(id)) {
            Course course = new Course();
            course.setId(Long.parseLong(id));
            setValue(course);
        }
    }
}
