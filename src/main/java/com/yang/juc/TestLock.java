package com.yang.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 1，同步代码
 * 2，同步方法，
 * 3.lock
 * lock上锁，unlock方法解锁（finally）
 * @author 爱不会绝迹
 */
public class TestLock {
	public static void main(String[] args) {
		Ticket t = new Ticket();
		new Thread(t,"1").start();
		new Thread(t,"2").start();
		new Thread(t,"3").start();
	}
}	


class Ticket implements Runnable{
	private int tick = 100;
	private Lock lock = new ReentrantLock();
	@Override
	public void run() {
			lock.lock();
			try{
				while(tick > 0){
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"号窗口完成售票，剩余票数为:"+ --tick);
				}
			}finally{
				lock.unlock();
			}
	}
	
}
