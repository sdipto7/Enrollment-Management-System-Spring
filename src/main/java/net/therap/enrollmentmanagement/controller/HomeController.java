package net.therap.enrollmentmanagement.controller;

//import net.therap.enrollmentmanagement.util.SessionUtil;

import net.therap.enrollmentmanagement.service.AccessManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String doGet(HttpSession session) throws GlobalExceptionHandler {
        AccessManager.checkLogin(session);

        return "home";
    }
}
