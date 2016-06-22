package com.prodyna.pac.vothing.remote.service;

import com.prodyna.pac.vothing.api.model.LoginCredentials;

/**
 * Created by jrodan on 17/05/16.
 */
public interface SecurityRemoteService {

	String login(LoginCredentials loginCredentials);
}
