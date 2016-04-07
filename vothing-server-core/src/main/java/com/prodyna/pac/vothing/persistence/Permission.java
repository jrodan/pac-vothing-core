package com.prodyna.pac.vothing.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.prodyna.pac.vothing.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_permission")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_PERMISSIONS, query = "SELECT a FROM Permission a") })
public class Permission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4518806862505629512L;

	@Id
	@GeneratedValue
	private long permissionId;

	@Column
	private String name;

	@JoinColumn(name = "roleId")
	@ManyToOne
	Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Default constructor.
	 */
	public Permission() {
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Permission)) {
			return false;
		}
		final Permission other = (Permission) obj;
		if (this.permissionId == 0) {
			if (other.permissionId != 0) {
				return false;
			}
		} else if (this.permissionId == other.permissionId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
