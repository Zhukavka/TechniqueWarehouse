package com.dashyl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Darya on 20.04.2015.
 */
public class DefaultFilter implements Filter {
    private RequestDispatcher defaultRequestDispatcher;

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        defaultRequestDispatcher.forward(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.defaultRequestDispatcher =
                filterConfig.getServletContext().getNamedDispatcher("default");
    }
}
