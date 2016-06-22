package com.prodyna.pac.vothing.remote.service;

import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.remote.model.SurveyRemote;

import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by jrodan on 18/05/16.
 */
public interface SurveyRemoteService {

	SurveyRemote createSurvey(Survey survey);

	List<SurveyRemote> getSurveys(@PathParam("order") String order);

	List<SurveyRemote> getSurveys();

	SurveyRemote updateSurvey(Survey survey);

	SurveyRemote getSurvey(@PathParam("surveyId") long surveyId);

	void deleteVote(@PathParam("surveyId") long surveyId);
}
