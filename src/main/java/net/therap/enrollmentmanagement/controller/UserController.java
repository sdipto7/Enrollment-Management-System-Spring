package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Role;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
public class UserController extends HttpServlet {

    private UserService userService;

    public UserController() {
        userService = new UserService();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String doPost(@RequestParam("name") String name,
                         @RequestParam("role") String role,
                         @RequestParam("action") String action,
                         @RequestParam(value = "userId", required = false, defaultValue = "0") long userId,
                         HttpSession session,
                         ModelMap modelMap) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }

        switch (Action.getAction(action)) {
            case SAVE:
                save(name, role, modelMap);
                break;
            case UPDATE:
                update(userId, name, role, modelMap);
                break;
            default:
                break;
        }
        return "userList";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String doGet(@RequestParam("action") String action,
                        @RequestParam(value = "userId", required = false, defaultValue = "0") long userId,
                        HttpSession session,
                        ModelMap modelMap) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }
        switch (Action.getAction(action)) {
            case EDIT:
                edit(userId, modelMap);
                return "user";
            case VIEW:
                showAll(modelMap);
                break;
            case DELETE:
                delete(userId, modelMap);
                break;
            default:
                break;
        }
        return "userList";
    }

    public void showAll(ModelMap modelMap) {
        modelMap.addAttribute("userList", userService.findAll());
    }

    public void edit(long userId, ModelMap modelMap) {
        if (userId == 0) {
            modelMap.addAttribute("action", "save");
        } else {
            modelMap.addAttribute("action", "update");
            modelMap.addAttribute("user", userService.find(userId));
            modelMap.addAttribute("userId", userId);
        }
    }

    public void save(String name, String role, ModelMap modelMap) {
        User user = new User();
        user.setName(name);
        user.setRole(Role.valueOf(role.toUpperCase()));

        userService.saveOrUpdate(user);
        showAll(modelMap);
    }

    public void update(long userId, String name, String role, ModelMap modelMap) {
        User updatedUser = userService.find(userId);
        updatedUser.setName(name);
        updatedUser.setRole(Role.valueOf(role.toUpperCase()));

        userService.saveOrUpdate(updatedUser);
        showAll(modelMap);
    }

    public void delete(long userId, ModelMap modelMap) {
        userService.delete(userId);
        showAll(modelMap);
    }
}