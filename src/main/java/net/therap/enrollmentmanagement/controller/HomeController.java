package net.therap.enrollmentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String doGet() {
        return "home";
    }
}
