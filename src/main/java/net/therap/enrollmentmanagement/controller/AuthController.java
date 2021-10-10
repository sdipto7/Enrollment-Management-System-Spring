package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
@SessionAttributes("currentUser")
public class AuthController {

    @Autowired
    private UserService userService;

    private static final String LOGIN_PAGE = "login";

    private static final String HOME_PAGE = "home";

    private static final String AUTH_USER_CMD = "currentUser";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewLoginPage(ModelMap model) {
        model.addAttribute("credential", new Credential());

        return LOGIN_PAGE;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session,
                         SessionStatus sessionStatus,
                         ModelMap model) {
        session.invalidate();
        sessionStatus.setComplete();
        model.addAttribute("credential", new Credential());

        return LOGIN_PAGE;
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
        if (Objects.nonNull(user)) {
            model.addAttribute(AUTH_USER_CMD, user);
            return HOME_PAGE;
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Wrong username or password");
            return "redirect:/";
        }
    }
}