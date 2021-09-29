package net.therap.enrollmentmanagement.dao;

import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.util.EntityManagerSingleton;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
@Repository
public class EnrollmentDao {

    private EntityManager em;

    public EnrollmentDao() {
        em = EntityManagerSingleton.getInstance().getEntityManager();
    }

    public Enrollment find(long id) {
        return em.find(Enrollment.class, id);
    }

    public List<Enrollment> findAll() {
        return em.createQuery("FROM Enrollment").getResultList();
    }

    public Enrollment saveOrUpdate(Enrollment enrollment) {
        em.getTransaction().begin();

        if (enrollment.isNew()) {
            em.persist(enrollment);
        } else {
            em.merge(enrollment);
        }
        em.flush();
        em.getTransaction().commit();

        return enrollment;
    }

    public void delete(long id) {
        Enrollment enrollment = em.getReference(Enrollment.class, id);

        em.getTransaction().begin();
        if (Objects.nonNull(enrollment)) {
            em.remove(enrollment);
        }
        em.flush();
        em.getTransaction().commit();
    }
}