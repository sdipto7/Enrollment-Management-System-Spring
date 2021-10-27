package net.therap.enrollmentmanagement.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 10/26/21
 */
public class LoggedInFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = ((HttpServletRequest) request).getSession();

        if (Objects.nonNull(session.getAttribute("currentUser"))) {
            httpServletResponse.sendRedirect("/home");
        } else {
            chain.doFilter(request, response);
        }
    }
}
