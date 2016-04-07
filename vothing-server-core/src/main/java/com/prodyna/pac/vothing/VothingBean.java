package com.prodyna.pac.vothing;

import java.io.Serializable;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.annotation.ejb.cache.simple.CacheConfig;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.persistence.Role;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.service.PermissionService;
import com.prodyna.pac.vothing.service.SecurityService;
import com.prodyna.pac.vothing.service.UserService;

//@SessionScoped
@CacheConfig(maxSize = 100000, idleTimeoutSeconds = 60, removalTimeoutSeconds = 60)
@VothingMonitoring
// TODO check if timeout is working
public class VothingBean implements Vothing, Serializable {

	private static final long serialVersionUID = 168371436432931798L;

	// @Context
	// private HttpServletRequest request;
	//
	// @Context
	// private HttpServletResponse response;

	@Inject
	private EntityManager entityManager;

	@Inject
	private UserService userService;

	@Inject
	private SecurityService securityService;

	@Inject
	private PermissionService permissionService;

	private User user;

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;

	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public UserService getUserService() {
		return userService;
	}

	@Override
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public SecurityService getSecurityService() {
		return securityService;
	}

	@Override
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	@Override
	public PermissionService getPermissionService() {
		return permissionService;
	}
	
	@Override
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

}
