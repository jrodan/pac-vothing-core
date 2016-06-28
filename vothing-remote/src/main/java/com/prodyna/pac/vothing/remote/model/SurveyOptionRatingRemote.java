package com.prodyna.pac.vothing.remote.model;

import com.prodyna.pac.vothing.api.model.User;

/**
 * Created by jrodan on 13/05/16.
 */
public class SurveyOptionRatingRemote extends BaseRemoteEntityImpl {

	private SurveyOptionRemote surveyOption;

	private User user;

	public SurveyOptionRemote getSurveyOptionRemote() {
		return surveyOption;
	}

	public void setSurveyOptionRemote(SurveyOptionRemote surveyOption) {
		this.surveyOption = surveyOption;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
