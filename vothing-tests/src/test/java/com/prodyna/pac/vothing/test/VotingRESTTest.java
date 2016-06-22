package com.prodyna.pac.vothing.test;

import com.prodyna.pac.vothing.api.model.LoginCredentials;
import com.prodyna.pac.vothing.remote.model.SurveyRemote;
import com.prodyna.pac.vothing.remote.service.SecurityRemoteService;
import com.prodyna.pac.vothing.remote.service.SurveyRemoteService;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;
import java.util.List;

@RunWith(Arquillian.class)
public class VotingRESTTest extends BaseRESTTest {

    @ArquillianResource
    private URL url;

    @Test
    @RunAsClient
    @InSequence(1)
    public void testLogin() {
        String token = login();
        Assert.assertNotNull(
                token);
    }

    private String login () {
        SecurityRemoteService securityService = createService(SecurityRemoteService.class, null);
        LoginCredentials cred = new LoginCredentials();
        cred.setEmail("admin@vothing.com");
        cred.setPassword("123");
        String token = securityService.login(cred);
        return token;
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void testGetSurveys() {
        String token = login();
        Assert.assertNotNull(token);
        SurveyRemoteService surveyRemoteService = createService(SurveyRemoteService.class, token);
        List<SurveyRemote> surveys = surveyRemoteService.getSurveys();
        Assert.assertNotNull(surveys);
        Assert.assertTrue(surveys.size() > 0);
    }

}
