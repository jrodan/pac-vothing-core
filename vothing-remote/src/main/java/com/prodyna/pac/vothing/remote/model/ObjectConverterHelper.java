package com.prodyna.pac.vothing.remote.model;

import com.prodyna.pac.vothing.api.model.Survey;

/**
 * Created by jrodan on 21/06/16.
 */
public interface ObjectConverterHelper {

	Survey toSurvey(SurveyRemote surveyRemote);

	SurveyRemote toSurveyRemote(long surveyId);
}
