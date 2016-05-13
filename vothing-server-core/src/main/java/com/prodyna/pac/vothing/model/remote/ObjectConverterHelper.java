package com.prodyna.pac.vothing.model.remote;

import com.prodyna.pac.vothing.model.impl.Survey;

/**
 * Created by jrodan on 13/05/16.
 */
public class ObjectConverterHelper {

    public static Survey toSurvey(SurveyRemote surveyRemote) {

        Survey survey = new Survey();

        survey.setId(surveyRemote.getId());
        survey.setSurveyOptions(surveyRemote.getSurveyOptions());
        survey.setName(surveyRemote.getName());
        survey.setUser(surveyRemote.getUser());
        survey.setCreateDate(surveyRemote.getCreateDate());
        survey.setModifiedDate(surveyRemote.getModifiedDate());

        return survey;
    }

    public static SurveyRemote toSurveyRemote(Survey survey) {

        SurveyRemote surveyRemote = new SurveyRemote();

        surveyRemote.setId(survey.getId());
        surveyRemote.setSurveyOptions(survey.getSurveyOptions());
        surveyRemote.setName(survey.getName());
        surveyRemote.setUser(survey.getUser());
        surveyRemote.setCreateDate(survey.getCreateDate());
        surveyRemote.setModifiedDate(survey.getModifiedDate());

        return surveyRemote;
    }


}
