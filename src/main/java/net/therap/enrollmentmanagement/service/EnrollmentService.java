package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.EnrollmentDao;
import net.therap.enrollmentmanagement.domain.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
@Component
public class EnrollmentService {

    @Autowired
    private EnrollmentDao enrollmentDao;

    public Enrollment find(long id) {
        return enrollmentDao.find(id);
    }

    public List<Enrollment> findAll() {
        return enrollmentDao.findAll();
    }

    public void save(Enrollment enrollment) {
        enrollmentDao.saveOrUpdate(enrollment);
    }

    public void update(Enrollment enrollment, Enrollment updatedEnrollment) {
        updatedEnrollment.setUser(enrollment.getUser());
        updatedEnrollment.setCourse(enrollment.getCourse());
        enrollmentDao.saveOrUpdate(updatedEnrollment);
    }

    public void delete(long id) {
        enrollmentDao.delete(id);
    }
}