package com.prodyna.pac.vothing.remote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prodyna.pac.vothing.api.model.User;

import java.util.Collection;

/**
 * Created by jrodan on 13/05/16.
 */
public class SurveyRemote extends BaseRemoteEntityImpl {

	private User user;

	private Collection<SurveyOptionRemote> surveyOptions;

	private long votes;

	public long getVotes() {
		return votes;
	}

	public void setVotes(long votes) {
		this.votes = votes;
	}

	public Collection<SurveyOptionRemote> getSurveyOptionsRemote() {
		return surveyOptions;
	}

	public void setSurveyOptionsRemote(Collection<SurveyOptionRemote> surveyOptions) {
		this.surveyOptions = surveyOptions;
	}

	public User getUser() {
		return user;
	}

	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
	}
}
