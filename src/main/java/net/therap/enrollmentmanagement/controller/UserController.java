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
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final String VIEW_PAGE = "userList";

    private static final String SAVE_PAGE = "user";

    private static final String LOGIN_PAGE = "login";

    private static final String DONE_PAGE = "success";

    private User user;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, "role", new RoleEditor());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam String action,
                       @RequestParam(defaultValue = "0") long userId,
                       HttpSession session,
                       ModelMap model) {
        if (SessionUtil.checkInvalidLogin(session)) {
            return LOGIN_PAGE;
        }
        switch (Action.getAction(action)) {
            case EDIT:
                user = userService.getOrCreateUser(userId);
                setupReferenceData(Action.getAction(action), model);
                return SAVE_PAGE;
            case VIEW:
                setupReferenceData(Action.getAction(action), model);
                break;
            case DELETE:
                userService.delete(userId);
                setupReferenceData(Action.getAction(action), model);
                break;
            default:
                break;
        }
        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute User user,
                          Errors errors,
                          HttpSession session,
                          ModelMap model) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return LOGIN_PAGE;
        }

        if (errors.hasErrors()) {
            return SAVE_PAGE;
        }
        userService.saveOrUpdate(user);

        return DONE_PAGE;
    }

    public void setupReferenceData(Action action, ModelMap model) {
        if (action.equals(Action.VIEW)) {
            model.addAttribute("userList", userService.findAll());
        } else if (action.equals(Action.EDIT)) {
            model.addAttribute("user", user);
        }
    }
}