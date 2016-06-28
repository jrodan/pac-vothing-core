package com.prodyna.pac.vothing.core.service;

import com.prodyna.pac.vothing.api.model.User;
import com.prodyna.pac.vothing.api.service.UserService;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Stateless
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Inject
	private Logger logger;

	@Override
	public User getUser(String email, String password)
			throws EntityNotFoundException {
		User user = null;
		List<User> users = this.getElements();

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

}
