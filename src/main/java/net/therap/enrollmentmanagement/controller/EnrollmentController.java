package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.editor.CourseEditor;
import net.therap.enrollmentmanagement.editor.UserEditor;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private static final String DONE_PAGE = "redirect:/home";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new UserEditor());
        binder.registerCustomEditor(Course.class, new CourseEditor());
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"), true));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam Action action,
                       @RequestParam(defaultValue = "0") long enrollmentId,
                       SessionStatus sessionStatus,
                       RedirectAttributes redirectAttributes,
                       ModelMap model) {

        switch (action) {
            case SAVE:
                setupReferenceData(action, enrollmentId, model);
                return SAVE_PAGE;
            case VIEW:
                setupReferenceData(action, enrollmentId, model);
                break;
            case DELETE:
                enrollmentService.delete(enrollmentId);
                sessionStatus.setComplete();
                redirectAttributes.addFlashAttribute("success", "Enrollment Successfully Deleted");
                return DONE_PAGE;
            default:
                break;
        }
        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute Enrollment enrollment,
                          Errors errors,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return SAVE_PAGE;
        }
        enrollmentService.saveOrUpdate(enrollment);
        sessionStatus.setComplete();
        redirectAttributes.addFlashAttribute("success", "Enrollment Successfully Saved");

        return DONE_PAGE;
    }

    public void setupReferenceData(Action action, long enrollmentId, ModelMap model) {
        switch (action) {
            case VIEW:
                model.addAttribute("enrollmentList", enrollmentService.findAll());
                break;
            case SAVE:
                model.addAttribute("enrollment", enrollmentService.getOrCreateEnrollment(enrollmentId));
                model.addAttribute("courseList", courseService.findAll());
                model.addAttribute("userList", userService.findAll());
                break;
            default:
                break;
        }
    }
}
