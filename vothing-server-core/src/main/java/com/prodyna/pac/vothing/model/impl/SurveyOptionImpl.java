package com.prodyna.pac.vothing.model.impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prodyna.pac.vothing.model.Survey;
import com.prodyna.pac.vothing.model.SurveyOption;
import com.prodyna.pac.vothing.model.SurveyOptionRating;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@XmlRootElement
@Table(name = "vothing_surveyoption")
public class SurveyOptionImpl extends BaseModelImpl<SurveyOption> implements SurveyOption {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 1219546602331216625L;

    @JsonBackReference
    @JoinColumn(name = "surveyId", referencedColumnName = "id")
    @ManyToOne(targetEntity = SurveyImpl.class)
    private Survey survey;

    @JsonManagedReference
    @OneToMany(mappedBy = "surveyOption", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity = SurveyOptionRatingImpl.class)
    private Collection<SurveyOptionRating> surveyOptionRatings = new ArrayList<SurveyOptionRating>();

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

    /**
     * Default constructor.
     */
    public SurveyOptionImpl() {
    }

}
