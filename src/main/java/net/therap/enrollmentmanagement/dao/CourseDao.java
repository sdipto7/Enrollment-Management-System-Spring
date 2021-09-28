package net.therap.enrollmentmanagement.dao;

import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.util.EntityManagerSingleton;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
@Component
public class CourseDao {

    private EntityManager em;

    public CourseDao() {
        em = EntityManagerSingleton.getInstance().getEntityManager();
    }

    public Course find(long id) {
        return em.find(Course.class, id);
    }

    public Course findByCourseCode(String courseCode) {
        return (Course) em.createQuery("FROM Course c WHERE c.courseCode = :code")
                .setParameter("code", courseCode)
                .getSingleResult();
    }

    public List<Course> findAll() {
        return em.createQuery("FROM Course")
                .getResultList();
    }

    public Course saveOrUpdate(Course course) {
        em.getTransaction().begin();

        if (course.isNew()) {
            System.out.println("NEW");
            em.persist(course);
        } else {
            System.out.println("OLD");
            em.merge(course);
        }
        em.flush();
        em.getTransaction().commit();

        return course;
    }

    public void delete(long id) {
        Course course = em.getReference(Course.class, id);

        em.getTransaction().begin();
        if (Objects.nonNull(course)) {
            em.remove(course);
        }
        em.flush();
        em.getTransaction().commit();
    }
}