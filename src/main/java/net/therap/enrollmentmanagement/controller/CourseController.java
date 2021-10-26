package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Action;
import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.helper.AccessChecker;
import net.therap.enrollmentmanagement.service.CourseService;
import net.therap.enrollmentmanagement.utils.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static net.therap.enrollmentmanagement.controller.AuthController.AUTH_USER_CMD;
import static net.therap.enrollmentmanagement.controller.CourseController.COURSE_CMD;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
@Controller
@RequestMapping("/course")
@SessionAttributes(COURSE_CMD)
public class CourseController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccessChecker accessChecker;

    @Autowired
    private CourseService courseService;

    private static final String LIST_VIEW_PAGE = "courseList";
    private static final String VIEW_PAGE = "course";
    public static final String COURSE_CMD = "course";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);

        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"), true));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showList(@RequestParam Action action,
                           ModelMap model) {

        setupReferenceData(action, 0, model);

        return LIST_VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam(defaultValue = "SAVE") Action action,
                       @RequestParam(defaultValue = "0") long courseId,
                       HttpSession session,
                       ModelMap model) {

        User sessionUser = (User) session.getAttribute(AUTH_USER_CMD);
        accessChecker.checkAdminAccess(sessionUser);

        setupReferenceData(action, courseId, model);

        return VIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute Course course,
                          Errors errors,
                          @RequestParam Action action,
                          SessionStatus sessionStatus,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        User sessionUser = (User) session.getAttribute(AUTH_USER_CMD);
        accessChecker.checkAdminAccess(sessionUser);

        if (errors.hasErrors()) {
            return VIEW_PAGE;
        }

        if (action == Action.DELETE) {
            courseService.delete(course);
        } else {
            courseService.saveOrUpdate(course);
        }

        sessionStatus.setComplete();
        setupSuccessData(redirectAttributes);

        return "redirect:" + Url.DONE_URL;
    }

    public void setupReferenceData(Action action, long courseId, ModelMap model) {
        if (action == Action.VIEW) {
            model.addAttribute("courseList", courseService.findAll());
        } else {
            model.addAttribute(COURSE_CMD, courseService.getOrCreateCourse(courseId));
        }
    }

    public void setupSuccessData(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success",
                messageSource.getMessage("course.success.msg", null, Locale.ENGLISH));
    }
}
