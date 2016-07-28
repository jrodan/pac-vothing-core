package com.prodyna.pac.vothing.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.model.SurveyOption;
import com.prodyna.pac.vothing.api.model.SurveyOptionRating;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "SurveyOption")
@Table(name = "vothing_surveyoption")
public class SurveyOptionImpl extends SurveyOption {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 1219546602331216625L;

	@JsonIgnore
	@JoinColumn(name = "surveyId", referencedColumnName = "id")
	@ManyToOne(cascade = {}, targetEntity = SurveyImpl.class)
	private Survey survey;

	@OneToMany(mappedBy = "surveyOption", cascade = {CascadeType.REMOVE, CascadeType.MERGE},
			targetEntity = SurveyOptionRatingImpl.class, fetch = FetchType.EAGER)
	private Collection<SurveyOptionRating> surveyOptionRatings = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public SurveyOptionImpl() {
	}

	@Override
	public Collection<SurveyOptionRating> getSurveyOptionRatings() {
		return surveyOptionRatings;
	}

	@Override
	public void setSurveyOptionRatings(
			Collection<SurveyOptionRating> surveyOptionRatings) {
		this.surveyOptionRatings = surveyOptionRatings;
	}

	@Override
	public Survey getSurvey() {
		return survey;
	}

	@Override
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

}
