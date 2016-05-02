package com.prodyna.pac.vothing.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.prodyna.pac.vothing.service.SecurityService;

public abstract class BaseTest {

	@Deployment(testable = false)
	public static Archive<?> createArchive() {
		// WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war");
		// war.addAsWebInfResource(new
		// File("src/test/resources/META-INF/persistence-test.xml"),
		// "classes/META-INF/persistence.xml");
		// war.addPackages(true, "com.prodyna.pac.vothing");
		// war.addClass(com.prodyna.conference.web.rest.RESTEnabler.class);

		WebArchive war = ShrinkWrap.create(WebArchive.class,
				"rest-test-vothing.war");
		// war.addAsWebInfResource(new File(
		// "src/test/resources/META-INF/persistence-test.xml"),
		// "classes/META-INF/persistence.xml");
		war.addPackages(true, "com.prodyna.pac.vothing");
		war.addPackages(true, "com.prodyna.pac.vothing.service");
		war.addClass(SecurityService.class);
		// war.addClass(LoginCredentials.class);
		war.addAsResource("persistence.xml", "META-INF/persistence.xml");
		war.addAsResource("beans.xml", "META-INF/beans.xml");

		/* Get all from the webapp in the web project, the pathes are relative */
		// war.as(ExplodedImporter.class).importDirectory(
		// "../vothing-web/src/main/webapp");

		/* Delete the beans.xml and add the local one */
		// war.delete("WEB-INF/beans.xml");
		// war.addAsWebInfResource(new File(
		// "META-INF/beans.xml"),
		// " classes/META-INF/beans.xml");
		// System.out.println(war.toString(true));
		// war.toString(true);
		return war;
	}

}
