package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.CourseDao;
import net.therap.enrollmentmanagement.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
@Component
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    public Course find(long id) {
        return courseDao.find(id);
    }

    public Course findByCourseCode(String courseCode) {
        return courseDao.findByCourseCode(courseCode);
    }

    public List<Course> findAll() {
        return courseDao.findAll();
    }

    public void save(Course course) {
        courseDao.saveOrUpdate(course);
    }

    public void update(Course course, Course updatedCourse) {
        updatedCourse.setCourseCode(course.getCourseCode());
        updatedCourse.setCourseTitle(course.getCourseTitle());
        courseDao.saveOrUpdate(updatedCourse);
    }

    public void delete(long id) {
        courseDao.delete(id);
    }
}