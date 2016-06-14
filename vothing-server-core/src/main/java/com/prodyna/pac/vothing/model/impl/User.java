package com.prodyna.pac.vothing.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@XmlRootElement
@Table(name = "vothing_user")
public class User extends BaseModelImpl<User> implements BaseModel<User> {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 3424622331216625L;

	@Column
	@NotNull
	private String foreName;

	@Column
	@NotNull
	private String email;

	@JsonIgnore
	@Column
	@NotNull
	private String password;

	@Column
	private Date lastLogin;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = {})
	private Collection<Survey> surveys = new ArrayList<>();

	@JsonIgnore
	@ManyToMany(cascade = {})
	@JoinTable(name = "vothing_userrole", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
	private Collection<Role> roles = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = {})
	private Collection<SurveyOptionRating> votes = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public User() {
	}

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

	@JsonIgnore
	public void setSurveys(Collection<Survey> surveys) {
		this.surveys = surveys;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	@JsonIgnore
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<SurveyOptionRating> getSurveyOptionRatings() {
		return votes;
	}

	@JsonIgnore
	public void setSurveyOptionRatings(Collection<SurveyOptionRating> votes) {
		this.votes = votes;
	}

	@JsonIgnore
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
