package com.prodyna.pac.vothing.core.service;

import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.model.Permission;
import com.prodyna.pac.vothing.api.model.Role;
import com.prodyna.pac.vothing.api.service.RoleService;
import com.prodyna.pac.vothing.core.model.RoleImpl;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

@Stateless
@VothingMonitoringAnn
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Inject
	private Logger logger;

	@Override
	public Role createRole(String name,
	                       Collection<Permission> permissions) {

		Role role = new RoleImpl();
		role.setName(name);
		role.setPermissions(permissions);
		super.addElement(role);

		return role;
	}

}
