package com.prodyna.pac.vothing.service;

import com.nimbusds.jose.JOSEException;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.persistence.Survey;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.security.LoginCredentials;
import com.prodyna.pac.vothing.security.PermissionEnum;

import java.text.ParseException;
import java.util.List;

@VothingMonitoring
public interface SecurityService {

	String login(LoginCredentials loginCredentials);
	
	User getUserByToken(String token) throws ParseException, JOSEException;

	public boolean hasUserPermission(User user, PermissionEnum permissionEnum);
	
	public boolean hasUserPermission(User user, Permission permission);
	
	public boolean hasUserPermission(User user, String permissionKey);

	public List<String> getUserSurveyPermissions(User user, Survey survey);

	public boolean isUserAdmin(User user);

}
