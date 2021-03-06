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

    @PersistenceContext(unitName = "enrollment-management-persistence-unit")
    private EntityManager em;

    public Enrollment find(long id) {
        return em.find(Enrollment.class, id);
    }

    public Enrollment findByUserAndCourse(long userId, long courseId) {
        return em.createQuery("FROM Enrollment e WHERE e.user.id = :userId AND e.course.id = :courseId", Enrollment.class)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<Enrollment> findAll() {
        return em.createQuery("FROM Enrollment")
                .getResultList();
    }

    @Transactional
    public Enrollment saveOrUpdate(Enrollment enrollment) {
        if (enrollment.isNew()) {
            em.persist(enrollment);
        } else {
            enrollment = em.merge(enrollment);
        }
        em.flush();

        return enrollment;
    }

    @Transactional
    public void delete(Enrollment enrollment) {
        if (Objects.nonNull(enrollment)) {
            em.remove(em.contains(enrollment) ? enrollment : em.merge(enrollment));
        }
        em.flush();
    }
}