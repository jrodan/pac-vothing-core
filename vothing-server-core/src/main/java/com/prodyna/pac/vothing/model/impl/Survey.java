package com.prodyna.pac.vothing.model.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Named
@RequestScoped
//@XmlRootElement
@Table(name = "vothing_survey")
public class Survey extends BaseModelImpl<Survey> implements BaseModel<Survey> {

    private static final long serialVersionUID = 7912134879739982095L;

    //    @JsonManagedReference
    @OneToMany(mappedBy = "survey", cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST})
    private Collection<SurveyOption> surveyOptions = new ArrayList<SurveyOption>();

    //    @JsonManagedReference
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.MERGE})
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
