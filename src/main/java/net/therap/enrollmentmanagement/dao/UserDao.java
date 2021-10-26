package net.therap.enrollmentmanagement.dao;

import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.domain.User;
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
public class UserDao {

    @PersistenceContext(unitName = "enrollment-management-persistence-unit")
    private EntityManager em;

    public User find(long id) {
        return em.find(User.class, id);
    }

    public User findByCredential(Credential credential) {
        return em.createQuery("FROM User u WHERE u.credential.userName = :userName", User.class)
                .setParameter("userName", credential.getUserName())
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public User findByName(String name) {
        return em.createQuery("FROM User u WHERE u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<User> findAll() {
        return em.createQuery("FROM User")
                .getResultList();
    }

    @Transactional
    public User saveOrUpdate(User user) {
        if (user.isNew()) {
            em.persist(user);
        } else {
            user = em.merge(user);
        }
        em.flush();

        return user;
    }

    @Transactional
    public void delete(User user) {
        if (Objects.nonNull(user)) {
            em.remove(em.contains(user) ? user : em.merge(user));
        }
        em.flush();
    }
}