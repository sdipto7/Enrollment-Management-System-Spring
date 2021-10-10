package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.utils.Url;
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
 * @since 9/9/21
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    public static final String COURSE_CMD = "course";

    private static final String VIEW_PAGE = "courseList";

    private static final String SAVE_PAGE = "course";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"), true));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showList(@RequestParam Action action,
                           ModelMap model) {
        setupReferenceData(action, 0, model);

        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam Action action,
                       @RequestParam(defaultValue = "0") long courseId,
                       SessionStatus sessionStatus,
                       RedirectAttributes redirectAttributes,
                       ModelMap model) {

        switch (action) {
            case UPDATE:
                setupReferenceData(action, courseId, model);
                return SAVE_PAGE;
            case DELETE:
                courseService.delete(courseId);
                sessionStatus.setComplete();
                setupSuccessData(redirectAttributes);
                return "redirect:" + Url.DONE;
            default:
                break;
        }

        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute Course course,
                          Errors errors,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return SAVE_PAGE;
        }
        courseService.saveOrUpdate(course);

        sessionStatus.setComplete();
        setupSuccessData(redirectAttributes);

        return "redirect:" + Url.DONE;
    }

    public void setupReferenceData(Action action, long courseId, ModelMap model) {
        switch (action) {
            case VIEW:
                model.addAttribute("courseList", courseService.findAll());
                break;
            case UPDATE:
                model.addAttribute("course", courseService.getOrCreateCourse(courseId));
                break;
            default:
                break;
        }
    }

    public void setupSuccessData(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", "Courses Successfully Updated");
    }
}
