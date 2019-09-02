package com.yang.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * 实现callable接口创建线程，相较于实现runnable接口的方式，
 * 可以有返回值含有跑出异常
 * 
 * 
 * @author 爱不会绝迹
 *
 */
public class TestCallable {
		
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableDemo cd = new CallableDemo();
		// 执行callable的方式需要futuretask的支持使用
		FutureTask<Integer> ft = new FutureTask<>(cd);		
		new Thread(ft).start();
		
		System.out.println(ft.get());
	}
}

class CallableDemo implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i = 0;i <= 100;i ++){
			sum += i;
		}
		return sum;
	}
	
}
