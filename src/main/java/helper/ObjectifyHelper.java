package helper;

import com.googlecode.objectify.ObjectifyService;
import entity.Employee;

import javax.servlet.*;
import java.io.IOException;


public class ObjectifyHelper implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ObjectifyService.register(Employee.class);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
