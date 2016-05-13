package com.prodyna.pac.vothing.model.impl;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@XmlRootElement
@Table(name = "vothing_role")
public class Role extends BaseModelImpl<Role> implements BaseModel<Role> {

	private static final long serialVersionUID = -4959572841738825267L;

	@ManyToMany(targetEntity = Permission.class)
	@JoinTable(name = "vothing_rolepermission", joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "permissionId", referencedColumnName = "id"))
	private Collection<Permission> permissions = new ArrayList<Permission>();

	public Collection<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}

	public Role() {
	}

}
