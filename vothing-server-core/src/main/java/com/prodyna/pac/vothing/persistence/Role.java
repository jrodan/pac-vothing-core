package com.prodyna.pac.vothing.persistence;

import java.util.Collection;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.prodyna.pac.vothing.constants.VothingConstants;

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
	private Collection<Permission> permissions;

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
