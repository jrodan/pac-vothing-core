package com.prodyna.pac.vothing.core.interceptor;

import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.core.monitoring.VothingMonitoringMXBean;
import com.prodyna.pac.vothing.core.monitoring.impl.VothingMonitoringCollector;
import org.slf4j.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by jrodan on 28/06/16.
 */
@Interceptor
@VothingMonitoringAnn
@Priority(Interceptor.Priority.APPLICATION)
public class VothingMonitoringInterceptor {

	@Inject
	private Logger logger;

	private VothingMonitoringMXBean vothingMonitoringMBean;

	/**
	 * Default constructor which connects to the performance MXBean.
	 */
	public VothingMonitoringInterceptor() {
		MBeanServer ms = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName objectName = new ObjectName(
					VothingMonitoringMXBean.OBJECT_NAME);
			vothingMonitoringMBean = new VothingMonitoringCollector();
			ms.registerMBean(vothingMonitoringMBean, objectName);
		} catch (Exception e) {
			System.out.println(e); // TODO
			//			logger.error("Unable to register MBeans");
		}
	}

	@AroundInvoke
	public Object monitorPerformance(final InvocationContext ctx) throws Exception {
		logger.trace("Monitoring interceptor has been called for [{}.{}]",
				ctx.getTarget().getClass().getName(),
				ctx.getMethod().getName());

		final long startTimeMillis = System.currentTimeMillis();
		final Object result = ctx.proceed();
		final long endTimeMillis = System.currentTimeMillis();
		java.lang.reflect.Method method = ctx.getMethod();

		this.vothingMonitoringMBean
				.addMethodExecutionDuration(method.getDeclaringClass().getName() + "." +
						method.getName(), endTimeMillis - startTimeMillis);

		return result;
	}


}
