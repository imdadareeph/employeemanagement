package com.app.employeemanagement.service;

import java.util.List;

import com.app.employeemanagement.model.Entity;

/**
 * 
 * Entity Service Interface
 * 
 * @author imdadareeph
 * @version 1.0.0
 *
 */

public interface IEntityService {
	
	public void addEntity(Entity entity);
	
	public void updateEntity(Entity entity);

	public void deleteEntity(Entity entity);
	
	public Entity getEntityById(int id);
	
	public List<Entity> getEntitys();
}
