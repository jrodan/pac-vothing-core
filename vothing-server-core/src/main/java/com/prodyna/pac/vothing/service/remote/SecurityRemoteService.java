package com.prodyna.pac.vothing.service.remote;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.security.LoginCredentials;

/**
 * Created by jrodan on 17/05/16.
 */
//@Provider
//@Path("/vothing/security")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
@VothingMonitoring
public interface SecurityRemoteService {

//    @POST
//    @Path("/login")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    String login(LoginCredentials loginCredentials);
}
