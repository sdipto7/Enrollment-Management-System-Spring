package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.CourseDao;
import net.therap.enrollmentmanagement.domain.Course;

import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
public class CourseService {

    private CourseDao courseDao;

    public CourseService() {
        courseDao = new CourseDao();
    }

    public Course find(long id) {
        return courseDao.find(id);
    }

    public Course findByCourseCode(String courseCode) {
        return courseDao.findByCourseCode(courseCode);
    }

    public List<Course> findAll() {
        return courseDao.findAll();
    }

    public void saveOrUpdate(Course course) {
        courseDao.saveOrUpdate(course);
    }

    public void delete(long id) {
        courseDao.delete(id);
    }
}