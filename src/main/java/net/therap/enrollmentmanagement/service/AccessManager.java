package net.therap.enrollmentmanagement.service;

import net.therap.enrollmentmanagement.controller.GlobalExceptionHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 10/3/21
 */
@Service
public class AccessManager {

    public static void checkLogin(HttpSession session) throws GlobalExceptionHandler {
        if (Objects.isNull(session.getAttribute("currentUser"))) {
            throw new GlobalExceptionHandler();
        }
    }
}
