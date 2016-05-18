package com.prodyna.pac.vothing.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
//@XmlRootElement
@Table(name = "vothing_surveyoption")
public class SurveyOption extends BaseModelImpl<SurveyOption> implements BaseModel<SurveyOption> {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 1219546602331216625L;

    //    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "surveyId", referencedColumnName = "id")
    @ManyToOne(cascade = {})
    private Survey survey;

    //    @JsonManagedReference
    @OneToMany(mappedBy = "surveyOption", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Collection<SurveyOptionRating> surveyOptionRatings = new ArrayList<SurveyOptionRating>();

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
