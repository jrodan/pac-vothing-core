package com.prodyna.pac.vothing.api.model.api;

import com.prodyna.pac.vothing.api.model.SurveyOption;
import com.prodyna.pac.vothing.api.model.User;

import java.util.Collection;

/**
 * Created by jrodan on 21/06/16.
 */
public interface SurveyInterface{
	public Collection<SurveyOption> getSurveyOptions();

	public void setSurveyOptions(Collection<SurveyOption> surveyOptions);

	public User getUser();

	public void setUser(User user);
}
