package com.yang.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 * 读写锁
 * 
 * 写锁独占/读锁共享
 * @author 爱不会绝迹
 *
 */
public class TestReadWrite {
	public static void main(String[] args) {
		ReadWriteLockDemo rw = new ReadWriteLockDemo();
		new Thread(()->{
			rw.set((int)(Math.random()*101));
		},"write").start();
		
		for(int i = 0;i < 10;i ++){
			new Thread(()->{
				rw.get();
			},"read"+i).start();
		}
	}
}
class ReadWriteLockDemo{
	private int num = 0;
	private ReadWriteLock rwlock = new ReentrantReadWriteLock();
	public void get(){
		rwlock.readLock().lock();
		try{
			System.out.println(Thread.currentThread().getName()+":"+num);			
		}finally{
			rwlock.readLock().unlock();
		}
	}
	public void set(int num){
		rwlock.writeLock().lock();
		try{
			this.num = num;	
			System.out.println(Thread.currentThread().getName()+":"+num);
		}finally{
			rwlock.writeLock().unlock();
		}
	}
}