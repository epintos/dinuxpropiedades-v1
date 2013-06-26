package ar.edu.itba.it.paw.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.web.SessionUserManager;
import ar.edu.itba.it.paw.web.UserManager;

public class PrivatePagesFilter implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
	
	HttpServletRequest req = ((HttpServletRequest) request);
	HttpServletResponse resp = ((HttpServletResponse) response);
	UserManager manager = new SessionUserManager(req, resp);
	
	if(!manager.existsUser()) {
	    resp.sendRedirect("../public/welcome");
	} else {
	    chain.doFilter(request, response);
	}
        
    }
    
    public void destroy() {
        
    }
    
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
