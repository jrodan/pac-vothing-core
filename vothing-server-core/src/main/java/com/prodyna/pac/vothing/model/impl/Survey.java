package com.prodyna.pac.vothing.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class Survey extends BaseModelImpl<Survey> implements BaseModel<Survey> {

    private static final long serialVersionUID = 7912134879739982095L;

    @JsonManagedReference
    @OneToMany(mappedBy = "survey", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity = SurveyOption.class)
    private Collection<SurveyOption> surveyOptions = new ArrayList<SurveyOption>();

    @JsonManagedReference
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(targetEntity = User.class)
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
