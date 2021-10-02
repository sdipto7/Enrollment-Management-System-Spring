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

    @PersistenceContext(unitName = "persistence-unit-1")
    private EntityManager em;

    public User find(long id) {
        return em.find(User.class, id);
    }

    public User findByCredential(Credential credential) {
        return (User) em.createQuery("FROM User u WHERE u.credential.userName = :userName AND u.credential.password = :password")
                .setParameter("userName", credential.getUserName())
                .setParameter("password", credential.getPassword())
                .getSingleResult();
    }

    public User findByName(String name) {
        return (User) em.createQuery("FROM User u WHERE u.name = :name")
                .setParameter("name", name)
                .getSingleResult();
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
            em.merge(user);
        }
        em.flush();

        return user;
    }

    @Transactional
    public void delete(long id) {
        User user = em.getReference(User.class, id);
        if (Objects.nonNull(user)) {
            em.remove(user);
        }
        em.flush();
    }
}