package com.prodyna.pac.vothing.service.impl;

import com.prodyna.pac.vothing.model.impl.Permission;
import com.prodyna.pac.vothing.model.impl.Role;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.service.RoleService;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

@Stateless
@VothingMonitoring
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Inject
	private Logger logger;

	@Override
	public Role createRole(String name,
	                       Collection<Permission> permissions) {

		Role role = new Role();
		role.setName(name);
		role.setPermissions(permissions);
		super.addElement(role);

		return role;
	}

}
