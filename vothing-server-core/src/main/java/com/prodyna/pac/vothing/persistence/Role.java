package com.prodyna.pac.vothing.persistence;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

	@OneToMany(mappedBy = "role")
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
