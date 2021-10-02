package net.therap.enrollmentmanagement.dao;

import net.therap.enrollmentmanagement.domain.Course;
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
public class CourseDao {

    @PersistenceContext(unitName = "persistence-unit-1")
    private EntityManager em;

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

    @Transactional
    public Course saveOrUpdate(Course course) {
        if (course.isNew()) {
            em.persist(course);
        } else {
            course = em.merge(course);
        }
        em.flush();

        return course;
    }

    @Transactional
    public void delete(long id) {
        Course course = em.getReference(Course.class, id);
        if (Objects.nonNull(course)) {
            em.remove(course);
        }
        em.flush();
    }
}