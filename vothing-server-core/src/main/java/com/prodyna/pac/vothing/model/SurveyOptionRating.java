package com.prodyna.pac.vothing.model;

/**
 * Created by jrodan on 13/05/16.
 */
public interface SurveyOptionRating extends BaseModel<SurveyOptionRating> {

    SurveyOption getSurveyOption();

    void setSurveyOption(SurveyOption surveyOption);

    User getUser();

    void setUser(User user);
}
