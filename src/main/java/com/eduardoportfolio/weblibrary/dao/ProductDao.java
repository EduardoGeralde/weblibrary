package com.eduardoportfolio.weblibrary.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.eduardoportfolio.weblibrary.models.Product;

@Repository
public class ProductDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product product){
		manager.persist(product);
	}
	
	

}
