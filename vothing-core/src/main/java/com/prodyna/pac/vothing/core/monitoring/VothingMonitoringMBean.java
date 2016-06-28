package com.prodyna.pac.vothing.core.monitoring;

import java.util.List;

/**
 * Created by jrodan on 28/06/16.
 */
public interface VothingMonitoringMBean {


	/**
	 * @return
	 */
	long getMonitoredMethodsCount();

	/**
	 * @return
	 */
	List<PerformanceEntry> getStatistics();

	/**
	 * @return
	 */
	PerformanceEntry getWorstByTotalDuration();

	/**
	 * @return
	 */
	PerformanceEntry getWorstByAverage();

	/**
	 * @return The performance entry for the method with highest execution count.
	 */
	PerformanceEntry getWorstByCount();

	/**
	 * @param methodName The name of the method to get the performance entry for.
	 * @return The performance entry for the given method.
	 */
	PerformanceEntry performanceForMethod(String methodName);

	/**
	 * Retrieves the minimal execution duration for given method.
	 *
	 * @param methodName Method to retrieve the minimal execution duration for.
	 * @return Minimal execution duration for given method.
	 */
	long getMethodExecutionDurationMinimum(String methodName);

	/**
	 * Retrieves the average execution duration for given method.
	 *
	 * @param methodName Method to retrieve the average execution duration for.
	 * @return Average execution duration for given method.
	 */
	long getMethodExecutionDurationAverage(String methodName);

	/**
	 * Retrieves the maximal execution duration for given method.
	 *
	 * @param methodName Method to retrieve the maximal execution duration for.
	 * @return Maximal execution duration for given method.
	 */
	long getMethodExecutionDurationMaximum(String methodName);

	/**
	 * Clears the tracked performance monitoring data for given method.
	 *
	 * @param methodName Method to clear the monitoring data for.
	 */
	void clearPerfomanceMonitoringData(String methodName);

	/**
	 * Clears all tracked performance monitoring data.
	 */
	void clearAllPerfomanceMonitoringData();

	/**
	 * @param methodName Method to save the execution duration for.
	 * @param duration   Execution duration for given method in milliseconds.
	 */
	void addMethodExecutionDuration(String methodName, long duration);
}
