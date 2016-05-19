package com.prodyna.pac.vothing.service.remote;

import com.prodyna.pac.vothing.security.LoginCredentials;

/**
 * Created by jrodan on 17/05/16.
 */
public interface SecurityRemoteService {

    String login(LoginCredentials loginCredentials);
}
