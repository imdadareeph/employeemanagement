package com.app.employeemanagement.daoimpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.app.employeemanagement.dao.IEntityDAO;
import com.app.employeemanagement.model.Entity;

/**
 * 
 * Entity DAO
 * 
 * @author imdadareeph
 * @version 1.0.0
 * 
 */
@Named
public class EntityDAO implements IEntityDAO,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Entity entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(entity);
		trans.commit();
	}

	public void deleteEntity(Entity entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(entity);
		trans.commit();
	}

	public void updateEntity(Entity entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(entity);
		trans.commit();
	}

	public Entity getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		List<?> list = session
				.createQuery("from Entity where id=?").setParameter(0, id)
				.list();
		
		trans.commit();
		return (Entity) list.get(0);
	}

	public List<Entity> getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Entity> list = (List<Entity>) session.createQuery("from Entity").list();
		
		trans.commit();
		return list;
	}
	
	public SessionFactory createSessionFaction(){
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		return factory;
	}

}
