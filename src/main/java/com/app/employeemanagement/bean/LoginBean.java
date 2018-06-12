/**
 * 
 */
package com.app.employeemanagement.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import com.app.employeemanagement.model.Entity;
import com.app.employeemanagement.service.IEntityService;
import com.app.employeemanagement.util.JsfUtil;
import com.app.employeemanagement.util.Util;

/**
 * @author imdadareeph
 *
 */
@Named("loginBean")
@Scope("session")
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String password;
    private String message, uname;
    private boolean loggedIn;
    private List<Entity> entityList;
    @Inject
	private IEntityService entityService;
    
    @PostConstruct
	public  void init(){
		entityList = new ArrayList<Entity>();
		entityList.addAll(getEntityService().getEntitys());
	}
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getUname() {
        return uname;
    }
 
    public void setUname(String uname) {
        this.uname = uname;
    }
    
    public String login(){
    	if((null!=uname || !"".equalsIgnoreCase(uname)) && (null!=password || !"".equalsIgnoreCase(password))){
    		for(Entity e:entityList){
    			if(e.getUsername().equalsIgnoreCase(uname) && e.getPassword().equalsIgnoreCase(JsfUtil.getMd5Digest(password))){
    				loggedIn = true;
    	    		 HttpSession session = Util.getSession();
    	             session.setAttribute("username", uname);
    	             session.setAttribute("loggedin", loggedIn);
    	    		
    	    		message ="Successfully logged-in.";
    	    		JsfUtil.addSuccessMessage(message);
    	    		return "index.jsf";
    			}
    		}
    		
    	}
    	if("admin".equalsIgnoreCase(uname) && "admin".equalsIgnoreCase(password)) {
    		loggedIn = true;
    		 HttpSession session = Util.getSession();
             session.setAttribute("username", uname);
             session.setAttribute("loggedin", loggedIn);
    		
    		message ="Successfully logged-in.";
    		JsfUtil.addSuccessMessage(message);
    		return "index.jsf";
    	} else {
    		message ="Wrong credentials.";
    		JsfUtil.addErrorMessage(message);
    		return "login.jsf";
    	}
    }
    
    public String logouttrue() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        loggedIn = false;
        return "/pages/login.jsf?faces-redirect=true"; 
    }
 
    public String loginProject() {
        boolean result = false;//UserDAO.login(uname, password);
        if (result) {
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", uname);
 
            return "home";
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
 
            // invalidate session, and redirect to other pages
            return "login";
        }
    }
 
    public String logout() {
      HttpSession session = Util.getSession();
      session.invalidate();
      loggedIn = false;
      session.setAttribute("loggedin", loggedIn);
      return "/pages/login.jsf?faces-redirect=true"; 
   }

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public IEntityService getEntityService() {
		return entityService;
	}

	public void setEntityService(IEntityService entityService) {
		this.entityService = entityService;
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}
}
