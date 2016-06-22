package com.prodyna.pac.vothing.api.model;

import com.prodyna.pac.vothing.api.model.api.SurveyInterface;

import javax.persistence.MappedSuperclass;

/**
 * Created by jrodan on 21/06/16.
 */
@MappedSuperclass
public abstract class Survey extends BaseModelImpl<Survey> implements SurveyInterface {
}
