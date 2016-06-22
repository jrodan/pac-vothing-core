package com.prodyna.pac.vothing.api.model;

import com.prodyna.pac.vothing.api.model.api.SurveyInterface;

import javax.persistence.MappedSuperclass;
import java.util.Collection;

/**
 * Created by jrodan on 21/06/16.
 */
@MappedSuperclass
public abstract class Survey extends BaseModelImpl<Survey> implements SurveyInterface {
	public Collection<SurveyOption> getSurveyOptions() {
		return null;
	}

	public void setSurveyOptions(Collection<SurveyOption> surveyOptions) {
	}

	public User getUser() {
		return null;
	}

	public void setUser(User user) {
	}
}
