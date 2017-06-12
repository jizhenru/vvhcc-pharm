package com.vvcs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class TokenFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}     
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		StringBuffer requestURL = request.getRequestURL();
		String URL = requestURL.toString();
		URL = StringUtils.substringAfter(URL, "http://");
		String username = (String) session.getAttribute("username");
		String name = (String) session.getAttribute("name");
		String role = (String) session.getAttribute("role");;
		System.out.println(username);
		System.out.println(name);
		System.out.println(role);
		if ((URL.indexOf("index.jsp") != -1) || (URL.indexOf("privacy.html") != -1) || (URL.indexOf("usage.html") != -1) ||(URL.endsWith(".ico")) ||(URL.endsWith(".js")) || (URL.endsWith(".css"))||(URL.indexOf(".woff") != -1)||(URL.indexOf(".ttf") != -1)||(URL.indexOf(".png") != -1)) {
		filterChain.doFilter(request, response);
		}else{ 
			if (role == null) {
				String token = request.getParameter("token");
				if (null != token && !"".equals(token)) {
					CloseableHttpClient httpClient = HttpClients.createDefault();
					HttpGet getMethod = new HttpGet("http://www.vvhcc.com/token/equalToken" + "?token=" + token);
					try {
						HttpResponse execute = httpClient.execute(getMethod);
						String jsondata = EntityUtils.toString(execute.getEntity(), "utf-8");
						JSONObject json = JSONObject.fromObject(jsondata);
						username = (String) json.get("username");
						name = (String) json.get("name");
						role = (String) json.get("role");
					} catch (Exception e) {
						e.printStackTrace();
					} 
					if (null != username && !"".equals(username)) {
						session.setAttribute("username", username);
						session.setAttribute("name", name);
						session.setAttribute("role", role);
						Cookie[] cookies = request.getCookies();
						if (cookies != null) {
							for (Cookie cookie : cookies) {
								String essionid = cookie.getName();
								if (essionid.equalsIgnoreCase("JSESSIONID")) {
									String JSESSIONID = cookie.getValue();
	                            	System.out.println(JSESSIONID);
									CloseableHttpClient httpClient1 = HttpClients.createDefault();
									HttpGet getMethod1 = new HttpGet("http://www.vvhcc.com/token/sendSessionId" + "?JSESSIONID="
				            	 	+ JSESSIONID + "&url=" + URL +"&username="+username);
								 	HttpResponse execute = httpClient1.execute(getMethod1);
							 		EntityUtils.toString(execute.getEntity(), "utf-8");
									System.out.println("sessionId获取完毕");
                              }   
				     	  }
						}   
						filterChain.doFilter(request, response);
					} else {
						response.sendRedirect("http://www.vvhcc.com/login");
					}
				} else {
					response.sendRedirect("http://www.vvhcc.com/login" + "?url=" + URL);
				}
			} else if (role.indexOf("admin") != -1) {
				if (URL.indexOf("Adminis") != -1) {
					filterChain.doFilter(request, response);
				} else {
					response.sendRedirect("http://www.vvhcc.com/index.jsp");
				}

			}else if (role.indexOf("yaojishi") != -1){
				if (URL.indexOf("druggist") != -1) {
					filterChain.doFilter(request, response);
				} else {
					response.sendRedirect("http://www.vvhcc.com/index.jsp");
				}
			}else if (role.indexOf("yonghu") != -1){
				if (URL.indexOf("user") != -1) {
					filterChain.doFilter(request, response);
				} else {
					response.sendRedirect("http://www.vvhcc.com/index.jsp");
				}
			} else {
				response.sendRedirect("http://www.vvhcc.com/index.jsp");
			}
		}
	}
	public void destroy() {

	}

}
