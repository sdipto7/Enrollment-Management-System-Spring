package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.editor.CourseEditor;
import net.therap.enrollmentmanagement.editor.UserEditor;
import net.therap.enrollmentmanagement.service.EnrollmentService;
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
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    private static final String VIEW_PAGE = "enrollmentList";

    private static final String SAVE_PAGE = "enrollment";

    private static final String LOGIN_PAGE = "login";

    private static final String DONE_PAGE = "success";

    private Enrollment enrollment;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, "user", new UserEditor());
        binder.registerCustomEditor(Course.class, "course", new CourseEditor());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam String action,
                       @RequestParam(defaultValue = "0") long enrollmentId,
                       HttpSession session,
                       ModelMap model) {
        if (SessionUtil.checkInvalidLogin(session)) {
            return LOGIN_PAGE;
        }
        switch (Action.getAction(action)) {
            case EDIT:
                enrollment = enrollmentService.getOrCreateEnrollment(enrollmentId);
                setupReferenceData(Action.getAction(action), model);
                return SAVE_PAGE;
            case VIEW:
                setupReferenceData(Action.getAction(action), model);
                break;
            case DELETE:
                enrollmentService.delete(enrollmentId);
                setupReferenceData(Action.getAction(action), model);
                break;
            default:
                break;
        }
        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute Enrollment enrollment,
                          Errors errors,
                          HttpSession session,
                          ModelMap model) {
        if (SessionUtil.checkInvalidLogin(session)) {
            return LOGIN_PAGE;
        }

        if (errors.hasErrors()) {
            return SAVE_PAGE;
        }
        enrollmentService.saveOrUpdate(enrollment);

        return DONE_PAGE;
    }

    public void setupReferenceData(Action action, ModelMap model) {
        if (action.equals(Action.VIEW)) {
            model.addAttribute("enrollmentList", enrollmentService.findAll());
        } else if (action.equals(Action.EDIT)) {
            model.addAttribute("enrollment", enrollment);
        }
    }
}
