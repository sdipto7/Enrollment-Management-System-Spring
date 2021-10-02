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
import org.springframework.validation.BindingResult;
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, "user", new UserEditor());
        binder.registerCustomEditor(Course.class, "course", new CourseEditor());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam("action") String action,
                       @RequestParam(value = "enrollmentId", defaultValue = "0") long enrollmentId,
                       HttpSession session,
                       ModelMap model) {
        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }
        switch (Action.getAction(action)) {
            case EDIT:
                enrollmentService.getOrCreateCourse(enrollmentId, model);
                return "enrollment";
            case VIEW:
                showAll(model);
                break;
            case DELETE:
                enrollmentService.delete(enrollmentId);
                showAll(model);
                break;
            default:
                break;
        }
        return "enrollmentList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Enrollment enrollment,
                       HttpSession session,
                       BindingResult result,
                       ModelMap model) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }

        if (result.hasErrors()) {
            return "enrollmentList";
        }
        enrollmentService.saveOrUpdate(enrollment);
        showAll(model);

        return "enrollmentList";
    }

    public void showAll(ModelMap model) {
        model.addAttribute("enrollmentList", enrollmentService.findAll());
    }
}
