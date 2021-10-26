package net.therap.enrollmentmanagement.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 10/6/21
 */
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = ((HttpServletRequest) request).getSession();
        if (Objects.isNull(session.getAttribute("currentUser"))) {
            httpServletResponse.sendRedirect("/");
        } else {
            chain.doFilter(request, response);
        }
    }
}
