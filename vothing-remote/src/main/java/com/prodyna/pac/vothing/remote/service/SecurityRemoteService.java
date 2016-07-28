package com.prodyna.pac.vothing.remote.service;

import com.prodyna.pac.vothing.api.model.LoginCredentials;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by jrodan on 17/05/16.
 */
@Path("/vothing/security")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SecurityRemoteService {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String login(LoginCredentials loginCredentials);
}
