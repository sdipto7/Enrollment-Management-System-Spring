package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.propertyeditor.EnrollmentCourseCodeEditor;
import net.therap.enrollmentmanagement.propertyeditor.EnrollmentUserNameEditor;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.service.EnrollmentService;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
public class EnrollmentController extends HttpServlet {

    @Autowired
    private EnrollmentService enrollmentService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, "userName", new EnrollmentUserNameEditor());
        binder.registerCustomEditor(Course.class, "courseCode", new EnrollmentCourseCodeEditor());
    }

    @RequestMapping(value = "/enrollment", method = RequestMethod.POST)
    public String doPost(@ModelAttribute Enrollment enrollment,
                         @RequestParam("action") String action,
                         @RequestParam(value = "enrollmentId", required = false, defaultValue = "0") long enrollmentId,
                         HttpSession session,
                         ModelMap modelMap) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }

        switch (Action.getAction(action)) {
            case SAVE:
                save(enrollment, modelMap);
                break;
            case UPDATE:
                update(enrollment, enrollmentId, modelMap);
                break;
            default:
                break;
        }
        return "enrollmentList";
    }

    @RequestMapping(value = "/enrollment", method = RequestMethod.GET)
    public String doGet(@RequestParam("action") String action,
                        @RequestParam(value = "enrollmentId", required = false, defaultValue = "0") long enrollmentId,
                        HttpSession session,
                        ModelMap modelMap) {
        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }
        switch (Action.getAction(action)) {
            case EDIT:
                edit(enrollmentId, modelMap);
                return "enrollment";
            case VIEW:
                showAll(modelMap);
                break;
            case DELETE:
                delete(enrollmentId, modelMap);
                break;
            default:
                break;
        }
        return "enrollmentList";
    }

    public void showAll(ModelMap modelMap) {
        modelMap.addAttribute("enrollmentList", enrollmentService.findAll());
    }

    public void edit(long enrollmentId, ModelMap modelMap) {
        if (enrollmentId == 0) {
            modelMap.addAttribute("action", "save");
        } else {
            modelMap.addAttribute("action", "update");
            modelMap.addAttribute("enrollment", enrollmentService.find(enrollmentId));
            modelMap.addAttribute("enrollmentId", enrollmentId);
        }
    }

    public void save(Enrollment enrollment, ModelMap modelMap) {
        enrollmentService.saveOrUpdate(enrollment);
        showAll(modelMap);
    }

    public void update(Enrollment enrollment, long enrollmentId, ModelMap modelMap) {
        Enrollment updatedEnrollment = enrollmentService.find(enrollmentId);
        updatedEnrollment.setUser(enrollment.getUser());
        updatedEnrollment.setCourse(enrollment.getCourse());

        enrollmentService.saveOrUpdate(updatedEnrollment);
        showAll(modelMap);
    }

    public void delete(long enrollmentId, ModelMap modelMap) {
        enrollmentService.delete(enrollmentId);
        showAll(modelMap);
    }
}
