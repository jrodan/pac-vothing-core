package com.prodyna.pac.vothing.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vothing_surveyoptionrating")
public class SurveyOptionRating extends BaseModelImpl<SurveyOptionRating> implements BaseModel<SurveyOptionRating> {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 22192424331216625L;

	@JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne(cascade = {})
	User user;

	@JsonIgnore
	@JoinColumn(name = "surveyOptionId", referencedColumnName = "id")
	@ManyToOne(cascade = {})
	SurveyOption surveyOption;

	/**
	 * Default constructor.
	 */
	public SurveyOptionRating() {
	}

	public SurveyOption getSurveyOption() {
		return surveyOption;
	}

	public void setSurveyOption(SurveyOption surveyOption) {
		this.surveyOption = surveyOption;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
