package com.yang.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 
 * 原子性问题
 * 
 * volatile不保原子性
 * @author 爱不会绝迹
 *
 */
public class TestAtomic {
	public static void main(String[] args) {
		AtomicDemo a1 = new AtomicDemo();
		/*
		 * 原子性问题发生多线程操作i++时会发生原子性问题
		 * i++是三步操作读，改，写
		 * 
		 */
		int i = 0;
		//是个线程同时访问demo类 
		while(i++<100000){
			new Thread(a1).start();		
		}
		
	}
}
class AtomicDemo implements Runnable{
	//volatile关键字不保证原子性
	//private volatile int number = 0;
	private AtomicInteger number = new AtomicInteger(0);
	/* 原子类
	 * 使用了volatile关键字和CAS算法（乐观锁的主要算法）
	 * 是硬件提供的对于并发操作的支持
	 * CAS : compare and swap 比较并交换
	 * CAS包含三个步骤
	 * 内存值：V
	 * 预估值：A
	 * 更新值：B
	 * 当且仅当 V==A 时，V=B，否则将不做任何操作
	 * （可能会引发ABA问题）
	 */
	public AtomicInteger getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number.set(number);
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"线程："+number.incrementAndGet());
	}
	
}
