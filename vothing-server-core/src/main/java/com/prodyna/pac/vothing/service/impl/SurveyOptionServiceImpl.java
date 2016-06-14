package com.prodyna.pac.vothing.service.impl;

import com.prodyna.pac.vothing.model.impl.Survey;
import com.prodyna.pac.vothing.model.impl.SurveyOption;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.service.SurveyOptionService;
import com.prodyna.pac.vothing.service.SurveyService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

@Stateless
@VothingMonitoring
public class SurveyOptionServiceImpl extends BaseServiceImpl<SurveyOption> implements SurveyOptionService {

	@Inject
	private SurveyService surveyService;

	@Override
	public Survey updateSurveyOptions(long surveyId, Collection<SurveyOption> surveyOptionList) {

		// get survey
		Survey survey = surveyService.getElement(surveyId);

		Collection<SurveyOption> surveyOptionListNew = new ArrayList<>();

		// remove DB surveyOptions
		// if DB element was removed
		survey.getSurveyOptions().stream().filter(surveyOptionDB -> !surveyOptionList.contains(surveyOptionDB)).forEach(surveyOptionDB -> {
			this.deleteElement(surveyOptionDB.getId());
		});

		// add or update surveyOptions
		for (SurveyOption surveyOption : surveyOptionList) {
			if (surveyOption.getId() == 0) {
				surveyOption.setSurvey(survey);
				SurveyOption surveyOptionDB = this.addElement(surveyOption);
				surveyOptionListNew.add(surveyOptionDB);
			} else {
				surveyOption.setSurvey(survey);
				SurveyOption surveyOptionDB = this.updateElement(surveyOption);
				surveyOptionListNew.add(surveyOptionDB);
			}
		}

		survey.setSurveyOptions(surveyOptionListNew);

		survey = surveyService.updateElement(survey);

		return survey;
	}
}
