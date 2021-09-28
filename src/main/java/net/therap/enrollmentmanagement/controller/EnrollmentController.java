package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.service.EnrollmentService;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
public class EnrollmentController extends HttpServlet {

    private EnrollmentService enrollmentService;

    private UserService userService;

    private CourseService courseService;

    public EnrollmentController() {
        enrollmentService = new EnrollmentService();
        userService = new UserService();
        courseService = new CourseService();
    }

    @RequestMapping(value = "/enrollment", method = RequestMethod.POST)
    public String doPost(@RequestParam("name") String userName,
                         @RequestParam("courseCode") String courseCode,
                         @RequestParam("action") String action,
                         @RequestParam(value = "enrollmentId", required = false, defaultValue = "0") long enrollmentId,
                         HttpSession session,
                         ModelMap modelMap) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }

        switch (Action.getAction(action)) {
            case SAVE:
                save(userName, courseCode, modelMap);
                break;
            case UPDATE:
                update(enrollmentId, userName, courseCode, modelMap);
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

    public void save(String userName, String courseCode, ModelMap modelMap) {
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(userService.findByName(userName));
        enrollment.setCourse(courseService.findByCourseCode(courseCode));

        enrollmentService.saveOrUpdate(enrollment);
        showAll(modelMap);
    }

    public void update(long enrollmentId, String userName, String courseCode, ModelMap modelMap) {
        Enrollment updatedEnrollment = enrollmentService.find(enrollmentId);
        updatedEnrollment.setUser(userService.findByName(userName));
        updatedEnrollment.setCourse(courseService.findByCourseCode(courseCode));

        enrollmentService.saveOrUpdate(updatedEnrollment);
        showAll(modelMap);
    }

    public void delete(long enrollmentId, ModelMap modelMap) {
        enrollmentService.delete(enrollmentId);
        showAll(modelMap);
    }
}
