package com.prodyna.pac.vothing;

import com.prodyna.pac.vothing.model.impl.User;
import com.prodyna.pac.vothing.service.PermissionService;
import com.prodyna.pac.vothing.service.SecurityService;
import com.prodyna.pac.vothing.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public interface Vothing {

	User getUser() throws EntityNotFoundException;

	void setUser(User user);

	EntityManager getEntityManager();

	void setEntityManager(EntityManager entityManager);

	UserService getUserService();

	void setUserService(UserService userService);

	SecurityService getSecurityService();

	void setSecurityService(SecurityService securityService);

	PermissionService getPermissionService();

	void setPermissionService(PermissionService permissionService);

}
