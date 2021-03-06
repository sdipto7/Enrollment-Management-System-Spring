package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.CourseDao;
import net.therap.enrollmentmanagement.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    public Course find(long id) {
        return courseDao.find(id);
    }

    public Course findByCourseCode(String courseCode) {
        return courseDao.findByCourseCode(courseCode);
    }

    public Course findByCourseTitle(String courseTitle) {
        return courseDao.findByCourseTitle(courseTitle);
    }

    public List<Course> findAll() {
        return courseDao.findAll();
    }

    public Course getOrCreateCourse(long courseId) {
        return courseId == 0 ? new Course() : find(courseId);
    }

    public void saveOrUpdate(Course course) {
        courseDao.saveOrUpdate(course);
    }

    public void delete(Course course) {
        courseDao.delete(course);
    }
}