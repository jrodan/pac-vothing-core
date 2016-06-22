package com.prodyna.pac.vothing.remote.model;

import com.prodyna.pac.vothing.api.model.SurveyOption;
import com.prodyna.pac.vothing.core.model.SurveyOptionImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
public class SurveyOptionRemote extends SurveyOptionImpl implements BaseRemoteEntity {

	private boolean userVotedThisOption = false;

	private List<String> usersPermissions = new ArrayList<>();

	private boolean isNew = false;

	private boolean userVoted = false;

	private int votes = 0;

	public SurveyOptionRemote(SurveyOption surveyOption) {

		this.setSurvey(surveyOption.getSurvey());
		this.setCreateDate(surveyOption.getCreateDate());
		this.setModifiedDate(surveyOption.getModifiedDate());
		this.setId(surveyOption.getId());
		this.setName(surveyOption.getName());
		this.setSurveyOptionRatings(surveyOption.getSurveyOptionRatings());

	}

	@Override
	public boolean getUserVotedThisOption() {
		return userVotedThisOption;
	}

	@Override
	public void setUserVotedThisOption(boolean userVotedThisOption) {
		this.userVotedThisOption = userVotedThisOption;
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
