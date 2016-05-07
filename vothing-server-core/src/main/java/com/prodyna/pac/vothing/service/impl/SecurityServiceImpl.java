package com.prodyna.pac.vothing.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.RoleConstants;
import com.prodyna.pac.vothing.constants.VothingConstants;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.persistence.Role;
import com.prodyna.pac.vothing.persistence.Survey;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.security.LoginCredentials;
import com.prodyna.pac.vothing.security.PermissionEnum;
import com.prodyna.pac.vothing.service.SecurityService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
@VothingMonitoring
public class SecurityServiceImpl implements SecurityService, VothingConstants {

    @Inject
    private Logger logger;

    @Inject
    private Vothing vothing;

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

        JSONObject claims = jwsObject.getPayload().toJSONObject();
        String userId = JSONObjectUtils.getJSONObject(claims, "user").get("userid").toString();
        Long userIdLong = Long.parseLong(userId);

        if (userIdLong > 0) {
            try {
                user = vothing.getUserService().getElement(userIdLong);
            } catch (EntityNotFoundException e) {
                logger.debug("no user is existing for userId " + userId, e);
            }
        }

        return user;

    }

    @Override
    public boolean hasUserPermission(User user, PermissionEnum permissionEnum) {
        return hasUserPermission(user, permissionEnum.getName());
    }

    @Override
    public boolean hasUserPermission(User user, Permission permissionToCheck) {
        Collection<Role> roles = user.getRoles();

        for (Role role : roles) {

            if (role.getName().equalsIgnoreCase(RoleConstants.ROLE_ADMIN)) {
                return true;
            }

            Collection<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                if (permission.getId() == permissionToCheck
                        .getId()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasUserPermission(User user, String permissionKey) {
        for (Permission permission : vothing.getPermissionService().getElements()) {
            if (permission.getName().equalsIgnoreCase(permissionKey)) {
                return hasUserPermission(user, permission);
            }
        }
        return false;
    }

    @Override
    public List<String> getUserSurveyPermissions(User user, Survey survey) {

        List<String> permissions = new ArrayList<String>();

        if (user.getId() == survey.getUser().getId() && !isUserAdmin(user)) {
            permissions.add(PermissionEnum.SURVEY_DELETE.getName());
            permissions.add(PermissionEnum.SURVEY_UPDATE.getName());
        }
        if (isUserAdmin(user)) {
            permissions.add(PermissionEnum.ADMIN.getName());
        }
        return permissions;
    }

    @Override
    public boolean isUserAdmin(User user) {
        boolean isUserAdmin = false;

        for (Role role : user.getRoles()) {
            if (role.getName().equals(RoleConstants.ROLE_ADMIN)) {
                isUserAdmin = true;
                break;
            }
        }

        return isUserAdmin;
    }

}
