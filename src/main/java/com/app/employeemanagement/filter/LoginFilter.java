/**
 * 
 */
package com.app.employeemanagement.filter;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.employeemanagement.bean.LoginBean;
import com.app.employeemanagement.util.JsfUtil;
/**
 * @author imdadareeph
 *
 */
public class LoginFilter implements Filter {
	
	@Inject
	private LoginBean loginBean;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       // LoginBean loginBean = (LoginBean)((HttpServletRequest)request).getSession().getAttribute("loginBean");
		Boolean isloggedIn = (Boolean)((HttpServletRequest)request).getSession().getAttribute("loggedin");
		LoginBean loginBean = JsfUtil.findBean("loginBean");
		//Boolean sess = Boolean.valueOf(isloggedIn);
     //   if (loginBean == null || !loginBean.isLoggedIn()) {
        if (isloggedIn == null || isloggedIn.equals(Boolean.FALSE)) {
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + "/pages/login.jsf");
        }
         
        chain.doFilter(request, response);
             
    }
 
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }
 
    public void destroy() {
        // Nothing to do here!
    }

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}   
	
	

}
