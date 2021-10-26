package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.editor.CourseEditor;
import net.therap.enrollmentmanagement.editor.UserEditor;
import net.therap.enrollmentmanagement.helper.AccessChecker;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.service.EnrollmentService;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.utils.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static net.therap.enrollmentmanagement.controller.AuthController.AUTH_USER_CMD;
import static net.therap.enrollmentmanagement.controller.EnrollmentController.ENROLLMENT_CMD;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
@RequestMapping("/enrollment")
@SessionAttributes(ENROLLMENT_CMD)
public class EnrollmentController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccessChecker accessChecker;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    private static final String LIST_VIEW_PAGE = "enrollmentList";
    private static final String VIEW_PAGE = "enrollment";
    public static final String ENROLLMENT_CMD = "enrollment";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new UserEditor());
        binder.registerCustomEditor(Course.class, new CourseEditor());
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"), true));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showList(@RequestParam Action action,
                           HttpSession session,
                           ModelMap model) {

        User sessionUser = (User) session.getAttribute(AUTH_USER_CMD);
        accessChecker.checkViewAccess(sessionUser);

        setupReferenceData(action, 0, model);

        return LIST_VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam(defaultValue = "SAVE") Action action,
                       @RequestParam(defaultValue = "0") long enrollmentId,
                       HttpSession session,
                       ModelMap model) {

        User sessionUser = (User) session.getAttribute(AUTH_USER_CMD);
        accessChecker.checkViewAccess(sessionUser);

        setupReferenceData(action, enrollmentId, model);

        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute Enrollment enrollment,
                          Errors errors,
                          @RequestParam Action action,
                          SessionStatus sessionStatus,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        User sessionUser = (User) session.getAttribute(AUTH_USER_CMD);
        accessChecker.checkEditAccess(sessionUser);

        if (errors.hasErrors()) {
            return VIEW_PAGE;
        }

        if (action == Action.DELETE) {
            enrollmentService.delete(enrollment);
        } else {
            enrollmentService.saveOrUpdate(enrollment);
        }

        sessionStatus.setComplete();
        setupSuccessData(redirectAttributes);

        return "redirect:" + Url.DONE_URL;
    }

    public void setupReferenceData(Action action, long enrollmentId, ModelMap model) {
        if (action == Action.VIEW) {
            model.addAttribute("enrollmentList", enrollmentService.findAll());
        } else {
            model.addAttribute(ENROLLMENT_CMD, enrollmentService.getOrCreateEnrollment(enrollmentId));
            model.addAttribute("courseList", courseService.findAll());
            model.addAttribute("userList", userService.findAll());
        }
    }

    public void setupSuccessData(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success",
                messageSource.getMessage("enrollment.success.msg", null, Locale.ENGLISH));
    }
}
