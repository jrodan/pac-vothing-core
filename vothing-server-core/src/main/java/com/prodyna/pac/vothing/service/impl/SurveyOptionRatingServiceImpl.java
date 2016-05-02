package com.prodyna.pac.vothing.service.impl;

import javax.ejb.Stateless;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.SurveyOptionRating;
import com.prodyna.pac.vothing.service.SurveyOptionRatingService;

@Stateless
@VothingMonitoring
public class SurveyOptionRatingServiceImpl extends BaseServiceImpl<SurveyOptionRating> implements SurveyOptionRatingService {

}
