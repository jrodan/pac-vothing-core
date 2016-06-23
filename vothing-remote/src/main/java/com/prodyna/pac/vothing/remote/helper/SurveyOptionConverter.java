package com.prodyna.pac.vothing.remote.helper;

import com.prodyna.pac.vothing.api.model.SurveyOption;
import com.prodyna.pac.vothing.api.model.SurveyOptionRating;
import com.prodyna.pac.vothing.api.model.User;
import com.prodyna.pac.vothing.core.model.SurveyOptionImpl;
import com.prodyna.pac.vothing.remote.model.SurveyOptionRemote;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jrodan on 13/05/16.
 */
@RequestScoped
public class SurveyOptionConverter {

	@Inject
	private SurveyOptionRatingConverter surveyOptionRatingConverter;

	public Collection<SurveyOption> toSurveyOptions(Collection<SurveyOptionRemote> surveyOptionRemoteList) {
		Collection<SurveyOption> surveyOptionList = new ArrayList<SurveyOption>();

		for (SurveyOptionRemote surveyOptionRemote : surveyOptionRemoteList) {
			surveyOptionList.add(toSurveyOption(surveyOptionRemote));
		}

		return surveyOptionList;
	}

	public SurveyOption toSurveyOption(SurveyOptionRemote surveyOptionRemote) {

		SurveyOption surveyOption = new SurveyOptionImpl();
		surveyOption.setObjectKey(surveyOptionRemote.getObjectKey());
		surveyOption.setModifiedDate(surveyOptionRemote.getModifiedDate());
		surveyOption.setCreateDate(surveyOptionRemote.getCreateDate());
		surveyOption.setId(surveyOptionRemote.getId());
		surveyOption.setName(surveyOptionRemote.getName());
//		surveyOption.setSurvey(surveyOptionRemote.get.getSurvey());
		surveyOption.setSurveyOptionRatings(surveyOptionRatingConverter
				.toSurveyOptionRating(surveyOption,
				surveyOptionRemote.getSurveyOptionRatings()));

		return surveyOption;
	}

	public Collection<SurveyOptionRemote> toSurveyOptionsRemote(User user, Collection<SurveyOption> surveyOptionList) {
		Collection<SurveyOptionRemote> surveyOptionRemoteList = new ArrayList<SurveyOptionRemote>();

		for (SurveyOption surveyOption : surveyOptionList) {
			surveyOptionRemoteList.add(toSurveyOptionRemote(user, surveyOption));
		}

		return surveyOptionRemoteList;
	}

	public SurveyOptionRemote toSurveyOptionRemote(User user, SurveyOption surveyOption) {

		SurveyOptionRemote surveyOptionRemote = new SurveyOptionRemote();
		surveyOptionRemote.setObjectKey(surveyOption.getObjectKey());
		surveyOptionRemote.setModifiedDate(surveyOption.getModifiedDate());
		surveyOptionRemote.setCreateDate(surveyOption.getCreateDate());
		surveyOptionRemote.setId(surveyOption.getId());
		surveyOptionRemote.setName(surveyOption.getName());
//		surveyOptionRemote.setSurveyRemote(surveyOption.getSurvey());
		surveyOptionRemote.setVotes(surveyOption.getSurveyOptionRatings().size());

		boolean hasUserVoted = false;
		boolean hasUserVotedThisOption = false;

		for (SurveyOptionRating surveyOptionRating : surveyOption.getSurveyOptionRatings()) {
			if (surveyOptionRating.getUser().getId() == user.getId()) {
				hasUserVoted = true;
				hasUserVotedThisOption = true;
				break;
			}
		}

		surveyOptionRemote.setUserVotedThisOption(hasUserVotedThisOption);
		surveyOptionRemote.setUserVoted(hasUserVoted);
		surveyOptionRemote.setSurveyOptionRatingsRemote(surveyOptionRatingConverter
				.toSurveyOptionRatingRemote(surveyOptionRemote, surveyOption.getSurveyOptionRatings()));

		return surveyOptionRemote;
	}


}
