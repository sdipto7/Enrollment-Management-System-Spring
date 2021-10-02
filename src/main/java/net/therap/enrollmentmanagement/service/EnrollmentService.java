package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.EnrollmentDao;
import net.therap.enrollmentmanagement.domain.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentDao enrollmentDao;

    public Enrollment find(long id) {
        return enrollmentDao.find(id);
    }

    public List<Enrollment> findAll() {
        return enrollmentDao.findAll();
    }

    public void getOrCreateCourse(long enrollmentId, ModelMap model) {
        if (enrollmentId == 0) {
            model.addAttribute("action", "save");
            model.addAttribute("course", new Enrollment());
        } else {
            model.addAttribute("action", "update");
            model.addAttribute("enrollment", find(enrollmentId));
        }
    }

    public void saveOrUpdate(Enrollment enrollment) {
        enrollmentDao.saveOrUpdate(enrollment);
    }

    public void delete(long id) {
        enrollmentDao.delete(id);
    }
}