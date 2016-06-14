package com.prodyna.pac.vothing.model.remote;

import com.prodyna.pac.vothing.model.impl.Survey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
public class SurveyRemote extends Survey implements BaseRemoteEntity {

	private List<String> usersPermissions = new ArrayList<>();

	private boolean isNew = false;

	private boolean userVoted = false;

	private int votes = 0;

	private List<SurveyOptionRemote> surveyOptionRemotes;

	public List<SurveyOptionRemote> getSurveyOptionsRemote() {
		return this.surveyOptionRemotes;
	}

	public void setSurveyOptionsRemote(List<SurveyOptionRemote> surveyOptionRemotes) {
		this.surveyOptionRemotes = surveyOptionRemotes;
	}

	@Override
	public boolean getUserVotedThisOption() {
		return false;
	}

	@Override
	public void setUserVotedThisOption(boolean userVotedThisOption) {

	}

	@Override
	public List<String> getUsersPermissions() {
		return usersPermissions;
	}

	@Override
	public void setUsersPermissions(List<String> usersPermissions) {
		this.usersPermissions = usersPermissions;
	}

	@Override
	public boolean isNew() {
		return isNew;
	}

	@Override
	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

	public boolean isUserVoted() {
		return userVoted;
	}

	public void setUserVoted(boolean userVoted) {
		this.userVoted = userVoted;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

}
