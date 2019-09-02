package com.yang.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock解决等待唤醒问题
 * 
 * @author 爱不会绝迹
 *
 */
public class TestProviderAndConsumerForLock {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Productor p = new Productor(clerk);
		Consumer c = new Consumer(clerk);
		
		new Thread(p,"生产者A").start();
		new Thread(c,"消费者B").start();
		//虚假唤醒，不该被唤醒的线程被唤醒使用while(true)
		new Thread(p,"生产者C").start();
		new Thread(c,"消费者D").start();
	}
}

class Clerk{
	private int product = 0;
	
	//使用Lock代替syn关键字
	private Lock lock = new ReentrantLock();
	//使用condition对象实现线程之间的通讯，唤醒，等待
	private Condition condition = lock.newCondition();
	//进货
	public void get(){
		lock.lock();
		try{
			//while循环解决虚假唤醒问题
			while(product >= 1){
				System.out.println("产品已满");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}//else{
			System.out.println(Thread.currentThread().getName()+":"+ ++product);
			condition.signalAll();		
		}finally{
			lock.unlock();
		}
	}
	//卖货
	public void sale(){
		
		lock.lock();
		try{
			while(product<=0){
				System.out.println("缺货");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}//else{
			//去掉else解决唤醒不的问题
			System.out.println(Thread.currentThread().getName()+":" + --product);
			condition.signal();
		}finally{
			lock.unlock();
		}
		
	}
}


class Productor implements Runnable{
	
	private Clerk clerk;
	public Productor(Clerk clerk){
		this.clerk = clerk;
	}
	
	@Override
	public void run() {
		for(int i = 0;i < 20;i ++){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clerk.get();
		}
	}
	
}


class Consumer implements Runnable{
	private Clerk clerk;
	
	public Consumer(Clerk clerk){
		this.clerk = clerk;
	}
	
	@Override
	public void run() {
		for(int i = 0;i < 20;i ++){
			
			clerk.sale();
		}
	}
}	
