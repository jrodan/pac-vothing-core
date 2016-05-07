package com.prodyna.pac.vothing.service.impl;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Survey;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.service.SurveyService;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@VothingMonitoring
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements SurveyService {

    @Inject
    private Logger logger;

    @Inject
    private Vothing vothing;

    @Override
    public List<Survey> getSurveys() {
        List<Survey> surveysReturn = new ArrayList<Survey>();

        List<Survey> surveys = super.getElements();
        User userContext = vothing.getUser();

        // add permissions for each entity
        for(Survey survey : surveys) {
            List<String> permissions = vothing.getSecurityService().getUserSurveyPermissions(userContext, survey);
            survey.setUsersPermissions(permissions);
            surveysReturn.add(survey);
        }

        return surveysReturn;
    }

    @Override
    public Survey getSurvey(long surveyId) {
        return super.getElement(surveyId);
    }

    @Override
    public void deleteVote(long surveyId) {
        super.deleteElement(surveyId);
    }

    @Override
    public Survey updateSurvey(Survey survey) {
        return super.addElement(survey);
    }

    @Override
    public Survey createSurvey(Survey survey) {
        return super.addElement(survey);
    }

    @Override
    public Survey getElement(long id) {
        return this.getSurvey(id);
    }

    @Override
    public List<Survey> getElements() {
        return this.getSurveys();
    }


}
