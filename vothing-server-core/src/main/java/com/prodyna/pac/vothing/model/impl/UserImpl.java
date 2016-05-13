package com.prodyna.pac.vothing.model.impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prodyna.pac.vothing.model.Role;
import com.prodyna.pac.vothing.model.Survey;
import com.prodyna.pac.vothing.model.SurveyOptionRating;
import com.prodyna.pac.vothing.model.User;

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
public class UserImpl extends BaseModelImpl<User> implements User {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 3424622331216625L;

	@Column
	@NotNull
	private String foreName;

	@Column
	@NotNull
	private String email;

	@JsonIgnoreProperties
	@Column
	@NotNull
	private String password;

	@Column
	private Date lastLogin;

	@JsonBackReference
	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST}, targetEntity = SurveyImpl.class)
	private Collection<Survey> surveys= new ArrayList<Survey>();

	@ManyToMany(targetEntity = RoleImpl.class)
	@JoinTable(name = "vothing_userrole", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
	private Collection<Role> roles = new ArrayList<Role>();

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, targetEntity = SurveyOptionRatingImpl.class)
	private Collection<SurveyOptionRating> votes = new ArrayList<SurveyOptionRating>();

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
	public void setSurveys(Collection<Survey> surveys) {
		this.surveys = surveys;
	}

	@Override
	public Collection<Role> getRoles() {
		return roles;
	}

	@Override
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<SurveyOptionRating> getSurveyOptionRatings() {
		return votes;
	}

	@Override
	public void setSurveyOptionRatings(Collection<SurveyOptionRating> votes) {
		this.votes = votes;
	}

	/**
	 * Default constructor.
	 */
	public UserImpl() {
	}

	@Override
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
