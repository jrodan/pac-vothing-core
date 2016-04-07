package com.prodyna.pac.vothing.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.VothingConstants;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.service.PermissionService;

@Stateless
@VothingMonitoring
public class PermissionServiceImpl implements PermissionService {

	// TODO check permissions before action

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

	@Override
	public List<Permission> getPermissions() {
		logger.trace("Retrieving all permissions from DB.");

		List<Permission> permissions = this.vothing
				.getEntityManager()
				.createNamedQuery(VothingConstants.SELECT_ALL_PERMISSIONS, Permission.class)
				.getResultList();

		return permissions;
	}

	

}
