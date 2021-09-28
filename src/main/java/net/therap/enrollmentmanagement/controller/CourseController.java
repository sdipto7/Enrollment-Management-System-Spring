package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public String doPost(@ModelAttribute Course course,
                         @RequestParam("action") String action,
                         @RequestParam(value = "courseId", required = false, defaultValue = "0") long courseId,
                         HttpSession session,
                         ModelMap modelMap) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }

        switch (Action.getAction(action)) {
            case SAVE:
                courseService.save(course);
                showAll(modelMap);
                break;
            case UPDATE:
                courseService.update(course, courseService.find(courseId));
                showAll(modelMap);
                break;
            default:
                break;
        }
        return "courseList";
    }

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public String doGet(@RequestParam("action") String action,
                        @RequestParam(value = "courseId", required = false, defaultValue = "0") long courseId,
                        HttpSession session,
                        ModelMap modelMap) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }
        switch (Action.getAction(action)) {
            case EDIT:
                edit(courseId, modelMap);
                return "course";
            case VIEW:
                showAll(modelMap);
                break;
            case DELETE:
                courseService.delete(courseId);
                showAll(modelMap);
                break;
            default:
                break;
        }
        return "courseList";
    }

    public void showAll(ModelMap modelMap) {
        modelMap.addAttribute("courseList", courseService.findAll());
    }

    public void edit(long courseId, ModelMap modelMap) {
        if (courseId == 0) {
            modelMap.addAttribute("action", "save");
        } else {
            modelMap.addAttribute("action", "update");
            modelMap.addAttribute("course", courseService.find(courseId));
            modelMap.addAttribute("courseId", courseId);
        }
    }
}
