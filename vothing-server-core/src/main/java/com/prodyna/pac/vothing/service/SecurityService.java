package com.prodyna.pac.vothing.service;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nimbusds.jose.JOSEException;
import com.prodyna.pac.vothing.LoginCredentials;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.persistence.User;

@Path("/vothing/security")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@VothingMonitoring
public interface SecurityService {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String login(LoginCredentials loginCredentials);

	User getUserByToken(String token) throws ParseException, JOSEException;

	public boolean hasUserPermission(Permission permission);

	public boolean hasUserPermission(String permissionKey);

}
