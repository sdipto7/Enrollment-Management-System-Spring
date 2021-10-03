package net.therap.enrollmentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author rumi.dipto
 * @since 10/4/21
 */
@Controller
public class GlobalExceptionHandler extends Throwable {

    @ExceptionHandler
    public static String loginException(ModelMap model) {
        model.addAttribute("message", "Invalid Session!");

        return "error";
    }
}
