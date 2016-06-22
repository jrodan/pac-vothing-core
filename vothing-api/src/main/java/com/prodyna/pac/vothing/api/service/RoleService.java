package com.prodyna.pac.vothing.api.service;

import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.model.Permission;
import com.prodyna.pac.vothing.api.model.Role;

import java.util.Collection;

@VothingMonitoringAnn
public interface RoleService extends BaseService<Role> {

	Role createRole(String name, Collection<Permission> permissions);

}
