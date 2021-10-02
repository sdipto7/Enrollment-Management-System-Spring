package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.dao.UserDao;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

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

    public void getOrCreateUser(long userId, ModelMap model) {
        if (userId == 0) {
            model.addAttribute("action", "save");
            model.addAttribute("user", new User());
        } else {
            model.addAttribute("action", "update");
            model.addAttribute("user", find(userId));
        }
    }

    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }

    public void delete(long id) {
        userDao.delete(id);
    }
}