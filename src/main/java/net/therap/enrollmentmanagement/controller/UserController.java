package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.AccessManager;
import net.therap.enrollmentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private static final String DONE_PAGE = "redirect:/home";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss"), true));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam Action action,
                       @RequestParam(defaultValue = "0") long userId,
                       HttpSession session,
                       SessionStatus sessionStatus,
                       RedirectAttributes redirectAttributes,
                       ModelMap model) throws GlobalExceptionHandler {

        AccessManager.checkLogin(session);

        switch (action) {
            case UPDATE:
                setupReferenceData(action, userId, model);
                return SAVE_PAGE;
            case VIEW:
                setupReferenceData(action, userId, model);
                break;
            case DELETE:
                userService.delete(userId);
                sessionStatus.setComplete();
                redirectAttributes.addFlashAttribute("success", "User Successfully Deleted");
                return DONE_PAGE;
            default:
                break;
        }

        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute User user,
                          Errors errors,
                          HttpSession session,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttributes) throws GlobalExceptionHandler {

        AccessManager.checkLogin(session);

        if (errors.hasErrors()) {
            return SAVE_PAGE;
        }
        userService.saveOrUpdate(user);
        sessionStatus.setComplete();
        redirectAttributes.addFlashAttribute("success", "User Successfully Saved");

        return DONE_PAGE;
    }

    public void setupReferenceData(Action action, long userId, ModelMap model) {
        switch (action) {
            case VIEW:
                model.addAttribute("userList", userService.findAll());
                break;
            case UPDATE:
                model.addAttribute("user", userService.getOrCreateUser(userId));
                break;
            default:
                break;
        }
    }
}