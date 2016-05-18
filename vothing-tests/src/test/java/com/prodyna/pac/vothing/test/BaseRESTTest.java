package com.prodyna.pac.vothing.test;

import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URL;

public abstract class BaseRESTTest extends BaseTest {

	private org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

	@ArquillianResource
	private URL url;

	protected javax.ws.rs.client.Client createClient() {
		final Client client = ClientBuilder.newClient();
		client.register(JsonProcessingFeature.class);
		client.register(JacksonFeature.class);
		return client;
	}
	
	protected WebTarget createWebTarget() {
		String fullPath = url.toString() ;//+ "rest";
		log.info("URL = " + fullPath);
		WebTarget target = createClient().target(fullPath);
		return target;
	}

	protected <C> C createService(Class<C> ifaceType) {
		return WebResourceFactory.newResource(ifaceType, createWebTarget());
	}

}
