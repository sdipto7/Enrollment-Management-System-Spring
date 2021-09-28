package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Role;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.propertyeditor.UserRoleEditor;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
public class UserController extends HttpServlet {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, "role", new UserRoleEditor());
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String doPost(@ModelAttribute User user,
                         @RequestParam("action") String action,
                         @RequestParam(value = "userId", required = false, defaultValue = "0") long userId,
                         HttpSession session,
                         ModelMap modelMap) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }

        switch (Action.getAction(action)) {
            case SAVE:
                userService.save(user);
                showAll(modelMap);
                break;
            case UPDATE:
                userService.update(user, userService.find(userId));
                showAll(modelMap);
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
                userService.delete(userId);
                showAll(modelMap);
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
}