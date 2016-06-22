package com.prodyna.pac.vothing.api.model.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prodyna.pac.vothing.api.model.Role;
import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.model.SurveyOptionRating;

import java.util.Collection;
import java.util.Date;

/**
 * Created by jrodan on 22/06/16.
 */
public interface UserInterface {

	String getForeName();

	void setForeName(String foreName);

	String getEmail();

	void setEmail(String email);

	String getPassword();

	void setPassword(String password);

	Date getLastLogin();

	void setLastLogin(Date lastLogin);

	Collection<Survey> getSurveys();

	@JsonIgnore
	void setSurveys(Collection<Survey> surveys);

	Collection<Role> getRoles();

	@JsonIgnore
	void setRoles(Collection<Role> roles);

	Collection<SurveyOptionRating> getSurveyOptionRatings();

	@JsonIgnore
	void setSurveyOptionRatings(Collection<SurveyOptionRating> votes);

	@JsonIgnore
	String toFrontendUserJSONObjectString();
}
