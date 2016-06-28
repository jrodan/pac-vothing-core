package com.prodyna.pac.vothing.remote.helper;

import com.prodyna.pac.vothing.api.model.SurveyOption;
import com.prodyna.pac.vothing.api.model.SurveyOptionRating;
import com.prodyna.pac.vothing.core.model.SurveyOptionRatingImpl;
import com.prodyna.pac.vothing.remote.model.SurveyOptionRatingRemote;
import com.prodyna.pac.vothing.remote.model.SurveyOptionRemote;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jrodan on 13/05/16.
 */
@RequestScoped
public class SurveyOptionRatingConverter {

	@Inject
	private SurveyOptionConverter surveyOptionConverter;

	public Collection<SurveyOptionRating> toSurveyOptionRating(SurveyOption surveyOption,
			Collection<SurveyOptionRatingRemote> surveyOptionRatingRemotes) {
		Collection<SurveyOptionRating> surveyOptionRatings = new ArrayList<SurveyOptionRating>();

		for (SurveyOptionRatingRemote surveyOptionRatingRemote : surveyOptionRatingRemotes) {
			surveyOptionRatings.add(toSurveyOptionRating(surveyOption, surveyOptionRatingRemote));
		}

		return surveyOptionRatings;
	}

	public SurveyOptionRating toSurveyOptionRating(SurveyOption surveyOption,
			SurveyOptionRatingRemote surveyOptionRatingRemote) {
		SurveyOptionRating surveyOptionRating = new SurveyOptionRatingImpl();

		surveyOptionRating.setName(surveyOptionRatingRemote.getName());
		surveyOptionRating.setId(surveyOptionRatingRemote.getId());
		surveyOptionRating.setCreateDate(surveyOptionRatingRemote.getCreateDate());
		surveyOptionRating.setModifiedDate(surveyOptionRatingRemote.getModifiedDate());
		surveyOptionRating.setObjectKey(surveyOptionRatingRemote.getObjectKey());
		surveyOptionRating.setSurveyOption(surveyOption);
		surveyOptionRating.setUser(surveyOptionRatingRemote.getUser());

		return surveyOptionRating;
	}

	public Collection<SurveyOptionRatingRemote> toSurveyOptionRatingRemote(
			SurveyOptionRemote surveyOptionRemote,
			Collection<SurveyOptionRating> surveyOptionRatings) {
		Collection<SurveyOptionRatingRemote> surveyOptionRatingsRemote =
				new ArrayList<SurveyOptionRatingRemote>();

		for (SurveyOptionRating surveyOptionRating : surveyOptionRatings) {
			surveyOptionRatingsRemote
					.add(toSurveyOptionRatingRemote(surveyOptionRemote, surveyOptionRating));
		}

		return surveyOptionRatingsRemote;
	}

	public SurveyOptionRatingRemote toSurveyOptionRatingRemote(SurveyOptionRemote surveyOptionRmote,
			SurveyOptionRating surveyOptionRating) {
		SurveyOptionRatingRemote surveyOptionRatingRemote = new SurveyOptionRatingRemote();

		surveyOptionRatingRemote.setName(surveyOptionRatingRemote.getName());
		surveyOptionRatingRemote.setId(surveyOptionRatingRemote.getId());
		surveyOptionRatingRemote.setCreateDate(surveyOptionRatingRemote.getCreateDate());
		surveyOptionRatingRemote.setModifiedDate(surveyOptionRatingRemote.getModifiedDate());
		surveyOptionRatingRemote.setObjectKey(surveyOptionRatingRemote.getObjectKey());
		surveyOptionRatingRemote.setSurveyOptionRemote(surveyOptionRmote);
		surveyOptionRatingRemote.setUser(surveyOptionRatingRemote.getUser());

		return surveyOptionRatingRemote;
	}


}
