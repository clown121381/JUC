package com.yang.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 线程的调度
 * 
 * @author 爱不会绝迹
 *
 */
public class TestScheduledThreadPool {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 定时任务或者调度
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

		for (int i = 0; i < 10; i++) {
			ScheduledFuture<Integer> schedule = pool.schedule(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int num = (int) (Math.random() * 101);
					System.out.println(Thread.currentThread().getName());
					return num;
				}

			}, 1, TimeUnit.SECONDS);

			System.out.println(schedule.get());
		}
		pool.shutdown();
	}
}
