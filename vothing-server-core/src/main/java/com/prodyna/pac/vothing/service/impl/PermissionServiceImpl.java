package com.prodyna.pac.vothing.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.security.PermissionEnum;
import com.prodyna.pac.vothing.service.PermissionService;

@Stateless
@VothingMonitoring
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

	// TODO check permissions before action

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

	@Override
	public Permission createPermission(String name) {
		
		Permission permission = new Permission();
		permission.setName(name);
		this.vothing.getEntityManager().persist(permission);

		return permission;
	}

	@Override
	public Permission createPermission(PermissionEnum permission) {
		return this.createPermission(permission.getName());
	}



}
