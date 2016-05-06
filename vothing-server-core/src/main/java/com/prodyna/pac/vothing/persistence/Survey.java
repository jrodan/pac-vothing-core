package com.prodyna.pac.vothing.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prodyna.pac.vothing.constants.VothingConstants;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@XmlRootElement
@Table(name = "vothing_survey")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_SURVEYS, query = "SELECT a FROM Survey a") })
public class Survey extends BaseModelImpl<Survey> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7912134879739982095L;

    @JsonManagedReference
	@OneToMany(mappedBy = "survey", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<SurveyOption> surveyOptions = new ArrayList<SurveyOption>();

    @JsonBackReference
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne
	User user;

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

	/**
	 * Default constructor.
	 */
	public Survey() {
	}

}
