package com.prodyna.pac.vothing.service;

import java.util.List;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;

@VothingMonitoring
public interface PermissionService {
	
	List<Permission> getPermissions();

}
