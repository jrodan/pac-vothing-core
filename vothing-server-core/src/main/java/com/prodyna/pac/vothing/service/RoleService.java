package com.prodyna.pac.vothing.service;

import java.util.Collection;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.persistence.Role;

@VothingMonitoring
public interface RoleService extends BaseService<Role>{
	
	Role createRole(String name, Collection<Permission> permissions);

}
