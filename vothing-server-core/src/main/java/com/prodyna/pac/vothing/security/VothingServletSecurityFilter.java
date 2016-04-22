package com.prodyna.pac.vothing.security;

import java.io.IOException;
import java.text.ParseException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;

import com.nimbusds.jose.JOSEException;
import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.VothingConstants;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.service.SecurityService;

@Provider
//@PreMatching
//@Priority(Priorities.AUTHENTICATION)
@WebFilter(filterName="VothingServletSecurityFilter", value="/restricted/*")
@VothingMonitoring
public class VothingServletSecurityFilter implements Filter, VothingConstants {

	@Inject
	private SecurityService securityService;

	@Inject
	private Vothing vothing;
	
	private FilterConfig filterConfig;

	@Override
	public void destroy() {
		// unused
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		boolean isLoginValid = false;

		/*
		 * check if login is requested.
		 * This will not be handled in the security
		 * filter. The security filter only checks of a already signed in login
		 * is still valid. The login method can be accessed without permission
		 * check and is excluded from the servlet's URL mapper.
		 */

		// check if token is valid
		String token = httpRequest.getHeader(VOTHING_ACCESS_TOKEN);
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
				request.setAttribute("user", user);
			}
		}

		// continue with filter chain
		if (isLoginValid) {
			chain.doFilter(request, response);
		} else {
			vothing.setUser(null);
			request.setAttribute("user", null);
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
