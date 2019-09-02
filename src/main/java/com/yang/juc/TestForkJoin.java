package com.yang.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 
 * jdk1.7线程的分之合并框架
 * 
 * @author 爱不会绝迹
 *
 */
public class TestForkJoin {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		
		ForkJoinTask<Long> ft = new ForkJoinDemo(0L,1000000000L);
		Long sum = pool.invoke(ft);
		System.out.println(sum);
	}
}
class ForkJoinDemo extends RecursiveTask<Long>{

	private static final long serialVersionUID = 1L;

	private long start;
	private long end;
	private final long THURSHOLD = 10000L;
	
	public ForkJoinDemo(long start,long end){
		this.start = start;
		this.end = end;
	}
	
	
	@Override
	protected Long compute() {
		long len = end - start;
		if(len <= THURSHOLD){			
			long sum = 0;
			
			for(long i = start;i <= end;i ++){
				sum += i;
			}
			return sum;
		}else{
			long middle = (start+end)/2;
			ForkJoinDemo left = new ForkJoinDemo(start, middle);
			left.fork();
			ForkJoinDemo right = new ForkJoinDemo(middle+1,end);
			right.fork();
			
			return left.join() + right.join();
		}
	}
	
}
