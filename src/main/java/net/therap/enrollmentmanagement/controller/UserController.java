package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Role;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.editor.RoleEditor;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
@RequestMapping("/user")
public class UserController extends HttpServlet {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, "role", new RoleEditor());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam("action") String action,
                       @RequestParam(value = "userId", defaultValue = "0") long userId,
                       HttpSession session,
                       ModelMap model) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }
        switch (Action.getAction(action)) {
            case EDIT:
                userService.getOrCreateUser(userId, model);
                return "user";
            case VIEW:
                showAll(model);
                break;
            case DELETE:
                userService.delete(userId);
                showAll(model);
                break;
            default:
                break;
        }
        return "userList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute User user,
                       HttpSession session,
                       BindingResult result,
                       ModelMap model) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }

        if (result.hasErrors()) {
            return "userList";
        }
        userService.saveOrUpdate(user);
        showAll(model);

        return "userList";
    }

    public void showAll(ModelMap model) {
        model.addAttribute("userList", userService.findAll());
    }
}