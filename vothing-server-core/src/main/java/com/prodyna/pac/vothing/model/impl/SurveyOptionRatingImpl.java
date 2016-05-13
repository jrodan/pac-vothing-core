package com.prodyna.pac.vothing.model.impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prodyna.pac.vothing.model.SurveyOption;
import com.prodyna.pac.vothing.model.SurveyOptionRating;
import com.prodyna.pac.vothing.model.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "vothing_surveyoptionrating")
public class SurveyOptionRatingImpl extends BaseModelImpl<SurveyOptionRating> implements SurveyOptionRating {
	
	/** Generated serial version UID. */
	private static final long serialVersionUID = 22192424331216625L;

    @JsonBackReference
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne(targetEntity = UserImpl.class)
	User user;

    @JsonBackReference
	@JoinColumn(name = "surveyOptionId", referencedColumnName = "id")
	@ManyToOne(targetEntity = SurveyOptionImpl.class)
	SurveyOption surveyOption;
	
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

	/**
	 * Default constructor.
	 */
	public SurveyOptionRatingImpl() {
	}
	
}
