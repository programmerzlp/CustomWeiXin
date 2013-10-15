package org.zlp.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with eclipse
 * 
 * @Description: 线程发放器
 * @author: programmer.zlp@qq.com
 * @Date: 2013-6-25
 * @Time: 上午11:54:42
 * 
 */
public enum ThreadGenerate {

	INSTANCE {

		@Override
		public ScheduledExecutorService createScheduledExecutorService() {
			return scheduledExecutorService;
		}

		@Override
		public void destroyScheduledExecutorService() {
			scheduledExecutorService.shutdown();
			try {
				scheduledExecutorService.awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public ExecutorService createExecutorService() {
			return executorService;
		}

		@Override
		public void destroyExecutorService() {
			executorService.shutdown();
			try {
				executorService.awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	};

	protected final ScheduledExecutorService scheduledExecutorService = Executors
			.newScheduledThreadPool(10);

	protected final ExecutorService executorService = Executors.newCachedThreadPool();

	public abstract ScheduledExecutorService createScheduledExecutorService();

	public abstract void destroyScheduledExecutorService();

	public abstract ExecutorService createExecutorService();

	public abstract void destroyExecutorService();

}