package com.prodyna.pac.vothing.service;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.User;

import javax.persistence.EntityNotFoundException;

@VothingMonitoring
public interface UserService extends BaseService<User>{

	User getUser(String email, String password) throws EntityNotFoundException;

}
