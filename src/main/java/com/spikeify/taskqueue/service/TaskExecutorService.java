package com.spikeify.taskqueue.service;

import com.spikeify.taskqueue.TaskContext;
import com.spikeify.taskqueue.TaskResult;

/**
 * Takes care of job execution, utilizes TaskQueueService to get next job
 * <p>
 * 1. retrieves next job from queue and locks it for other executors
 * 2. executes job - takes care of failover (job taking to long and other exceptions)
 * 3. unlocks job when execution finishes
 */
public interface TaskExecutorService {

	/**
	 * Executes next job
	 *
	 * @param context job context
	 * @return job result or throws exception
	 */
	TaskResult execute(TaskContext context);

	/**
	 * @return true if job is running, false if not
	 */
	boolean isRunning();

	/**
	 * performs clean up after thread interruption (if any) to avoid locking
	 */
	void reset();
}
