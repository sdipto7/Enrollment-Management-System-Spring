package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Role;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.helper.AccessChecker;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.utils.Url;
import net.therap.enrollmentmanagement.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Locale;

import static net.therap.enrollmentmanagement.controller.AuthController.AUTH_USER_CMD;
import static net.therap.enrollmentmanagement.controller.UserController.*;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
@RequestMapping("/user")
@SessionAttributes({USER_CMD, USER_LIST, ROLE_LIST})
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccessChecker accessChecker;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    private static final String LIST_VIEW_PAGE = "userList";
    private static final String VIEW_PAGE = "user";
    public static final String USER_CMD = "user";
    public static final String USER_LIST = "userList";
    public static final String ROLE_LIST = "roleList";

    @InitBinder(USER_CMD)
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);

        binder.addValidators(userValidator);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showList(@RequestParam Action action,
                           ModelMap model) {

        setupReferenceData(action, 0, model);

        return LIST_VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam(defaultValue = "SAVE") Action action,
                       @RequestParam(defaultValue = "0") long userId,
                       HttpSession session,
                       ModelMap model) {

        User sessionUser = (User) session.getAttribute(AUTH_USER_CMD);
        accessChecker.checkAdminAccess(sessionUser);

        setupReferenceData(action, userId, model);

        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute User user,
                          Errors errors,
                          @RequestParam Action action,
                          SessionStatus sessionStatus,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        User sessionUser = (User) session.getAttribute(AUTH_USER_CMD);
        accessChecker.checkAdminAccess(sessionUser);

        if (errors.hasErrors()) {
            return VIEW_PAGE;
        }

        if (action == Action.DELETE) {
            userService.delete(user);
        } else {
            userService.saveOrUpdate(user);
        }

        sessionStatus.setComplete();
        setupSuccessData(redirectAttributes);

        return "redirect:" + Url.DONE_URL;
    }

    public void setupReferenceData(Action action, long userId, ModelMap model) {
        if (action == Action.VIEW) {
            model.addAttribute(USER_LIST, userService.findAll());
        } else {
            model.addAttribute(USER_CMD, userService.getOrCreateUser(userId));
            model.addAttribute(ROLE_LIST, Arrays.asList(Role.values()));
        }
    }

    public void setupSuccessData(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success",
                messageSource.getMessage("user.success.msg", null, Locale.ENGLISH));
    }
}