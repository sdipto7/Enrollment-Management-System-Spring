package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.CourseDao;
import net.therap.enrollmentmanagement.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

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

    public List<Course> findAll() {
        return courseDao.findAll();
    }

    public void getOrCreateCourse(long courseId, ModelMap model) {
        if (courseId == 0) {
            model.addAttribute("action", "save");
            model.addAttribute("course", new Course());
        } else {
            model.addAttribute("action", "update");
            model.addAttribute("course", find(courseId));
        }
    }

    public void saveOrUpdate(Course course) {
        courseDao.saveOrUpdate(course);
    }

    public void delete(long id) {
        courseDao.delete(id);
    }
}