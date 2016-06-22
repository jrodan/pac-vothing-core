package com.prodyna.pac.vothing.api.model.api;

import com.prodyna.pac.vothing.api.model.Permission;

import java.util.Collection;

/**
 * Created by jrodan on 21/06/16.
 */
public interface RoleInterface {
	public Collection<Permission> getPermissions();

	public void setPermissions(Collection<Permission> permissions);
}
