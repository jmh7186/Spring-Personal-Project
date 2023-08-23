package com.my.PJ.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public LoginFilter() {
		super();
	}

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//세션 확인
		HttpServletRequest hsreq = (HttpServletRequest) request;
		HttpSession session = hsreq.getSession();
		
		if (session.getAttribute("id")==null){
			if(!(hsreq.getRequestURI().contains("/member") ||
				hsreq.getRequestURI().contains("/signup") ||
				hsreq.getRequestURI().contains("/map") ||
				hsreq.getRequestURI().contains("/resource") ||
				hsreq.getRequestURI().equals("/"))) {
				hsreq.setAttribute("mainpage", "/member/login.jsp");
				hsreq.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
				return; 
			}
		}
		
		chain.doFilter(request, response);
	}

}
