package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.editor.ActionEditor;
import net.therap.enrollmentmanagement.service.AccessManager;
import net.therap.enrollmentmanagement.service.CourseService;
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
 * @since 9/9/21
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    private static final String VIEW_PAGE = "courseList";
    private static final String SAVE_PAGE = "course";
    private static final String DONE_PAGE = "success";

    private Course course;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Action.class, new ActionEditor());
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam Action action,
                       @RequestParam(defaultValue = "0") long courseId,
                       HttpSession session,
                       ModelMap model) throws GlobalExceptionHandler {

        AccessManager.checkLogin(session);

        switch (action) {
            case SAVE:
                course = courseService.getOrCreateCourse(courseId);
                setupReferenceData(action, model);
                return SAVE_PAGE;
            case VIEW:
                setupReferenceData(action, model);
                break;
            case DELETE:
                courseService.delete(courseId);
                setupReferenceData(action, model);
                return DONE_PAGE;
            default:
                break;
        }

        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute Course course,
                          Errors errors,
                          @RequestParam Action action,
                          HttpSession session,
                          ModelMap model) throws GlobalExceptionHandler {

        AccessManager.checkLogin(session);

        if (errors.hasErrors()) {
            return SAVE_PAGE;
        }
        courseService.saveOrUpdate(course);
        setupReferenceData(action, model);

        return DONE_PAGE;
    }

    public void setupReferenceData(Action action, ModelMap model) {
        if (action.equals(Action.VIEW)) {
            model.addAttribute("courseList", courseService.findAll());
        } else if (action.equals(Action.SAVE)) {
            model.addAttribute("course", course);
            model.addAttribute("entity", "Course");
            model.addAttribute("operation", "Saved");
        } else {
            model.addAttribute("entity", "Course");
            model.addAttribute("operation", "Deleted");
        }
    }
}
