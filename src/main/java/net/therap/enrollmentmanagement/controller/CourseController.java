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
import javax.validation.Valid;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@Valid @ModelAttribute Course course,
                         @RequestParam("action") String action,
                         HttpSession session,
                         ModelMap model) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }

        switch (Action.getAction(action)) {
            case SAVE:
                courseService.saveOrUpdate(course);
                showAll(model);
                break;
            case UPDATE:
                courseService.saveOrUpdate(course);
                showAll(model);
                break;
            default:
                break;
        }
        return "courseList";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(@RequestParam("action") String action,
                        @RequestParam(value = "courseId", required = false, defaultValue = "0") long courseId,
                        HttpSession session,
                        ModelMap model) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }

        model.addAttribute("course", new Course());

        switch (Action.getAction(action)) {
            case EDIT:
                edit(courseId, model);
                return "course";
            case VIEW:
                showAll(model);
                break;
            case DELETE:
                courseService.delete(courseId);
                showAll(model);
                break;
            default:
                break;
        }
        return "courseList";
    }

    public void showAll(ModelMap model) {
        model.addAttribute("courseList", courseService.findAll());
    }

    public void edit(long courseId, ModelMap model) {
        if (courseId == 0) {
            model.addAttribute("action", "save");
        } else {
            model.addAttribute("action", "update");
            model.addAttribute("course", courseService.find(courseId));
            model.addAttribute("courseId", courseId);
        }
    }
}
