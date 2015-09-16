package com.spikeify.taskqueue.service;

import com.spikeify.taskqueue.entities.QueueInfo;

import java.util.List;

public interface TaskQueueManager {

	/**
	 * Registers queue to be monitored
	 * @param queueName name of queue
	 * @return registered queue info
	 */
	QueueInfo register(String queueName);

	/**
	 * Lists queues registered
	 * @param active - true list active queues, false - list disabled queues, null - list all
	 * @return list of registered queues
	 */
	List<QueueInfo> list(Boolean active);

	/**
	 * Removes queue from monitoring
	 * @param queueName to be removed
	 */
	void unregister(String queueName);

	/**
	 * Starts queues ... (on given JVM)
	 * should be called only once per thread,
	 * if called multiple times then threads are terminated and restated (acts as restart)
	 *
	 * @param queueNames names to be started or empty to start all enabled queues
	 * @throws InterruptedException
	 */
	void start(String... queueNames) throws InterruptedException;

	/**
	 * Stops queues - stops all running tasks/threads (on given JVM)
	 * @param queueNames to be stopped or empty to stop all enabled queues
	 * @throws InterruptedException
	 */
	void stop(String... queueNames) throws InterruptedException;

	/**
	 * Enables queue
	 * @param queueName - enables queue to be run
	 * @return enabled queue info
	 */
	QueueInfo enable(String queueName);

	/**
	 * Disables queue - stops queue if running
	 * @param queueName - disables queue from running
	 * @return disabled queue info
	 */
	QueueInfo disable(String queueName);

	/**
	 * Should be called on regular basis from each machine running queues
	 * takes care that if one instance has started/stopped a queue it is also started/stopped on other machines
	 *
	 * Best invoked from a cron job or similar
	 * @throws InterruptedException
	 */
	void check() throws InterruptedException;

}
