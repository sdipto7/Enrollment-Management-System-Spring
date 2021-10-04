package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.editor.ActionEditor;
import net.therap.enrollmentmanagement.editor.CourseEditor;
import net.therap.enrollmentmanagement.editor.UserEditor;
import net.therap.enrollmentmanagement.service.AccessManager;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.service.EnrollmentService;
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
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    private static final String VIEW_PAGE = "enrollmentList";

    private static final String SAVE_PAGE = "enrollment";

    private static final String DONE_PAGE = "success";

    private Enrollment enrollment;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new UserEditor());
        binder.registerCustomEditor(Course.class, new CourseEditor());
        binder.registerCustomEditor(Action.class, new ActionEditor());
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam Action action,
                       @RequestParam(defaultValue = "0") long enrollmentId,
                       HttpSession session,
                       ModelMap model) throws GlobalExceptionHandler {

        AccessManager.checkLogin(session);

        switch (action) {
            case SAVE:
                enrollment = enrollmentService.getOrCreateEnrollment(enrollmentId);
                setupReferenceData(action, model);
                return SAVE_PAGE;
            case VIEW:
                setupReferenceData(action, model);
                break;
            case DELETE:
                enrollmentService.delete(enrollmentId);
                setupReferenceData(action, model);
                return DONE_PAGE;
            default:
                break;
        }
        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute Enrollment enrollment,
                          Errors errors,
                          @RequestParam Action action,
                          HttpSession session,
                          ModelMap model) throws GlobalExceptionHandler {

        AccessManager.checkLogin(session);

        if (errors.hasErrors()) {
            return SAVE_PAGE;
        }
        enrollmentService.saveOrUpdate(enrollment);
        setupReferenceData(action, model);

        return DONE_PAGE;
    }

    public void setupReferenceData(Action action, ModelMap model) {
        if (action.equals(Action.VIEW)) {
            model.addAttribute("enrollmentList", enrollmentService.findAll());
        } else if (action.equals(Action.SAVE)) {
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courseList", courseService.findAll());
            model.addAttribute("userList", userService.findAll());
        } else if (action.equals(Action.DELETE)) {
            model.addAttribute("entity", "Enrollment");
            model.addAttribute("operation", "Deleted");
        } else {
            model.addAttribute("entity", "Enrollment");
            model.addAttribute("operation", "Saved");
        }
    }
}
