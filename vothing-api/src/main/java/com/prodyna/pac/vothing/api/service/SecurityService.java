package com.prodyna.pac.vothing.api.service;

import com.nimbusds.jose.JOSEException;
import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.constants.PermissionEnum;
import com.prodyna.pac.vothing.api.model.LoginCredentials;
import com.prodyna.pac.vothing.api.model.Permission;
import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.model.User;
import com.prodyna.pac.vothing.api.exception.PrivateKeyException;

import java.text.ParseException;
import java.util.List;

@VothingMonitoringAnn
public interface SecurityService {

	String login(LoginCredentials loginCredentials);

	User getUserByToken(String token) throws ParseException, JOSEException, PrivateKeyException;

	boolean hasUserPermission(User user, PermissionEnum permissionEnum);

	boolean hasUserPermission(User user, Permission permission);

	boolean hasUserPermission(User user, String permissionKey);

	List<String> getUserSurveyPermissions(User user, Survey survey);

	boolean isUserAdmin(User user);

}
