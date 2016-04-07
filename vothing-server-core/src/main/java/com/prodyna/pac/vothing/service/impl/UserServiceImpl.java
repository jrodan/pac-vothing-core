package com.prodyna.pac.vothing.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.VothingConstants;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.service.UserService;

@Stateless
@VothingMonitoring
public class UserServiceImpl implements UserService {

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

	@Override
	public User getUser(String email, String password)
			throws EntityNotFoundException {
		User user = null;
		List<User> users = vothing.getEntityManager().createNamedQuery(
				VothingConstants.SELECT_ALL_USERS, User.class).getResultList();

		if (users != null) {
			for (User userTemp : users) {
				if (userTemp.getEmail().equalsIgnoreCase(email)
						&& userTemp.getPassword().equals(password)) {
					// TODO encrypt/decrypt password
					user = userTemp;
					break;
				}
			}
		}

		// if (user == null) {
		// throw new EntityNotFoundException(
		// "User could not be found for given email and password ["
		// + email + "]");
		// TODO
		// }

		return user;
	}

	@Override
	public User getUser(long userId) throws EntityNotFoundException {
		final User user = vothing.getEntityManager().find(User.class, userId);
		// if (user == null) {
		// throw new EntityNotFoundException(
		// "User could not be found for given userId [" + userId + "]");
		// }
		// TODO

		return user;
	}

	@Override
	public void createUser(User user) {
		vothing.getEntityManager().persist(user);
	}

}
