package com.prodyna.pac.vothing.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.nimbusds.jose.JOSEException;
import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.VothingConstants;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.service.SecurityService;

@Provider
@Priority(2)
// @PreMatching
// @Priority(Priorities.AUTHENTICATION)
// TODO @WebFilter(filterName="VothingServletSecurityFilter",
// value="/restricted/*")
@Path("/restricted/*")
@VothingMonitoring
public class VothingSecurityFilter implements ContainerRequestFilter, VothingConstants {
	
	@Context
	private ResourceInfo resourceInfo;
	
	@Inject
	private SecurityService securityService;

	@Inject
	private Vothing vothing;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		boolean isLoginValid = false;
		
		Method method = resourceInfo.getResourceMethod();
		if(method.getName().equals("login")) {
			return;
		}

		/*
		 * check if login is requested. This will not be handled in the security
		 * filter. The security filter only checks of a already signed in login
		 * is still valid. The login method can be accessed without permission
		 * check and is excluded from the servlet's URL mapper.
		 */
		
		// check if token is valid
		String token = requestContext.getHeaderString(VOTHING_ACCESS_TOKEN);
		if (token != null) {
			User user = null;
			try {
				user = securityService.getUserByToken(token);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JOSEException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (user != null) {
				vothing.setUser(user);
				isLoginValid = true;
				requestContext.setProperty("user", user);
			}
		}

		// stop access to application if login is not valid
		if (!isLoginValid) {
			vothing.setUser(null);
			requestContext.setProperty("user", null);
			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
					.build());
		}
		
	}

}
