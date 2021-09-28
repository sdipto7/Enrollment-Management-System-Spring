package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Credential;
import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 9/10/21
 */
@Controller
public class LoginController {

    private UserService userService;

    public LoginController() {
        userService = new UserService();
    }

    @RequestMapping(value = "/loginForm", method = RequestMethod.POST)
    public String login(@ModelAttribute Credential credential, HttpSession session) {

        User user = userService.findByCredential(credential);

        if (Objects.nonNull(user)) {
            session.setAttribute("currentUser", user);
            return "home";
        } else {
            return "login";
        }
    }

    @RequestMapping("/")
    public String loginPage() {
        return "login";
    }
}