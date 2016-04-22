package com.prodyna.pac.vothing.persistence;

import java.util.Collection;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.prodyna.pac.vothing.constants.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_user")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_USERS, query = "SELECT a FROM User a") })
public class User extends BaseModelImpl<User> {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 3424622331216625L;

	@Column
	@NotNull
	private String foreName;

	@Column
	@NotNull
	private String email;

	@Column
	@NotNull
	private String password;

	@Column
	private Date lastLogin;

	@OneToMany(mappedBy = "user")
	private Collection<Survey> surveys;

	@ManyToMany
	@JoinTable(name = "userrole", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
	private Collection<Role> roles;

	@OneToMany(mappedBy = "user")
	private Collection<Vote> votes;

	public String getForeName() {
		return foreName;
	}

	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Collection<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(Collection<Survey> surveys) {
		this.surveys = surveys;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Collection<Vote> votes) {
		this.votes = votes;
	}

	/**
	 * Default constructor.
	 */
	public User() {
	}

	public String toFrontendUserJSONObjectString() {
		JsonObjectBuilder userAttributes = Json.createObjectBuilder();
		userAttributes.add("email", this.getEmail());
		userAttributes.add("forename", this.getForeName());
		userAttributes.add("lastname", this.getName());
		userAttributes.add("userid", this.getId());
		JsonObjectBuilder user = Json.createObjectBuilder();
		user.add("user", userAttributes);
		return user.build().toString();
	}
}
