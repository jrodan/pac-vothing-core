package com.prodyna.pac.vothing.api.service;

import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.model.User;

import javax.persistence.EntityNotFoundException;

@VothingMonitoringAnn
public interface UserService extends BaseService<User> {

	User getUser(String email, String password) throws EntityNotFoundException;

}
