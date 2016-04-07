package com.prodyna.pac.vothing.service;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.security.PermissionEnum;

@VothingMonitoring
public interface PermissionService extends BaseService<Permission> {
	
	Permission createPermission(String name);
	
	Permission createPermission(PermissionEnum permission);

}
