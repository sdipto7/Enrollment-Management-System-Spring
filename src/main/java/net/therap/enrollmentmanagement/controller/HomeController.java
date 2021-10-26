package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.User;
import net.therap.enrollmentmanagement.helper.AccessChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static net.therap.enrollmentmanagement.controller.AuthController.AUTH_USER_CMD;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
@Controller
public class HomeController {

    @Autowired
    private AccessChecker accessChecker;

    @RequestMapping("/home")
    public String show(HttpSession session) {
        User sessionUser = (User) session.getAttribute(AUTH_USER_CMD);
        accessChecker.checkViewAccess(sessionUser);

        return "home";
    }
}
