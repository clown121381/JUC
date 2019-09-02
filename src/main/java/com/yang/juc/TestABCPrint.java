package com.yang.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程交替打印ABC
 * @author 爱不会绝迹
 *
 */
public class TestABCPrint {
	public static void main(String[] args) {
		Demo d = new Demo();
		new Thread(()->{
			for(int i = 0;i < 20;i ++){
				d.loopA(i);
			}
		},"A").start();
		
		new Thread(()->{
			for(int i = 0;i < 20;i ++){
				d.loopB(i);
			}
		},"B").start();
		
		new Thread(()->{
			for(int i = 0;i < 20;i ++){
				d.loopC(i);
			}
		},"C").start();
	}
}
class Demo {
	//当前执行线程的标记
	private int num = 1;
	
	private Lock lock = new ReentrantLock();
	private Condition con1 = lock.newCondition();
	private Condition con2 = lock.newCondition();
	private Condition con3 = lock.newCondition();
	
	public void loopA(int loop){
		lock.lock();
		try{
			if(num != 1){
				con1.await();
			}
			for(int i = 1;i <=5 ;i ++){
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+loop);
			}
			
			//唤醒
			num = 2;
			con2.signal();
			
		}catch(InterruptedException e){
		
		}finally{
			lock.unlock();
		}
	}
	public void loopB(int loop){
		lock.lock();
		try{
			if(num != 2){
				con2.await();
			}
			for(int i = 1;i <=5 ;i ++){
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+loop);
			}
			
			//唤醒
			num = 3;
			con3.signal();
			
		}catch(InterruptedException e){
		
		}finally{
			lock.unlock();
		}
	}
	public void loopC(int loop){
		lock.lock();
		try{
			if(num != 3){
				con3.await();
			}
			for(int i = 1;i <=5 ;i ++){
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+loop);
			}
			
			//唤醒
			num = 1;
			con1.signal();
			
		}catch(InterruptedException e){
		
		}finally{
			lock.unlock();
		}
	}
}