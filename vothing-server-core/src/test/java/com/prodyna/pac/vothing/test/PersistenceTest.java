package com.prodyna.pac.vothing.test;

import com.prodyna.pac.vothing.constants.RoleConstants;
import com.prodyna.pac.vothing.persistence.*;
import com.prodyna.pac.vothing.security.PermissionEnum;
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
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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
        Assert.assertEquals(permissionService.getElements(), 5);

        // test roles
        Role adminRole = roleService.createRole(RoleConstants.ROLE_ADMIN, null);
        Assert.assertNotNull(adminRole);
        Assert.assertEquals(adminRole.getPermissions(), 0);
        Role userRole = roleService.createRole(RoleConstants.ROLE_USER, permissions);
        Assert.assertNotNull(userRole);
        Assert.assertEquals(userRole.getPermissions(), 5);

        // test survey
        Survey survey = new Survey();
        survey.setUser(null); // TODO
        survey.setName("testSurvey1");
        Survey surveyDB = surveyService.createSurvey(survey);
        Assert.assertNotNull(surveyDB);
        Assert.assertTrue(surveyDB.getId() > 0);

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


    }

}
