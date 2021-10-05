package net.therap.enrollmentmanagement.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author rumi.dipto
 * @since 10/4/21
 */
@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static String loginException(ModelMap model) {
        model.addAttribute("message", "Invalid Session!");

        return "error";
    }
}
