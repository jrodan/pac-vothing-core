package com.prodyna.pac.vothing.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
//@XmlRootElement
@Table(name = "vothing_surveyoptionrating")
public class SurveyOptionRating extends BaseModelImpl<SurveyOptionRating> implements BaseModel<SurveyOptionRating> {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 22192424331216625L;

    //    @JsonBackReference
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(cascade = {})
    User user;

    //    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "surveyOptionId", referencedColumnName = "id")
    @ManyToOne(cascade = {})
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
