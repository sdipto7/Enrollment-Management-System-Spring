package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
@Entity
@Table(name = "course")
public class Course extends Persistent {

    private static final long serialVersionUID = 1L;

    @Column(name = "course_code")
    @Size(min = 2, max = 7, message = "{validation.length.msg}")
    @NotNull(message = "{validation.notNull.msg}")
    private String courseCode;

    @Column(name = "course_title")
    @Size(min = 2, max = 100, message = "{validation.length.msg}")
    @NotNull(message = "{validation.notNull.msg}")
    private String courseTitle;

    @OneToMany(mappedBy = "course",
            cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollmentList;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
