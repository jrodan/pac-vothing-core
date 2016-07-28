package com.prodyna.pac.vothing.remote.service.impl;

import com.prodyna.pac.vothing.api.Vothing;
import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.model.LoginCredentials;
import com.prodyna.pac.vothing.api.service.SecurityService;
import com.prodyna.pac.vothing.remote.service.SecurityRemoteService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static com.prodyna.pac.vothing.api.constants.VothingConstants.HTTP_CLIENT_STATUS_LOGIN_INVALID;

@Provider
@VothingMonitoringAnn
public class SecurityRemoteServiceImpl implements SecurityRemoteService {

	@Inject
	private Vothing vothing;

	@Inject
	private SecurityService securityService;

	@Context
	private HttpServletRequest httpRequest;

	@Context
	private HttpServletResponse httpResponse;

	@Inject
	private Logger logger;

	@Override
	public String login(LoginCredentials loginCredentials) {
		String token = securityService.login(loginCredentials);

		if (token == null) {
			try {
				httpResponse.setHeader("Access-Control-Allow-Origin", "*");
				httpResponse.sendError(HTTP_CLIENT_STATUS_LOGIN_INVALID);
			} catch (IOException e) {
				logger.error("could not send error message 403", e);
			}
		}

		return token;
	}
}
