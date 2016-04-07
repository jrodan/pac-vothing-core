package com.prodyna.pac.vothing;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.service.UserService;

public class VothingServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do nothing in this case
	}
	
	@Inject
	private UserService userService;
	
	@Inject
	private Logger logger;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		initDatabase();
	}
	
	public void initDatabase() {
		// check if default data exist otherwise create
		User user = null;
		try {
			user = userService.getUser("default@vothing.com", "123");
		} catch (EntityNotFoundException e) {
			logger.error("no default users are existing - continue creating these",e);
		}
		
		if(user == null) {
			
			// TODO create default users
			user = new User();
			user.setEmail("default@vothing.com");
			user.setForeName("dummy");
			user.setLastName("user");
			user.setPassword("123");
			userService.createUser(user);
			
			user = new User();
			user.setEmail("user@vothing.com");
			user.setForeName("user");
			user.setLastName("user");
			user.setPassword("123");
			userService.createUser(user);
			
			user = new User();
			user.setEmail("admin@vothing.com");
			user.setForeName("admin");
			user.setLastName("admin");
			user.setPassword("123");
			userService.createUser(user);
			
			// TODO assign default roles to default users
			// TODO create default roles
			// TODO assign permissions to roles
			
		}
		
		// TODO init server private key
		
	}

}
