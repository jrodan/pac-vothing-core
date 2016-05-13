package com.prodyna.pac.vothing.model.impl;

import com.prodyna.pac.vothing.model.Permission;
import com.prodyna.pac.vothing.model.Role;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@XmlRootElement
@Table(name = "vothing_role")
public class RoleImpl extends BaseModelImpl<Role> implements Role {

	private static final long serialVersionUID = -4959572841738825267L;

	@ManyToMany(targetEntity = PermissionImpl.class)
	@JoinTable(name = "vothing_rolepermission", joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "permissionId", referencedColumnName = "id"))
	private Collection<Permission> permissions = new ArrayList<Permission>();

	@Override
	public Collection<Permission> getPermissions() {
		return permissions;
	}

	@Override
	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}

	public RoleImpl() {
	}

}
