package com.yang.juc;

import com.yang.juc.TestCAS;

public class TestCAS01 {
	public static void main(String[] args){
		final TestCAS cas = new TestCAS();
		for(int i = 0;i < 10;i ++){
			new Thread(()->{
				int oldValue = cas.getValue();
				try {
					Thread.sleep(200);
				} catch (Exception e) {
					e.printStackTrace();
				}
				boolean b = cas.compareAndSet(oldValue, (int)Math.random()*100);
				System.out.println(b);
			}).start();
		}
	}
}
