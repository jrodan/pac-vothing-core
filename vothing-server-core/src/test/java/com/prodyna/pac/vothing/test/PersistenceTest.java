package com.prodyna.pac.vothing.test;

import com.prodyna.pac.vothing.constants.PermissionEnum;
import com.prodyna.pac.vothing.constants.RoleConstants;
import com.prodyna.pac.vothing.model.impl.*;
import com.prodyna.pac.vothing.service.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jrodan on 04/05/16.
 */
@RunWith(Arquillian.class)
public class PersistenceTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "com.prodyna.pac.vothing")
                .addPackages(true, "com.nimbusds.jose")
                .addPackages(true, "com.google.gson")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private UserService userService;

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

    @PersistenceContext
    private EntityManager em;
//
//    @Inject
//    private AddEventHelper eventHelper;

    @Test
    @InSequence(1)
    public void testPersistence() {

        // test default user impl
        User user1 = new User();
        user1.setEmail("default@vothing.com");
        user1.setForeName("dummy");
        user1.setName("user");
        user1.setPassword("123");
        User user1DB = userService.addElement(user1);
        Assert.assertNotNull(user1DB);
        Assert.assertTrue(user1DB.getEmail().equals(user1.getEmail()));

        // test permissions
        Collection<Permission> permissions = new ArrayList<Permission>();
        Permission permissionNone = permissionService.createPermission(PermissionEnum.NONE);
        Assert.assertNotNull(permissionNone);
        Permission permissionNoneDB = permissionService.getElement(permissionNone.getId());
        Assert.assertNotNull(permissionNoneDB);
        Assert.assertEquals(permissionNone.getId(), permissionNoneDB.getId());
        permissions.add(permissionNoneDB);

        permissions.add(permissionService.createPermission(PermissionEnum.SURVEY_DELETE));
        permissions.add(permissionService.createPermission(PermissionEnum.SURVEY_ADD));
        permissions.add(permissionService.createPermission(PermissionEnum.SURVEY_UPDATE));
        permissions.add(permissionService.createPermission(PermissionEnum.SURVEY_LIST));
        permissions.add(permissionService.createPermission(PermissionEnum.ADMIN));
        permissions.add(permissionService.createPermission(PermissionEnum.SURVEYOPTIONRATING_UPDATE));
        permissions.add(permissionService.createPermission(PermissionEnum.SURVEYOPTIONRATING_UPDATE_DELETE));
        Assert.assertEquals(permissionService.getElements().size(), 8);

        // test roles
        Role adminRole = roleService.createRole(RoleConstants.ROLE_ADMIN, new ArrayList<Permission>());
        Assert.assertNotNull(adminRole);
        Assert.assertEquals(adminRole.getPermissions().size(), 0);
        Role userRole = roleService.createRole(RoleConstants.ROLE_USER, permissions);
        Assert.assertNotNull(userRole);
        Assert.assertEquals(userRole.getPermissions().size(), 8);

        // test survey
        Survey survey = new Survey();
        survey.setUser(user1DB);
        survey.setName("testSurvey1");
        Survey surveyDB = surveyService.addElement(survey);
        Assert.assertNotNull(surveyDB);
        Assert.assertTrue(surveyDB.getId() > 0);
        Assert.assertTrue(user1DB.getId() == user1.getId());

        // test update of user in db in survey table
        user1DB.setForeName("duck");
        User user1DB2 = userService.addElement(user1DB);
        Survey surveyDB2 = surveyService.getElement(surveyDB.getId());
        Assert.assertTrue(user1DB2.getForeName().equals(surveyDB2.getUser().getForeName()));

        // test survey options
        // TODO does a survey need to know its survey options in the model?
        SurveyOption surveyOption1 = new SurveyOption();
        surveyOption1.setName("Option 1");
        surveyOption1.setSurvey(survey);
        SurveyOption surveyOption2 = new SurveyOption();
        surveyOption2.setName("Option 2");
        surveyOption2.setSurvey(survey);
        SurveyOption surveyOption1DB = surveyOptionService.addElement(surveyOption1);
        SurveyOption surveyOption2DB = surveyOptionService.addElement(surveyOption2);
        Assert.assertNotNull(surveyOption1DB);
        Assert.assertTrue(surveyOption1DB.getId() > 0);
        Assert.assertTrue(surveyOption1DB.getSurvey().getId() == survey.getId());
        // TODO

        // test survey option rating
        SurveyOptionRating surveyOptionRating1 = new SurveyOptionRating();
        surveyOptionRating1.setSurveyOption(surveyOption1);
        surveyOptionRating1.setUser(null); // TODO
        SurveyOptionRating surveyOptionRating2 = new SurveyOptionRating();
        surveyOptionRating2.setSurveyOption(surveyOption2);
        surveyOptionRating2.setUser(null); // TODO
        SurveyOptionRating surveyOptionRating1DB = surveyOptionRatingService.addElement(surveyOptionRating1);
        SurveyOptionRating surveyOptionRating2DB = surveyOptionRatingService.addElement(surveyOptionRating2);
        Assert.assertNotNull(surveyOptionRating1DB);
        Assert.assertNotNull(surveyOptionRating2DB);
        Assert.assertTrue(surveyOptionRating1DB.getId() > 0);
        Assert.assertTrue(surveyOptionRating2DB.getId() > 0);
        Assert.assertTrue(surveyOptionRating1DB.getSurveyOption().getId() == surveyOption1DB.getId());

        // test user
        // TODO does a user need to know its surveys in the model?

        // test user with role
        User user2 = new User();
        user2.setEmail("user@vothing.com");
        user2.setForeName("user");
        user2.setName("user");
        user2.setPassword("123");
        List<Role> roles2 = new ArrayList<Role>();
        roles2.add(userRole);
        user2.setRoles(roles2);
        User user2DB = userService.addElement(user2);
        Assert.assertNotNull(user2DB);
        Assert.assertEquals(user2DB.getRoles().size(), 1);

        // test user with vote and survey
        User admin = new User();
        admin.setEmail("admin@vothing.com");
        admin.setForeName("admin");
        admin.setName("admin");
        admin.setPassword("123");
        User adminDB = userService.addElement(admin);
        Assert.assertNotNull(adminDB);
        // add roles
        List<Role> roles3 = new ArrayList<Role>();
        roles3.add(adminRole);
        adminDB.setRoles(roles3);
        // add surveys
        List<Survey> surveys3 = new ArrayList<Survey>();
        surveyDB.setUser(adminDB);
        surveys3.add(surveyDB); // add already created survey
        surveyService.updateElement(surveyDB);
        // add survey option
        List<SurveyOption> surveyOptions3 = new ArrayList<SurveyOption>();
        surveyOptions3.add((SurveyOption)surveyOption1DB);
        surveyOptions3.add((SurveyOption)surveyOption2DB);
        surveyDB.setSurveyOptions(surveyOptions3);
        adminDB.setSurveys(surveys3);
        // update user
        adminDB = userService.addElement(adminDB);
        Assert.assertTrue(adminDB.getRoles().size() == 1);
        Assert.assertTrue(adminDB.getSurveys().size() == 1);
        Assert.assertTrue(adminDB.getSurveyOptionRatings().size() == 0); // the result should be 0 because even if I add the options there, their userId is currently still not set correctly
        // test deletion of entries
        surveyOptionService.deleteElement(surveyOption1DB.getId());
        User adminDB2 = userService.getElement(adminDB.getId());
        Assert.assertTrue(adminDB.getId() == adminDB2.getId());
        Assert.assertTrue(adminDB2.getSurveys().size() == 1);
        Assert.assertTrue(adminDB2.getSurveys().contains(surveyDB));
        for (Survey surveyTemp : adminDB2.getSurveys()) {
            Assert.assertTrue(surveyTemp.getSurveyOptions().size() == 1);
        }
        // update user
        surveyOptionRating2DB.setUser(adminDB2);
        // TODO why will children not be updated?
        surveyOptionRatingService.updateElement(surveyOptionRating2DB);
        List<SurveyOptionRating> surveyOptionRatings3 = new ArrayList<SurveyOptionRating>();
        surveyOptionRatings3.add(surveyOptionRating2DB);
        adminDB2.setSurveyOptionRatings(surveyOptionRatings3);
        User adminDB3 = userService.updateElement(adminDB2);
        Assert.assertTrue(adminDB3.getSurveyOptionRatings().size() == 1);

        // TODO add new survey without having it persisted before to a user

        // add further dummy data
        surveyDB2.setId(0);
        surveyOption1DB.setId(0);
        surveyOption2DB.setId(0);
        surveyDB2.setName("survey 2");
        surveyService.addElement(surveyDB2);
        Survey surveyDB3 = new Survey();
        surveyDB3.setUser(adminDB3);
        surveyDB3.setName("survey 3");
        surveyOption1DB.setId(0);
        surveyOption2DB.setId(0);
        List<SurveyOption> surveyOptionList2 = new ArrayList<SurveyOption>();
        surveyOptionList2.add((SurveyOption)surveyOption1DB);
        surveyOptionList2.add((SurveyOption)surveyOption2DB);
        surveyDB3.setSurveyOptions(surveyOptionList2);
        surveyService.addElement(surveyDB3);

    }


//    @Test
//    @InSequence(2)
//    public void testEntitySerialization() {
//        User admin = userService.getUser("admin@vothing.com","123");
//
////        admin.getSurveys()
//
//    }
//
//    @Test
//    @InSequence(3)
//    public void testEntityDerialization() {
//        User admin = userService.getUser("admin@vothing.com","123");
//
////        admin.getSurveys()
//
//    }
//
//    @Test
//    @InSequence(2)
//    public void testEntityDerialization() {
//        User admin = userService.getUser("admin@vothing.com","123");
//
////        admin.getSurveys()
//
//    }
}
