package com.yang.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 只有早完其他的线程的所有操作时闭锁才会执行
 * 计算10个线程并发执行完的时间
 * @author 爱不会绝迹
 *
 */
public class TestCountDownLatch {
	public static void main(String[] args) {
		
		final CountDownLatch latch = new CountDownLatch(10);
		
		LatchDemo ld = new LatchDemo(latch);
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			new Thread(ld).start();
		}
		try{
			latch.await();
		}catch(InterruptedException e){
			
		}
		
		long end = System.currentTimeMillis();
		System.out.println("十个线程执行完的时间是："+ (end-start));
	}
}

class LatchDemo implements Runnable{
	
	private CountDownLatch countDownLacth;
	
	public LatchDemo(CountDownLatch countDownLatch){
		this.countDownLacth = countDownLatch;
	}
	
	
	@Override
	public void run() {
		synchronized (this) {
			try{
				for(int i = 0;i < 50;i ++){
					if(i % 2 == 0){
						System.out.println(i);
					}
				}
			}finally{
				/*
				 * 内部维护一个计数器
				 */
				countDownLacth.countDown();			
			}			
		}
	}
	
}


