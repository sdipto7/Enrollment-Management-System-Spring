package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.UserDao;
import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.domain.User;

import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/25/21
 */
public class UserService {

    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

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

    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }

    public void delete(long id) {
        userDao.delete(id);
    }
}