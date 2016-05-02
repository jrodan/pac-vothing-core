package com.prodyna.pac.vothing;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.constants.RoleConstants;
import com.prodyna.pac.vothing.persistence.Permission;
import com.prodyna.pac.vothing.persistence.Role;
import com.prodyna.pac.vothing.persistence.Survey;
import com.prodyna.pac.vothing.persistence.SurveyOption;
import com.prodyna.pac.vothing.persistence.SurveyOptionRating;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.security.PermissionEnum;
import com.prodyna.pac.vothing.service.PermissionService;
import com.prodyna.pac.vothing.service.RoleService;
import com.prodyna.pac.vothing.service.SurveyOptionRatingService;
import com.prodyna.pac.vothing.service.SurveyOptionService;
import com.prodyna.pac.vothing.service.SurveyService;
import com.prodyna.pac.vothing.service.UserService;

@WebListener
public class VothingServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do nothing in this case
	}
	
	@Inject
	private UserService userService;
	
	@Inject
	private Logger logger;
	
	@Inject
	private PermissionService permissionService;
	
	@Inject
	private RoleService roleService;
	
	@Inject
	private SurveyService surveyService;
	
	@Inject
	private SurveyOptionService surveyOptionService;
	
	@Inject
	private SurveyOptionRatingService surveyOptionRatingService;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		initDatabase();
	}
	
	public void initDatabase() {
		// check if default data exist otherwise create
		
		// TODO init permissions
		Collection<Permission> permissions = new ArrayList<Permission>();
		permissionService.createPermission(PermissionEnum.NONE);
		permissionService.createPermission(PermissionEnum.SURVEY_DELETE);
		permissions.add(permissionService.createPermission(PermissionEnum.SURVEY_ADD));
		permissions.add(permissionService.createPermission(PermissionEnum.SURVEY_UPDATE));
		permissions.add(permissionService.createPermission(PermissionEnum.SURVEY_LIST));
		
		// TODO init roles
		Role adminRole = roleService.createRole(RoleConstants.ROLE_ADMIN, null);
		Role userRole = roleService.createRole(RoleConstants.ROLE_USER, permissions);
		
		Collection<Role> roles = new ArrayList<Role>();
		
		User user = null;
		try {
			user = userService.getUser("default@vothing.com", "123");
		} catch (EntityNotFoundException e) {
			logger.error("no default users are existing - continue creating these",e);
		}
		
		
		if(user == null) {
			
			// create default users
			user = new User();
			user.setEmail("default@vothing.com");
			user.setForeName("dummy");
			user.setName("user");
			user.setPassword("123");
			userService.createUser(user);
			
			user = new User();
			user.setEmail("user@vothing.com");
			user.setForeName("user");
			user.setName("user");
			user.setPassword("123");
			roles.add(userRole);
			user.setRoles(roles);
			userService.createUser(user);
			roles.clear();
			
			User admin = new User();
			admin.setEmail("admin@vothing.com");
			admin.setForeName("admin");
			admin.setName("admin");
			admin.setPassword("123");
			roles.add(adminRole);
			admin.setRoles(roles);
			userService.createUser(admin);
			
			// add sample survey
			Survey survey = new Survey();
			survey.setName("surveyOne");
			survey.setUser(admin);
			SurveyOption surveyOption1 = new SurveyOption();
			surveyOption1.setName("Option 1");
			surveyOption1.setSurvey(survey);
			SurveyOption surveyOption2 = new SurveyOption();
			surveyOption2.setName("Option 2");
			surveyOption2.setSurvey(survey);
			SurveyOptionRating surveyOptionRating1 = new SurveyOptionRating();
			surveyOptionRating1.setSurveyOption(surveyOption1);
			surveyOptionRating1.setUser(user);
			SurveyOptionRating surveyOptionRating2 = new SurveyOptionRating();
			surveyOptionRating2.setSurveyOption(surveyOption2);
			surveyOptionRating2.setUser(admin);
			
			// persist
			surveyService.addElement(survey);
			surveyOptionService.addElement(surveyOption1);
			surveyOptionService.addElement(surveyOption2);	
			surveyOptionRatingService.addElement(surveyOptionRating1);
			surveyOptionRatingService.addElement(surveyOptionRating2);
			
			
		}
		
		
	}

}
