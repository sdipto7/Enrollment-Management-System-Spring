package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.helper.AccessChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rumi.dipto
 * @since 9/9/21
 */
@Controller
public class HomeController {

    @Autowired
    private AccessChecker accessChecker;

    @RequestMapping("/home")
    public String show() {
        return "home";
    }
}
