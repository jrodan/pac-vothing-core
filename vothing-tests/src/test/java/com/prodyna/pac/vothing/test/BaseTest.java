package com.prodyna.pac.vothing.test;

import com.prodyna.pac.vothing.service.SecurityService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class BaseTest {

    @Deployment(testable = false)
    public static Archive<?> createArchive() {

        WebArchive war = ShrinkWrap.create(WebArchive.class,
                "rest-test-vothing.war");
        war.addPackages(true, "com.prodyna.pac.vothing");
        war.addPackages(true, "com.nimbusds");
        war.addPackages(true, "com.fasterxml.jackson");
        war.addPackages(true, "net.minidev.json");
        war.addClass(SecurityService.class);
        war.addAsResource("persistence.xml", "META-INF/persistence.xml");
        war.addAsResource("beans.xml", "META-INF/beans.xml");

        return war;
    }

}
