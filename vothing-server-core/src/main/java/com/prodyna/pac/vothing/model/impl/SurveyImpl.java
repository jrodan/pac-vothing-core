package com.prodyna.pac.vothing.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prodyna.pac.vothing.model.Survey;
import com.prodyna.pac.vothing.model.SurveyOption;
import com.prodyna.pac.vothing.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Named
@RequestScoped
@XmlRootElement
@Table(name = "vothing_survey")
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyImpl extends BaseModelImpl<Survey> implements Survey {

    private static final long serialVersionUID = 7912134879739982095L;

    @JsonManagedReference
    @OneToMany(mappedBy = "survey", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity = SurveyOptionImpl.class)
    private Collection<SurveyOption> surveyOptions = new ArrayList<SurveyOption>();

    // TODO get a permission map for each entity
    @JsonManagedReference
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(targetEntity = UserImpl.class)
    User user;

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

    /**
     * Default constructor.
     */
    public SurveyImpl() {
    }

}
