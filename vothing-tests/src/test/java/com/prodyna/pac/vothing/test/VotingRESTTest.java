package com.prodyna.pac.vothing.test;

import java.net.URL;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.service.SecurityService;
import com.prodyna.pac.vothing.service.SessionService;
import com.prodyna.pac.vothing.service.UserService;
import com.prodyna.pac.vothing.service.VoteService;

@RunWith(Arquillian.class)
public class VotingRESTTest {
	
	@Inject
	private Logger logger;

	@Deployment
	@OverProtocol("Servlet 3.0")
	public static WebArchive createDeployment() {
		WebArchive wa = ShrinkWrap
				.create(WebArchive.class, "rest-test-vothing.war")
				.addPackages(true, "com.prodyna.pac.vothing")
				.addAsResource("persistence.xml", "META-INF/persistence.xml")
				.addAsResource("beans.xml", "META-INF/beans.xml");
		wa.toString(true);
		return wa;
	}

	@Test
	public void doIt() {
		Assert.assertFalse(false);
	}

	@ArquillianResource
	private URL deploymentURL;

	@Inject
	private UserService userService;

	@Inject
	private SecurityService securityService;

	@Inject
	private VoteService voteService;
	
	@Inject
	private SessionService sessionService;

	@Context
	private HttpServletRequest request;

	@Test
	public void postWithoutMocking() throws Exception {
		// init 
		// TODO
		
		String email = "default@vothing.com";
		String password = "123";
		String sessionKey = null;
		
		// login
		sessionKey = getResponseString("/vothing-core/vothing/security/login?email="+email+"&password="+password);
        Assert.assertNotNull(sessionKey);
        
        // check if login returns a valid token and user is signed in
        User userSignedIn = securityService.getUserByAccessToken(sessionKey);
        Assert.assertNotNull(userSignedIn);
        Assert.assertEquals(userSignedIn.getEmail(), email);
        
        // check if user can get votes now after login
        
        
        
        
	}
	
	private String getResponseString(String action) {
		String clientPath = System.getProperty("jboss.client.address");
		ResteasyWebTarget target = null;
		if(clientPath == null) {
			clientPath = "http://macci:8080";
			logger.warn("could not find System Property clientPath jboss.client.address");
		}
		ResteasyClient client = new ResteasyClientBuilder().build();
		target = client.target(clientPath+action);
        Response response = target.request().get();
        String responseString = response.readEntity(String.class);
        response.close();
        return responseString;
	}

}
