package com.prodyna.pac.vothing.persistence;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.prodyna.pac.vothing.constants.VothingConstants;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "vothing_permission")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_PERMISSIONS, query = "SELECT a FROM Permission a") })
public class Permission extends BaseModelImpl<Permission> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4518806862505629512L;

	//@JoinColumn(name = "roleId")
//	@JoinColumn(name = "roleId", referencedColumnName = "id")
//    @ManyToMany
//    @JoinTable(name = "vothing_rolepermission", joinColumns = @JoinColumn(name = "permissionId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "permissionId", referencedColumnName = "id"))
//	Role role;
//
//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}

	/**
	 * Default constructor.
	 */
	public Permission() {
	}
	
}
