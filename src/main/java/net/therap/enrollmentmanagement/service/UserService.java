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

    public void save(User user) {
        userDao.saveOrUpdate(user);
    }

    public void update(User user, User updatedUser) {
        updatedUser.setName(user.getName());
        updatedUser.setRole(user.getRole());
        userDao.saveOrUpdate(updatedUser);
    }

    public void delete(long id) {
        userDao.delete(id);
    }
}