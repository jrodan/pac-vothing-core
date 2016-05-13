package com.prodyna.pac.vothing.service;

import com.prodyna.pac.vothing.model.impl.User;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;

import javax.persistence.EntityNotFoundException;

@VothingMonitoring
public interface UserService extends BaseService<User>{

	User getUser(String email, String password) throws EntityNotFoundException;

}
