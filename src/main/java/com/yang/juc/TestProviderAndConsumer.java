package com.yang.juc;
/**
 * 生产者消费者
 * 
 * 等待唤醒机制，和虚假唤醒问题
 * 
 * @author 爱不会绝迹
 *
 */
public class TestProviderAndConsumer {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Productor p = new Productor(clerk);
		Consumer c = new Consumer(clerk);
		
		new Thread(p,"生产者A").start();
		new Thread(c,"消费者B").start();
		//虚假唤醒，不该被唤醒的线程被唤醒使用while(true)可以解决虚假唤醒问题
		new Thread(p,"生产者C").start();
		new Thread(c,"消费者D").start();
	}
}

class Clerk{
	private int product = 0;
	//进货
	public synchronized void get(){
		//while循环解决虚假唤醒问题
		while(product >= 1){
			System.out.println("产品已满");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//else{
		System.out.println(Thread.currentThread().getName()+":"+ ++product);
		this.notifyAll();
	}
	//卖货
	public synchronized void sale(){
		while(product<=0){
			System.out.println("缺货");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//else{
		//去掉else解决左后一个线程唤醒不的问题
		System.out.println(Thread.currentThread().getName()+":" + --product);
		this.notifyAll();
		
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