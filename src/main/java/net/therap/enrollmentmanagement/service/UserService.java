package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.UserDao;
import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User find(long id) {
        return userDao.find(id);
    }

    public User findByCredential(Credential credential) {
        return userDao.findByCredential(credential);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User getOrCreateUser(long userId) {
        return userId == 0 ? new User() : find(userId);
    }

    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }

    public void delete(long id) {
        userDao.delete(id);
    }
}