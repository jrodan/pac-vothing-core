package com.prodyna.pac.vothing.test;

import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import java.net.URL;
import java.util.ArrayList;

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
		String fullPath = url.toString();//+ "rest";
		log.info("URL = " + fullPath);
		WebTarget target = createClient().target(fullPath);
		return target;
	}

	protected <C> C createService(Class<C> ifaceType, String token) {
		MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add("Vothing-Token",token);
		return WebResourceFactory.newResource(ifaceType, createWebTarget(), false, headers, new ArrayList<>(), new Form());
	}

}
