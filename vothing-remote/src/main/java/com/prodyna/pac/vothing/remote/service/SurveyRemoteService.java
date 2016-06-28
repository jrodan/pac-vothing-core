package com.prodyna.pac.vothing.remote.service;

import com.prodyna.pac.vothing.remote.model.SurveyRemote;

import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by jrodan on 18/05/16.
 */
public interface SurveyRemoteService {

	SurveyRemote createSurvey(SurveyRemote survey);

	List<SurveyRemote> getSurveys(@PathParam("order") String order);

	List<SurveyRemote> getSurveys();

	SurveyRemote updateSurvey(SurveyRemote survey);

	SurveyRemote getSurvey(@PathParam("surveyId") long surveyId);

	void deleteVote(@PathParam("surveyId") long surveyId);
}
