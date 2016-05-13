package com.prodyna.pac.vothing.service;

import com.prodyna.pac.vothing.constants.PermissionEnum;
import com.prodyna.pac.vothing.model.impl.Permission;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;

@VothingMonitoring
public interface PermissionService extends BaseService<Permission> {

	Permission createPermission(String name);

	Permission createPermission(PermissionEnum permission);

}
