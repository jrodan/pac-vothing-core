package com.prodyna.pac.vothing.service;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.User;

import javax.persistence.EntityNotFoundException;

@VothingMonitoring
public interface UserService {

	User getUser(String email, String password) throws EntityNotFoundException;

	User getUser(long userId) throws EntityNotFoundException;

	User createUser(User user);

}
