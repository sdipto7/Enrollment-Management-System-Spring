package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.helper.AuthChecker;
import net.therap.enrollmentmanagement.service.UserService;
import net.therap.enrollmentmanagement.utils.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Objects;

import static net.therap.enrollmentmanagement.controller.AuthController.AUTH_USER_CMD;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
@SessionAttributes(AUTH_USER_CMD)
public class AuthController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AuthChecker authChecker;

    @Autowired
    private UserService userService;

    private static final String LOGIN_PAGE = "login";
    private static final String HOME_PAGE = "home";
    public static final String AUTH_USER_CMD = "currentUser";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewLoginPage() {
        return "redirect:" + Url.LOGIN_URL;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        setupReferenceData(model);

        return LOGIN_PAGE;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session,
                         HttpServletResponse response,
                         SessionStatus sessionStatus) {

        session.removeAttribute(AUTH_USER_CMD);
        session.invalidate();
        sessionStatus.setComplete();

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        return "redirect:" + Url.LOGIN_URL;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute Credential credential,
                        Errors errors,
                        RedirectAttributes redirectAttributes,
                        ModelMap model) {

        if (errors.hasErrors()) {
            return LOGIN_PAGE;
        }

        User user = userService.findByCredential(credential);
        if (Objects.nonNull(user) && authChecker.check(user, credential.getPassword())) {
            model.addAttribute(AUTH_USER_CMD, user);
            return HOME_PAGE;
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("error.msg", null, Locale.ENGLISH));
            return "redirect:" + Url.LOGIN_URL;
        }
    }

    public void setupReferenceData(ModelMap model) {
        model.addAttribute("credential", new Credential());
    }
}