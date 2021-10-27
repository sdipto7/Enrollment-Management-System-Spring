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
import net.therap.enrollmentmanagement.validator.EnrollmentValidator;
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
import java.util.Locale;

import static net.therap.enrollmentmanagement.controller.AuthController.AUTH_USER_CMD;
import static net.therap.enrollmentmanagement.controller.EnrollmentController.*;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
@RequestMapping("/enrollment")
@SessionAttributes({ENROLLMENT_CMD, ENROLLMENT_LIST, COURSE_LIST, USER_LIST})
public class EnrollmentController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccessChecker accessChecker;

    @Autowired
    private EnrollmentValidator enrollmentValidator;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    private static final String LIST_VIEW_PAGE = "enrollmentList";
    private static final String VIEW_PAGE = "enrollment";
    public static final String ENROLLMENT_CMD = "enrollment";
    public static final String ENROLLMENT_LIST = "enrollmentList";
    public static final String COURSE_LIST = "courseList";
    public static final String USER_LIST = "userList";

    @InitBinder(ENROLLMENT_CMD)
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);

        binder.registerCustomEditor(User.class, new UserEditor());
        binder.registerCustomEditor(Course.class, new CourseEditor());
        binder.addValidators(enrollmentValidator);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showList(@RequestParam Action action,
                           ModelMap model) {

        setupReferenceData(action, 0, model);

        return LIST_VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam(defaultValue = "SAVE") Action action,
                       @RequestParam(defaultValue = "0") long enrollmentId,
                       HttpSession session,
                       ModelMap model) {

        User sessionUser = (User) session.getAttribute(AUTH_USER_CMD);
        accessChecker.checkAdminAccess(sessionUser);

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
        accessChecker.checkAdminAccess(sessionUser);

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
            model.addAttribute(ENROLLMENT_LIST, enrollmentService.findAll());
        } else {
            model.addAttribute(ENROLLMENT_CMD, enrollmentService.getOrCreateEnrollment(enrollmentId));
            model.addAttribute(COURSE_LIST, courseService.findAll());
            model.addAttribute(USER_LIST, userService.findAll());
        }
    }

    public void setupSuccessData(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success",
                messageSource.getMessage("enrollment.success.msg", null, Locale.ENGLISH));
    }
}
