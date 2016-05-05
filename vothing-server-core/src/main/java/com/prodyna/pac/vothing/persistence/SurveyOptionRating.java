package com.prodyna.pac.vothing.persistence;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.prodyna.pac.vothing.constants.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_surveyoptionrating")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_SURVEYOPTIONRATING, query = "SELECT a FROM SurveyOptionRating a") })
public class SurveyOptionRating extends BaseModelImpl<SurveyOptionRating> {
	
	/** Generated serial version UID. */
	private static final long serialVersionUID = 22192424331216625L;
	
//	@JoinColumn(name = "userId", referencedColumnName = "id")
//	@ManyToOne
    User user;

	
//	@JoinColumn(name = "surveyOptionId", referencedColumnName = "id")
//	@ManyToOne
    SurveyOption surveyOption;
	
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

	/**
	 * Default constructor.
	 */
	public SurveyOptionRating() {
	}
	
}
