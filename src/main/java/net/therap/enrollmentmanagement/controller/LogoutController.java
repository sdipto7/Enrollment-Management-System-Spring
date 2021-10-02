package net.therap.enrollmentmanagement.controller;

import net.therap.enrollmentmanagement.domain.Credential;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author rumi.dipto
 * @since 9/11/21
 */
@Controller
public class LogoutController {

    @RequestMapping("logout")
    public String logout(HttpSession session, ModelMap model) {
        session.invalidate();
        model.addAttribute("credential", new Credential());

        return "login";
    }
}
