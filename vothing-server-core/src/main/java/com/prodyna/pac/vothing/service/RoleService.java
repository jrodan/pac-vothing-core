package com.prodyna.pac.vothing.service;

import com.prodyna.pac.vothing.model.impl.Permission;
import com.prodyna.pac.vothing.model.impl.Role;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;

import java.util.Collection;

@VothingMonitoring
public interface RoleService extends BaseService<Role>{
	
	Role createRole(String name, Collection<Permission> permissions);

}
