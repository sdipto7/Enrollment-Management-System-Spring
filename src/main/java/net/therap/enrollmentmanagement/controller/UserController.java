package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Role;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.editor.RoleEditor;
import net.therap.enrollmentmanagement.service.AccessManager;
import net.therap.enrollmentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        binder.registerCustomEditor(Role.class, new RoleEditor());
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam String action,
                       @RequestParam(defaultValue = "0") long userId,
                       HttpSession session,
                       ModelMap model) throws GlobalExceptionHandler {

        AccessManager.checkLogin(session);

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
                          ModelMap model) throws GlobalExceptionHandler {

        AccessManager.checkLogin(session);

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