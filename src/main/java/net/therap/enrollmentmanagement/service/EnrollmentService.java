package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.EnrollmentDao;
import net.therap.enrollmentmanagement.domain.Enrollment;

import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
public class EnrollmentService {

    private EnrollmentDao enrollmentDao;

    public EnrollmentService() {
        enrollmentDao = new EnrollmentDao();
    }

    public Enrollment find(long id) {
        return enrollmentDao.find(id);
    }

    public List<Enrollment> findAll() {
        return enrollmentDao.findAll();
    }

    public void saveOrUpdate(Enrollment enrollment) {
        enrollmentDao.saveOrUpdate(enrollment);
    }

    public void delete(long id) {
        enrollmentDao.delete(id);
    }
}