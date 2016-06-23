package com.prodyna.pac.vothing.remote.model;

import java.util.Collection;

/**
 * Created by jrodan on 13/05/16.
 */
public class SurveyOptionRemote extends BaseRemoteEntityImpl  {

	private Collection<SurveyOptionRatingRemote> surveyOptionRatings;

//	private SurveyRemote survey;

	private long votes = 0;

	private boolean hasUserVoted = false;

	public boolean hasUserVoted() {
		return hasUserVoted;
	}

	public void setUserVoted(boolean hasUserVoted) {
		this.hasUserVoted = hasUserVoted;
	}

	public long getVotes() {
		return votes;
	}

	public void setVotes(long votes) {
		this.votes = votes;
	}

	public void setSurveyOptionRatings(Collection<SurveyOptionRatingRemote> surveyOptionRatings) {
		this.surveyOptionRatings = surveyOptionRatings;
	}

	public SurveyOptionRemote() {

	}

	public Collection<SurveyOptionRatingRemote> getSurveyOptionRatings() {
		return surveyOptionRatings;
	}

	public void setSurveyOptionRatingsRemote(Collection<SurveyOptionRatingRemote> surveyOptionRatings) {
		this.surveyOptionRatings = surveyOptionRatings;
	}

//	public SurveyRemote getSurvey() {
//		return survey;
//	}
//
//	public void setSurveyRemote(SurveyRemote survey) {
//		this.survey = survey;
//	}

}
