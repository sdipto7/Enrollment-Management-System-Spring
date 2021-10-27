package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.EnrollmentDao;
import net.therap.enrollmentmanagement.domain.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Enrollment findByUserAndCourse(long userId, long courseId) {
        return enrollmentDao.findByUserAndCourse(userId, courseId);
    }

    public List<Enrollment> findAll() {
        return enrollmentDao.findAll();
    }

    public Enrollment getOrCreateEnrollment(long enrollmentId) {
        return enrollmentId == 0 ? new Enrollment() : find(enrollmentId);
    }

    public void saveOrUpdate(Enrollment enrollment) {
        enrollmentDao.saveOrUpdate(enrollment);
    }

    public void delete(Enrollment enrollment) {
        enrollmentDao.delete(enrollment);
    }
}