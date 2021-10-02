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
        binder.registerCustomEditor(User.class, "userName", new UserEditor());
        binder.registerCustomEditor(Course.class, "courseCode", new CourseEditor());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@Valid @ModelAttribute Enrollment enrollment,
                         @RequestParam("action") String action,
                         @RequestParam(value = "enrollmentId", required = false, defaultValue = "0") long enrollmentId,
                         HttpSession session,
                         ModelMap model) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }

        switch (Action.getAction(action)) {
            case SAVE:
                enrollmentService.save(enrollment);
                showAll(model);
                break;
            case UPDATE:
                enrollmentService.update(enrollment, enrollmentService.find(enrollmentId));
                showAll(model);
                break;
            default:
                break;
        }
        return "enrollmentList";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(@RequestParam("action") String action,
                        @RequestParam(value = "enrollmentId", required = false, defaultValue = "0") long enrollmentId,
                        HttpSession session,
                        ModelMap model) {
        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }
        switch (Action.getAction(action)) {
            case EDIT:
                edit(enrollmentId, model);
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

    public void showAll(ModelMap model) {
        model.addAttribute("enrollmentList", enrollmentService.findAll());
    }

    public void edit(long enrollmentId, ModelMap model) {
        if (enrollmentId == 0) {
            model.addAttribute("action", "save");
        } else {
            model.addAttribute("action", "update");
            model.addAttribute("enrollment", enrollmentService.find(enrollmentId));
            model.addAttribute("enrollmentId", enrollmentId);
        }
    }
}
