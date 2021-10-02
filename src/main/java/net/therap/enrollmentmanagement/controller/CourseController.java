package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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

    private static final String VIEW_PAGE = "courseList";

    private static final String SAVE_PAGE = "course";

    private static final String LOGIN_PAGE = "login";

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam("action") String action,
                       @RequestParam(value = "courseId", defaultValue = "0") long courseId,
                       HttpSession session,
                       ModelMap model) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return LOGIN_PAGE;
        }

        switch (Action.getAction(action)) {
            case EDIT:
                courseService.getOrCreateCourse(courseId, model);
                return SAVE_PAGE;
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

        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Course course,
                       HttpSession session,
                       BindingResult result,
                       ModelMap model) {

        if (SessionUtil.checkInvalidLogin(session)) {
            return LOGIN_PAGE;
        }

        if (result.hasErrors()) {
            return VIEW_PAGE;
        }
        courseService.saveOrUpdate(course);
        showAll(model);

        return VIEW_PAGE;
    }

    public void showAll(ModelMap model) {
        model.addAttribute("courseList", courseService.findAll());
    }
}
