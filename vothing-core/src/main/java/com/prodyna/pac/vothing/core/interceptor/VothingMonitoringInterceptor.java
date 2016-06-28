package com.prodyna.pac.vothing.core.interceptor;

import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.core.monitoring.VothingMonitoringMBean;
import org.slf4j.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * Created by jrodan on 28/06/16.
 */
@VothingMonitoringAnn
// TODO fix interceptor problem
//@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class VothingMonitoringInterceptor implements Serializable {

	@Inject
	private Logger logger;

	@Inject
	private VothingMonitoringMBean vothingMonitoringMBean;

	@AroundInvoke
	public Object monitorPerformance(final InvocationContext ctx) throws Exception {
		this.logger.trace("Monitoring interceptor has been called for [{}.{}]",
				ctx.getTarget().getClass().getName(),
				ctx.getMethod().getName());

		final long startTimeMillis = System.currentTimeMillis();
		final Object result = ctx.proceed();
		final long endTimeMillis = System.currentTimeMillis();

		this.vothingMonitoringMBean.addMethodExecutionDuration(ctx.getTarget()
				.getClass().getSimpleName() + "."
				+ ctx.getMethod().getName(), endTimeMillis - startTimeMillis);

		return result;
	}


}
