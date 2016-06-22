package com.prodyna.pac.vothing.api.service;

import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.constants.PermissionEnum;
import com.prodyna.pac.vothing.api.model.Permission;

@VothingMonitoringAnn
public interface PermissionService extends BaseService<Permission> {

	Permission createPermission(String name);

	Permission createPermission(PermissionEnum permission);

}
