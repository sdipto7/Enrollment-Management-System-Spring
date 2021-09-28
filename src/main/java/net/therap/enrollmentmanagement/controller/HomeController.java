package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
@Controller
public class HomeController extends HttpServlet {

    @RequestMapping("/home")
    public String doGet(HttpSession session) {
        if (SessionUtil.checkInvalidLogin(session)) {
            return "login";
        }
        return "home";
    }
}
