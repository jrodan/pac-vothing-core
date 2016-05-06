package com.prodyna.pac.vothing.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prodyna.pac.vothing.constants.VothingConstants;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@XmlRootElement
@Table(name = "vothing_role")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_ROLES, query = "SELECT a FROM Role a") })
public class Role extends BaseModelImpl<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4959572841738825267L;

	@ManyToMany
	@JoinTable(name = "vothing_rolepermission", joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "permissionId", referencedColumnName = "id"))
	private Collection<Permission> permissions = new ArrayList<Permission>();

	//@JoinColumn(name = "userId")
//	@JoinColumn(name = "userId", referencedColumnName = "id")
//	@ManyToOne
//	User user;
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Collection<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * Default constructor.
	 */
	public Role() {
	}

}
