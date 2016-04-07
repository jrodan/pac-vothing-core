package com.prodyna.pac.vothing.service.impl;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.persistence.Role;
import com.prodyna.pac.vothing.service.RoleService;

@Stateless
@VothingMonitoring
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	// TODO check permissions before action
	// TODO generate generic Service Base impl and use here just the custom overrides

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

	@Override
	public Role createRole(String name,
			Collection<Permission> permissions) {

		Role role = new Role();
		role.setName(name);
		role.setPermissions(permissions);
		this.vothing.getEntityManager().persist(role);

		return role;
	}

}
