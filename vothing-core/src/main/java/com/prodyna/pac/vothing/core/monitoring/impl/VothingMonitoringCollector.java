package com.prodyna.pac.vothing.core.monitoring.impl;


import com.prodyna.pac.vothing.core.monitoring.PerformanceEntry;
import com.prodyna.pac.vothing.core.monitoring.VothingMonitoringMBean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by jrodan on 28/06/16.
 */
public class VothingMonitoringCollector implements VothingMonitoringMBean {

	/**
	 * Map containing the collected performance values for each method.
	 */
	private final Map<String, PerformanceEntry> methodExecutionDurationMap =
			new HashMap<String, PerformanceEntry>();

	@Override
	public void addMethodExecutionDuration(final String methodName, final long durationMs) {
		final String cleanedMethodName = methodName.replace("$Proxy$_$$_WeldSubclass", "");

		PerformanceEntry performanceEntry = this.methodExecutionDurationMap.get(cleanedMethodName);
		if (performanceEntry == null) {
			performanceEntry = new PerformanceEntry(cleanedMethodName, durationMs);
			this.methodExecutionDurationMap.put(cleanedMethodName, performanceEntry);
		} else {
			performanceEntry.add(durationMs);
		}
	}

	@Override
	public long getMethodExecutionDurationMinimum(final String methodName) {
		long result = 0;

		final PerformanceEntry performanceEntry = this.methodExecutionDurationMap.get(methodName);
		if (performanceEntry != null) {
			result = performanceEntry.getMinimumDuration();
		}

		return result;
	}

	@Override
	public long getMethodExecutionDurationAverage(final String methodName) {
		long result = 0;

		final PerformanceEntry performanceEntry = this.methodExecutionDurationMap.get(methodName);
		if (performanceEntry != null) {
			result = performanceEntry.getAverageDuration();
		}

		return result;
	}

	@Override
	public long getMethodExecutionDurationMaximum(final String methodName) {
		long result = 0;

		final PerformanceEntry performanceEntry = this.methodExecutionDurationMap.get(methodName);
		if (performanceEntry != null) {
			result = performanceEntry.getMaximumDuration();
		}

		return result;
	}

	@Override
	public void clearPerfomanceMonitoringData(final String methodName) {
		this.methodExecutionDurationMap.remove(methodName);
	}

	@Override
	public void clearAllPerfomanceMonitoringData() {
		this.methodExecutionDurationMap.clear();
	}

	@Override
	public long getMonitoredMethodsCount() {
		return this.methodExecutionDurationMap.size();
	}

	@Override
	public List<PerformanceEntry> getStatistics() {
		final List<PerformanceEntry> resultList = new LinkedList<PerformanceEntry>();
		resultList.addAll(this.methodExecutionDurationMap.values());
		return resultList;
	}

	@Override
	public PerformanceEntry getWorstByTotalDuration() {
		long worstTotal = 0;
		PerformanceEntry worstPerformanceEntry = null;
		for (final PerformanceEntry performanceEntry : this.methodExecutionDurationMap.values()) {
			if (performanceEntry.getTotalDuration() > worstTotal) {
				worstTotal = performanceEntry.getTotalDuration();
				worstPerformanceEntry = performanceEntry;
			}
		}

		return worstPerformanceEntry;
	}

	@Override
	public PerformanceEntry getWorstByAverage() {
		long worstAverage = 0;
		PerformanceEntry worstPerformanceEntry = null;
		for (final PerformanceEntry performanceEntry : this.methodExecutionDurationMap.values()) {
			if (performanceEntry.getAverageDuration() > worstAverage) {
				worstAverage = performanceEntry.getAverageDuration();
				worstPerformanceEntry = performanceEntry;
			}
		}

		return worstPerformanceEntry;
	}

	@Override
	public PerformanceEntry getWorstByCount() {
		long worstInvocationCount = 0;
		PerformanceEntry worstPerformanceEntry = null;
		for (final PerformanceEntry performanceEntry : this.methodExecutionDurationMap.values()) {
			if (performanceEntry.getInvocationCount() > worstInvocationCount) {
				worstInvocationCount = performanceEntry.getInvocationCount();
				worstPerformanceEntry = performanceEntry;
			}
		}

		return worstPerformanceEntry;
	}

	@Override
	public PerformanceEntry performanceForMethod(final String methodName) {
		return this.methodExecutionDurationMap.get(methodName);
	}

}
