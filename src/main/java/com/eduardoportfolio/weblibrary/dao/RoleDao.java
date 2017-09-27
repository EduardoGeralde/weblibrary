package com.eduardoportfolio.weblibrary.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.eduardoportfolio.weblibrary.models.Role;

@Repository
public class RoleDao {
	
	@PersistenceContext
	EntityManager manager;
	
	public void save(Role role){
		manager.persist(role);
	}
	
	public void delete(Role role){
		manager.remove(role);
	}
	
	public List<Role> list(){
		return manager.createQuery("select p from Role p", Role.class).getResultList();
	}

}
