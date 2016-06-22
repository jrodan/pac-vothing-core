package com.prodyna.pac.vothing.api.model.api;

import com.prodyna.pac.vothing.api.model.SurveyOption;
import com.prodyna.pac.vothing.api.model.User;

/**
 * Created by jrodan on 21/06/16.
 */
public interface SurveyOptionRatingInterface {
	SurveyOption getSurveyOption();

	void setSurveyOption(SurveyOption surveyOption);

	User getUser();

	void setUser(User user);
}
