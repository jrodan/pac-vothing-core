package com.prodyna.pac.vothing;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.constants.RoleConstants;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.persistence.Role;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.security.PermissionEnum;
import com.prodyna.pac.vothing.service.PermissionService;
import com.prodyna.pac.vothing.service.RoleService;
import com.prodyna.pac.vothing.service.UserService;

@WebListener
public class VothingServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do nothing in this case
	}
	
	@Inject
	private UserService userService;
	
	@Inject
	private Logger logger;
	
	@Inject
	private PermissionService permissionService;
	
	@Inject
	private RoleService roleService;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		initDatabase();
	}
	
	public void initDatabase() {
		// check if default data exist otherwise create
		
		// TODO init permissions
		Collection<Permission> permissions = new ArrayList<Permission>();
		permissionService.createPermission(PermissionEnum.NONE);
		permissionService.createPermission(PermissionEnum.SURVEY_DELETE);
		permissions.add(permissionService.createPermission(PermissionEnum.SURVEY_ADD));
		permissions.add(permissionService.createPermission(PermissionEnum.SURVEY_UPDATE));
		permissions.add(permissionService.createPermission(PermissionEnum.SURVEY_LIST));
		
		// TODO init roles
		Role adminRole = roleService.createRole(RoleConstants.ROLE_ADMIN, null);
		Role userRole = roleService.createRole(RoleConstants.ROLE_USER, permissions);
		
		Collection<Role> roles = new ArrayList<Role>();
		
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
			user.setName("user");
			user.setPassword("123");
			userService.createUser(user);
			
			user = new User();
			user.setEmail("user@vothing.com");
			user.setForeName("user");
			user.setName("user");
			user.setPassword("123");
			roles.add(userRole);
			user.setRoles(roles);
			userService.createUser(user);
			roles.clear();
			
			user = new User();
			user.setEmail("admin@vothing.com");
			user.setForeName("admin");
			user.setName("admin");
			user.setPassword("123");
			roles.add(adminRole);
			user.setRoles(roles);
			userService.createUser(user);
			
		}
		
		
	}

}
