package com.yang.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池
 * java.util.concurrent.Executor 线程的使用个调度根接口
 * 			|--ExecutorService 子接口：线程池的主要接口
 * 				|---ThreadPoolExecutor 实现类
 * 				|---ScheduleExecutor   用于线程调度的子接口
 * 					|---ScheduleThreadPoolExecutor 线程池线程调度接口
 * Executors 工具类
 * ExecutorService newFixedThreadPool(); 固定大小
 * ExecutorService newCachedThreadPool(); 缓存大小线程池，大小不固定
 * ExecutorService newSingleThreadPool(); 一个线程的线程池
 * 
 * ScheduleThreadPoolExecutor newScheduleThreadPool(); 固定大小的线程池，可以创建和执行延迟任务
 * @author 爱不会绝迹
 *
 */
public class TestThreadPool {
	public static void main(String[] args) {
		ThreadPoolDemo td = new ThreadPoolDemo();
		
		//创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		//为线程池中的线程提交任务
		pool.submit(td);			
		
		
		Future<Integer> fu = pool.submit(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				int sum = 0;
				int index = 0;
				while(++index <= 100){
					sum+=index;
				}
				return sum;
			}
			
		});
		try{
			System.out.println(fu.get());
		} catch(InterruptedException e){
			
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭线程池
		pool.shutdown();
	}
}

class ThreadPoolDemo implements Runnable{
	private int i = 0;

	@Override
	public void run() {
		while(i <= 100){
			System.out.println(Thread.currentThread().getName()+":\t"+i++);
		}
	}
	
}
