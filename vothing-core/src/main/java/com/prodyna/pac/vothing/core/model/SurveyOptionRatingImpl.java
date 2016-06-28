package com.prodyna.pac.vothing.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prodyna.pac.vothing.api.model.SurveyOption;
import com.prodyna.pac.vothing.api.model.SurveyOptionRating;
import com.prodyna.pac.vothing.api.model.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "SurveyOptionRating")
@Table(name = "vothing_surveyoptionrating")
public class SurveyOptionRatingImpl extends SurveyOptionRating {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 22192424331216625L;

	@JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne(cascade = {}, targetEntity = UserImpl.class)
	User user;

	@JsonIgnore
	@JoinColumn(name = "surveyOptionId", referencedColumnName = "id")
	@ManyToOne(cascade = {}, targetEntity = SurveyOptionImpl.class)
	SurveyOption surveyOption;

	/**
	 * Default constructor.
	 */
	public SurveyOptionRatingImpl() {
	}

	@Override
	public SurveyOption getSurveyOption() {
		return surveyOption;
	}

	@Override
	public void setSurveyOption(SurveyOption surveyOption) {
		this.surveyOption = surveyOption;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

}
