package com.prodyna.pac.vothing.api.model;

import com.prodyna.pac.vothing.api.model.api.SurveyOptionRatingInterface;

import javax.persistence.MappedSuperclass;

/**
 * Created by jrodan on 21/06/16.
 */
@MappedSuperclass
public abstract class SurveyOptionRating extends BaseModelImpl<SurveyOptionRating>
		implements SurveyOptionRatingInterface {
}
