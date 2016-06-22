package com.prodyna.pac.vothing.core.service;

import com.prodyna.pac.vothing.api.Vothing;
import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.constants.PermissionEnum;
import com.prodyna.pac.vothing.api.model.Permission;
import com.prodyna.pac.vothing.api.service.PermissionService;
import com.prodyna.pac.vothing.core.model.PermissionImpl;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@VothingMonitoringAnn
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

	@Override
	public Permission createPermission(String name) {

		Permission permission = new PermissionImpl();
		permission.setName(name);
		return super.addElement(permission);
	}

	@Override
	public Permission createPermission(PermissionEnum permission) {
		return this.createPermission(permission.getName());
	}


}
