package com.vvcs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
     
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
    	   HttpSession session = request.getSession();
   		if(session!=null){
   		System.out.println((String) session.getAttribute("username"));
   		session.removeAttribute("username");
   		System.out.println((String) session.getAttribute("role"));
   		session.removeAttribute("role");
       }
		return;
	}
	public void destroy() {

	}
                                 
}
