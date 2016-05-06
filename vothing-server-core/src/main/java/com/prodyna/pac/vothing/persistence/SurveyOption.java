package com.prodyna.pac.vothing.persistence;

import com.prodyna.pac.vothing.constants.VothingConstants;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@XmlRootElement
@Table(name = "vothing_surveyoption")
@NamedQueries({@NamedQuery(name = VothingConstants.SELECT_SURVEYOPTION, query = "SELECT a FROM SurveyOption a")})
public class SurveyOption extends BaseModelImpl<SurveyOption> {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 1219546602331216625L;

    @JoinColumn(name = "surveyId", referencedColumnName = "id")
    @ManyToOne
    private Survey survey;

    @OneToMany(mappedBy = "surveyOption", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
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
