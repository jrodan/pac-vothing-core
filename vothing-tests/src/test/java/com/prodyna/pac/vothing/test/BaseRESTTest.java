package com.prodyna.pac.vothing.test;

import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import java.net.URL;
import java.util.ArrayList;

public abstract class BaseRESTTest extends BaseTest {

	private org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

	@ArquillianResource
	private URL url;

	protected javax.ws.rs.client.Client createClient() {
		Client client = ClientBuilder.newClient();
		client.register(JsonProcessingFeature.class);
		client.register(JacksonFeature.class);
		return client;
	}

	protected WebTarget createWebTarget(String url) {
		log.info("URL = " + url);
		WebTarget target = createClient().target(url);
		return target;
	}

	protected <C> C createService(Class<C> ifaceType, String token, String url) {
		MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
		headers.add("Vothing-Token", token);
		return WebResourceFactory
				.newResource(ifaceType, createWebTarget(url), false, headers, new ArrayList<>(),
						new Form());
	}

}
