package com.prodyna.pac.vothing.service.impl;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.PermissionEnum;
import com.prodyna.pac.vothing.model.impl.Permission;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.service.PermissionService;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@VothingMonitoring
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

	@Override
	public Permission createPermission(String name) {

		Permission permission = new Permission();
		permission.setName(name);
		return super.addElement(permission);
	}

	@Override
	public Permission createPermission(PermissionEnum permission) {
		return this.createPermission(permission.getName());
	}


}
