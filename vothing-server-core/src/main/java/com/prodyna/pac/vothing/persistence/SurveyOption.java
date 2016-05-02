package com.prodyna.pac.vothing.persistence;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.prodyna.pac.vothing.constants.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_surveyoption")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_SURVEYOPTION, query = "SELECT a FROM SurveyOption a") })
public class SurveyOption extends BaseModelImpl<SurveyOption> {
	
	/** Generated serial version UID. */
	private static final long serialVersionUID = 1219546602331216625L;
	
	@JoinColumn(name = "surveyId", referencedColumnName = "id")
	@ManyToOne Survey survey;
	
	@OneToMany(mappedBy = "surveyOption", cascade = {CascadeType.PERSIST})
	private Collection<SurveyOptionRating> surveyOptionRatings;
	
	public Collection<SurveyOptionRating> getSurveyOptionRatings() {
		return surveyOptionRatings;
	}

	public void setSurveyOptionRatings(
			Collection<SurveyOptionRating> surveyOptionRatings) {
		this.surveyOptionRatings = surveyOptionRatings;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	/**
	 * Default constructor.
	 */
	public SurveyOption() {
	}
	
}
