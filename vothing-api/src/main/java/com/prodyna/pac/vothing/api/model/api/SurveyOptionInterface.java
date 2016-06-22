package com.prodyna.pac.vothing.api.model.api;

import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.model.SurveyOptionRating;

import java.util.Collection;

/**
 * Created by jrodan on 21/06/16.
 */
public interface SurveyOptionInterface {
	Collection<SurveyOptionRating> getSurveyOptionRatings();

	void setSurveyOptionRatings(
			Collection<SurveyOptionRating> surveyOptionRatings);

	Survey getSurvey();

	void setSurvey(Survey survey);
}
