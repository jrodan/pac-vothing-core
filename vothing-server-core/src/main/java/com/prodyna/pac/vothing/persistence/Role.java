package com.prodyna.pac.vothing.persistence;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.prodyna.pac.vothing.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_role")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_ROLES, query = "SELECT a FROM Role a") })
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4959572841738825267L;

	@Id
	@GeneratedValue
	private long roleId;

	@Column
	private String name;

	@OneToMany(mappedBy = "role")
	private Collection<Permission> permissions;

	@JoinColumn(name = "userId")
	@ManyToOne
	User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Role)) {
			return false;
		}
		final Role other = (Role) obj;
		if (this.roleId == 0) {
			if (other.roleId != 0) {
				return false;
			}
		} else if (this.roleId == other.roleId) {
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
