package com.prodyna.pac.vothing.service.remote;

import com.prodyna.pac.vothing.model.impl.Survey;
import com.prodyna.pac.vothing.model.remote.SurveyRemote;

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
