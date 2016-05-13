package com.prodyna.pac.vothing.model;

import java.util.Collection;

/**
 * Created by jrodan on 13/05/16.
 */
public interface SurveyOption extends BaseModel<SurveyOption>{

    Collection<SurveyOptionRating> getSurveyOptionRatings();

    void setSurveyOptionRatings(
            Collection<SurveyOptionRating> surveyOptionRatings);

    Survey getSurvey();

    void setSurvey(Survey survey);
}
