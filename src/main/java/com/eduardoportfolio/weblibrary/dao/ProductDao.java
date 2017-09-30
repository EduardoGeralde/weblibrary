package com.eduardoportfolio.weblibrary.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.eduardoportfolio.weblibrary.models.BookType;
import com.eduardoportfolio.weblibrary.models.Product;

@Repository
public class ProductDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product product){
		manager.persist(product);
	}
	
	public void delete(Product product){
		manager.remove(product);
	}
	
	public List<Product> list(){
		return manager.createQuery("select distinct(p) from Product p join fetch p.prices",
																Product.class).getResultList();
	}
	
	public Product find(Integer id) {
		TypedQuery<Product> query = manager.createQuery(
									"select distinct(p) from Product p join fetch p.prices where p.id = :id",
																		Product.class).setParameter("id", id);
		return query.getSingleResult();
	}
	
	public Product findBy(Integer id, BookType bookType) {
		String jpql = "select p from Product p join fetch p.prices price where p.id = :id and price.bookType = :bookType";
		TypedQuery<Product> query = manager.createQuery(jpql, Product.class);
		query.setParameter("id", id);
		query.setParameter("bookType", bookType);
		return query.getSingleResult();
	}
}
