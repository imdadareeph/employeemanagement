package com.app.employeemanagement.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;

import com.app.employeemanagement.model.Entity;
import com.app.employeemanagement.service.IEntityService;
import com.app.employeemanagement.util.JsfUtil;

/**
 * 
 * Entity Backed Bean
 * 
 * @author imdadareeph
 * @version 1.0.0
 *
 */

@Named("entityBBean")
@ViewScoped
public class EntityBBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IEntityService entityService;
	
	private int id;
	private String name;
	private String mobile;
	private String address;
	private String nationality;
	private String username;
	private String password;
	private List<Entity> entityList;
	
	
	@PostConstruct
	public  void init(){
		entityList = new ArrayList<Entity>();
		entityList.addAll(getEntityService().getEntitys());
	}
	public void addEntity() {
		Entity entity = new Entity();
		try {
			entity.setId(getId());
			entity.setName(getName());
			entity.setMobile(getMobile());
			entity.setAddress(getAddress());
			entity.setNationality(getNationality());
			if(null==getUsername() || "".equalsIgnoreCase(getUsername())){
				JsfUtil.addErrorMessage("Username cannot be empty");
			}else{
				for(Entity e:entityList){
	    			if(e.getUsername().equalsIgnoreCase(getUsername()) && e.getPassword().equalsIgnoreCase(JsfUtil.getMd5Digest(password))){
	    				JsfUtil.addErrorMessage("Username already exists");
	    				return;
	    				//break;
	    			}
				}
			}
			entity.setUsername(getUsername());
			
			if(null!=getPassword() || !"".equalsIgnoreCase(getPassword())){
				entity.setPassword(JsfUtil.getMd5Digest(getPassword()));
			}else{
				JsfUtil.addErrorMessage("Password cannot be empty");
				return;
			}
			
			if(validateData(entity)){
				getEntityService().addEntity(entity);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Added Successfully!", "Message: "));  
				entityList.add(entity);
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Record not added!", "Message: ")); 
			}
			
			
		} catch (DataAccessException e) {
			entityList.remove(entity);
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Record not added!", "Message: ")); 
		} 	
		
	}
	
	public boolean validateData(Entity en){
		if(null==en.getUsername() || "".equalsIgnoreCase(en.getUsername())) {
			return false;
		}else if(null==en.getPassword() || "".equalsIgnoreCase(en.getPassword())) {
			return false;
		}else{
			return true;
		}
		
	}
	public void deleteAction(Entity value){
		getEntityService().deleteEntity(value);
		entityList.clear();
		entityList.addAll(getEntityService().getEntitys());
		JsfUtil.addSuccessMessage(value.getUsername()+" is successfully deleted");
	}
	

	public void reset() {
		this.setId(0);
		this.setName("");
		this.setMobile("");
		this.setAddress("");
		this.setNationality("");
		this.setUsername("");
		this.setPassword("");
	}

	public List<Entity> getEntityList() {
		
		return entityList;
	}

	public IEntityService getEntityService() {
		return entityService;
	}

	public void setEntityService(IEntityService entityService) {
		this.entityService = entityService;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void onRowEdit(RowEditEvent event){
		System.out.println("<---------start edit-------->");
		Entity updateEntity=(((Entity) event.getObject()));
		System.out.println("<---------updateEntity-------->"+updateEntity.getName());
		getEntityService().updateEntity(updateEntity);
		JsfUtil.addSuccessMessage(updateEntity.getUsername()+" is successfully updated");
	}
	
 }