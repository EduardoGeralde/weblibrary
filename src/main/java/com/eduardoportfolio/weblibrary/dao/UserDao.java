package com.eduardoportfolio.weblibrary.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.eduardoportfolio.weblibrary.models.User;

@Repository
public class UserDao implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		//We don't need to retrieve the password, because we don't want to deal with the hash before make the
		//query, we just search for the logging, and the Spring will verify is the saved password confers with
		//the password, passed in the form, 
		String jpql = "select u from User u where u.login = :login";
		
		List<User> users = manager.createQuery(jpql, User.class)
														.setParameter("login", userName).getResultList();
		if(users.isEmpty()){
			throw new UsernameNotFoundException ("The user "+ userName + "is not found !");
		}
		
		return users.get(0);
	}
	
	public void save(User user){
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		manager.persist(user);
	}
	
	public void delete(User user){
		manager.remove(user);
	}
}
