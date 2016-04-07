package com.prodyna.pac.vothing.service;

import javax.persistence.EntityNotFoundException;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.User;

@VothingMonitoring
public interface UserService {

	User getUser(String email, String password) throws EntityNotFoundException;

	User getUser(long userId) throws EntityNotFoundException;

	void createUser(User user);

}
