package com.prodyna.pac.vothing.core.monitoring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Created by jrodan on 28/06/16.
 */
@Singleton
@Startup
public class VothingMonitoringStarter {

	/**
	 * Instance of an {@link MBeanServer}.
	 */
	@Inject
	private MBeanServer platformMBeanServer;

	@Inject
	private VothingMonitoringMBean vothingMonitoringMBean;

	/**
	 * Object name to use to register this MBean to MBeanServer.
	 */
	private ObjectName objectName = null;

	/**
	 * Registers this MBean to JMX.
	 */
	@PostConstruct
	public void registerInJMX() {

		try {
			this.objectName =
					new ObjectName(
							"com.prodyna.pac.vothing.core.monitoring:type=PerformanceMonitoring");

			this.platformMBeanServer.registerMBean(vothingMonitoringMBean, this.objectName);
		} catch (final JMException e) {
			throw new IllegalStateException(
					"Problem during registration of performance monitoring into JMX", e);
		}
	}

	/**
	 * Unregisters this MBean from JMX.
	 */
	@PreDestroy
	public void unregisterFromJMX() {
		try {
			this.platformMBeanServer.unregisterMBean(this.objectName);
		} catch (final JMException e) {
			throw new IllegalStateException(
					"Problem during unregistration of performance monitoring from JMX", e);
		}
	}

}
