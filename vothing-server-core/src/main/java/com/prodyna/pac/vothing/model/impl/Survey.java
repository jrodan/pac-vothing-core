package com.prodyna.pac.vothing.model.impl;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "vothing_survey")
public class Survey extends BaseModelImpl<Survey> implements BaseModel<Survey> {

	private static final long serialVersionUID = 7912134879739982095L;
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne(cascade = {CascadeType.MERGE})
	User user;

	@OneToMany(mappedBy = "survey", cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST})
	private Collection<SurveyOption> surveyOptions = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public Survey() {
	}

	public Collection<SurveyOption> getSurveyOptions() {
		return surveyOptions;
	}

	public void setSurveyOptions(Collection<SurveyOption> surveyOptions) {
		this.surveyOptions = surveyOptions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
