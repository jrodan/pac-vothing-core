package com.prodyna.pac.vothing.model.remote;

import com.prodyna.pac.vothing.model.impl.Survey;
import com.prodyna.pac.vothing.model.impl.SurveyOption;
import com.prodyna.pac.vothing.model.impl.SurveyOptionRating;

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

    public static SurveyRemote toSurveyRemote(long userId, Survey survey) {

        SurveyRemote surveyRemote = new SurveyRemote();

        surveyRemote.setId(survey.getId());
        surveyRemote.setSurveyOptions(survey.getSurveyOptions());
        surveyRemote.setName(survey.getName());
        surveyRemote.setUser(survey.getUser());
        surveyRemote.setCreateDate(survey.getCreateDate());
        surveyRemote.setModifiedDate(survey.getModifiedDate());

        // count votes and set if user has already voted
        int votes = 0;
        boolean hasUserVoted = false;
        for(SurveyOption surveyOptionLocal : survey.getSurveyOptions()) {
            votes += surveyOptionLocal.getSurveyOptionRatings().size();
            for(SurveyOptionRating surveyOptionRating : surveyOptionLocal.getSurveyOptionRatings()) {
                if(surveyOptionRating.getUser().getId() == userId) {
                    hasUserVoted = true;
                    break;
                }
            }
        }
        surveyRemote.setVotes(votes);
        surveyRemote.setUserVoted(hasUserVoted);

        return surveyRemote;
    }


}
