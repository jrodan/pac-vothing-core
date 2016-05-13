package com.prodyna.pac.vothing;

import com.prodyna.pac.vothing.model.impl.User;
import com.prodyna.pac.vothing.service.PermissionService;
import com.prodyna.pac.vothing.service.SecurityService;
import com.prodyna.pac.vothing.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public interface Vothing {

	public User getUser() throws EntityNotFoundException;

	public void setUser(User user);

	public EntityManager getEntityManager();

	public void setEntityManager(EntityManager entityManager);

	public UserService getUserService();

	public void setUserService(UserService userService);

	void setSecurityService(SecurityService securityService);

	SecurityService getSecurityService();

	PermissionService getPermissionService();

	void setPermissionService(PermissionService permissionService);

}
