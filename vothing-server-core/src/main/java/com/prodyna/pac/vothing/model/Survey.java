package com.prodyna.pac.vothing.model;

import java.util.Collection;

/**
 * Created by jrodan on 13/05/16.
 */
public interface Survey extends BaseModel<Survey>{

    Collection<SurveyOption> getSurveyOptions();

    void setSurveyOptions(Collection<SurveyOption> surveyOptions);

    User getUser();

    void setUser(User user);
}
