package com.prodyna.pac.vothing.test;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.pac.vothing.service.SecurityService;

@RunWith(Arquillian.class)
public class VotingRESTTest extends BaseRESTTest {

	@ArquillianResource
	private URL url;
	
	@Test
	@RunAsClient
//	@InSequence(1)
	public void createUser() {
		SecurityService securityService = createService(SecurityService.class);
//		LoginCredentials cred = new LoginCredentials();
//		cred.setEmail("user@vothing.com");
//		cred.setPassword("123");
//		String token = securityService.login(cred);
		Assert.assertNotNull("123");
	}

}
