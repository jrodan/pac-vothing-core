package com.prodyna.pac.vothing.service.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import net.minidev.json.JSONObject;

import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;

import com.google.gson.Gson;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.prodyna.pac.vothing.LoginCredentials;
import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.VothingConstants;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.persistence.Role;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.service.SecurityService;

@Stateless
@VothingMonitoring
public class SecurityServiceImpl implements SecurityService, VothingConstants {

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;
	
	@Context
	private HttpServletRequest httpRequest;

	@Context
	private HttpServletResponse httpResponse;

	@Override
	public String login(LoginCredentials loginCredentials) {

		User user = null;
		String token = null;

		try {
			user = vothing.getUserService().getUser(loginCredentials.getEmail(), loginCredentials.getPassword());
		} catch (EntityNotFoundException e) {
			logger.debug("no user is existing for email " + loginCredentials.getEmail(), e);
		}

		if (user != null) {

			String jwtToken = getUserJwt(user);
			vothing.setUser(user);
			
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("jwt", jwtToken);
			token = objectBuilder.build().toString();

		} else {
			try {
				httpResponse.sendError(HTTP_CLIENT_STATUS_LOGIN_INVALID);
			} catch (IOException e) {
				logger.error("could not send error message 403", e);
			}
		}

		return token;
	}

	private String getUserJwt(User user) {
		String jwe = null;

		try {
			JWSSigner signer = new MACSigner(SERVER_PRIVATE_AUTH_KEY);

			// Create JWS payload
			final Payload payload = new Payload(user.toFrontendUserJSONObjectString());

			// Create JWS header with HS256 algorithm
			JWSObject jwsObject = new JWSObject(new JWSHeader(
					JWSAlgorithm.HS256), payload);
			try {
				jwsObject.sign(signer);

				jwe = jwsObject.serialize();
			} catch (JOSEException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (KeyLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jwe;
	}

	@Override
	public User getUserByToken(String token) throws ParseException,
			JOSEException {

		User user = null;

		JWSObject jwsObject = JWSObject.parse(token);
		JWSVerifier verifier = new MACVerifier(SERVER_PRIVATE_AUTH_KEY);
		jwsObject.verify(verifier);
		
		// TODO get user from here
		JSONObject claims = jwsObject.getPayload().toJSONObject();
		String userId = JSONObjectUtils.getJSONObject(claims, "user").get("userid").toString();
		Long userIdLong = Long.parseLong(userId);
		
		if(userIdLong > 0) {
			try {
				user = vothing.getUserService().getUser(userIdLong);
			} catch (EntityNotFoundException e) {
				logger.debug("no user is existing for userId " + userId, e);
			}
		}

		return user;

	}

	@Override
	public boolean hasUserPermission(Permission permissionToCheck) {
		User user = vothing.getUser();
		Collection<Role> roles = user.getRoles();

		for (Role role : roles) {
			Collection<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {
				if (permission.getPermissionId() == permissionToCheck
						.getPermissionId()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean hasUserPermission(String permissionKey) {
		for (Permission permission : vothing.getPermissionService().getPermissions()) {
			if (permission.getName().equalsIgnoreCase(permissionKey)) {
				return hasUserPermission(permission);
			}
		}
		return false;
	}

//	@Override
//	public String encodePassword(String password) {
//
//		String encodedPassword = Md5Crypt.md5Crypt(
//				password.getBytes(Charset.forName("UTF-8")), PASSWORD_SALT);
//
//		return encodedPassword;
//	}

}
