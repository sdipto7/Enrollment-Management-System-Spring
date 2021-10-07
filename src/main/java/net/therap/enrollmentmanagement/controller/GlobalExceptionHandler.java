package net.therap.enrollmentmanagement.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author rumi.dipto
 * @since 10/4/21
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public static String loginException(ModelMap model) {
        model.addAttribute("message", "Please try again later");

        return "error";
    }
}
