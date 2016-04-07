package com.prodyna.pac.vothing.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.prodyna.pac.vothing.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_user")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_USERS, query = "SELECT a FROM User a") })
public class User implements Serializable {
	
	/** Generated serial version UID. */
	private static final long serialVersionUID = 3424622331216625L;
	
	@Id
	@GeneratedValue
	private long userId;
	
	@Column
	private String foreName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private Date lastLogin;
	
	@OneToMany(mappedBy="user")
	private Collection<Survey> surveys;
	
	@OneToMany(mappedBy="user")
	private Collection<Role> roles;
	
	@OneToMany(mappedBy="user")
	private Collection<Vote> votes;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getForeName() {
		return foreName;
	}

	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		final User other = (User) obj;
		if (this.userId == 0) {
			if (other.userId != 0) {
				return false;
			}
		} else if (this.userId == other.userId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public String toFrontendUserJSONObjectString() {
		JsonObjectBuilder userAttributes = Json.createObjectBuilder();
		userAttributes.add("email", this.getEmail());
		userAttributes.add("forename", this.getForeName());
		userAttributes.add("lastname", this.getLastName());
		userAttributes.add("userid", this.getUserId());
		JsonObjectBuilder user = Json.createObjectBuilder();
		user.add("user", userAttributes);
		return user.build().toString();
	}
}
