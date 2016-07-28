package com.prodyna.pac.vothing.core.model;

import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.model.SurveyOption;
import com.prodyna.pac.vothing.api.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "Survey")
@Table(name = "vothing_survey")
public class SurveyImpl extends Survey {

	private static final long serialVersionUID = 7912134879739982095L;
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne(cascade = {CascadeType.MERGE}, targetEntity = UserImpl.class)
	User user;

	@OneToMany(mappedBy = "survey",
			cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST},
			targetEntity = SurveyOptionImpl.class, fetch = FetchType.EAGER)
	private Collection<SurveyOption> surveyOptions = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public SurveyImpl() {
	}

	@Override
	public Collection<SurveyOption> getSurveyOptions() {
		return surveyOptions;
	}

	@Override
	public void setSurveyOptions(Collection<SurveyOption> surveyOptions) {
		this.surveyOptions = surveyOptions;
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
