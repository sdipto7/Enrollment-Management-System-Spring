package net.therap.enrollmentmanagement.dao;

import net.therap.enrollmentmanagement.domain.Enrollment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
@Repository
public class EnrollmentDao {

    @PersistenceContext(unitName = "persistence-unit-1")
    private EntityManager em;

    public Enrollment find(long id) {
        return em.find(Enrollment.class, id);
    }

    public List<Enrollment> findAll() {
        return em.createQuery("FROM Enrollment").getResultList();
    }

    @Transactional
    public Enrollment saveOrUpdate(Enrollment enrollment) {
        if (enrollment.isNew()) {
            em.persist(enrollment);
        } else {
            em.merge(enrollment);
        }
        em.flush();

        return enrollment;
    }

    @Transactional
    public void delete(long id) {
        Enrollment enrollment = em.getReference(Enrollment.class, id);
        if (Objects.nonNull(enrollment)) {
            em.remove(enrollment);
        }
        em.flush();
    }
}