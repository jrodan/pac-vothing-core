package com.prodyna.pac.vothing.core.producer;

import javax.enterprise.inject.Produces;
import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

/**
 * Created by jrodan on 28/06/16.
 */
public class MBeanServerProducer {

	/**
	 * Produces the {@link MBeanServer} using the {@link ManagementFactory}.
	 *
	 * @return The platform MBean server created by the {@link ManagementFactory}.
	 */
	@Produces
	public MBeanServer produceMBeanServer() {
		return ManagementFactory.getPlatformMBeanServer();
	}
}