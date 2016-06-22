package com.prodyna.pac.vothing.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prodyna.pac.vothing.api.model.*;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity(name = "User")
@XmlRootElement
@Table(name = "vothing_user")
public class UserImpl extends User {

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
	@OneToMany(mappedBy = "user", cascade = {}, targetEntity = SurveyImpl.class)
	private Collection<Survey> surveys = new ArrayList<>();

	@JsonIgnore
	@ManyToMany(cascade = {}, targetEntity = RoleImpl.class)
	@JoinTable(name = "vothing_userrole", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
	private Collection<Role> roles = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = {}, targetEntity = SurveyOptionRatingImpl.class)
	private Collection<SurveyOptionRating> votes = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public UserImpl() {
	}

	@Override
	public String getForeName() {
		return foreName;
	}

	@Override
	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Date getLastLogin() {
		return lastLogin;
	}

	@Override
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public Collection<Survey> getSurveys() {
		return surveys;
	}

	@Override
	@JsonIgnore
	public void setSurveys(Collection<Survey> surveys) {
		this.surveys = surveys;
	}

	@Override
	public Collection<Role> getRoles() {
		return roles;
	}

	@Override
	@JsonIgnore
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<SurveyOptionRating> getSurveyOptionRatings() {
		return votes;
	}

	@Override
	@JsonIgnore
	public void setSurveyOptionRatings(Collection<SurveyOptionRating> votes) {
		this.votes = votes;
	}

	@Override
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
