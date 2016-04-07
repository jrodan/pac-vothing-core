package com.prodyna.pac.vothing.persistence;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.prodyna.pac.vothing.constants.VothingConstants;

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
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	@ManyToOne
	Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Default constructor.
	 */
	public Permission() {
	}
	
}
