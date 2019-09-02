package com.yang.juc;
/**
 * 多个线程操作共享数据时，数据彼此不可见
 * volatile 内存可见性的问题
 * @author 爱不会绝迹
 *
 */
public class TestVolatile {
	public static void main(String[] args) {
		ThreadDemo d1 = new ThreadDemo();
		Thread t1 = new Thread(d1);
		t1.start();
		while(true){
			if(d1.isFlag()){
				/*
				 * 下滑线不会被打印，因为内存可见性的问题
				 */
				System.out.println("___________________");
				break;
			}
		}
	}
}
class ThreadDemo implements Runnable{

	//private boolean flag = false;
	/*
	 * 加上volatile关键字以后可以保证内存中ThreadDemo 
	 * 线程 和main线程对于flag的可见性
	 */
	private volatile boolean flag;
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		flag = true;
		
		System.out.println("flag is "+flag);
	}
}