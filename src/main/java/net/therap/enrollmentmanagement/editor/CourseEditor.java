package net.therap.enrollmentmanagement.editor;

import net.therap.enrollmentmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/28/21
 */
@Component
public class CourseEditor extends PropertyEditorSupport {

    @Autowired
    CourseService courseService;

    @Override
    public void setAsText(String courseCode) {
        if (Objects.nonNull(courseCode)) {
            setValue(courseService.findByCourseCode(courseCode));
        }
    }
}
