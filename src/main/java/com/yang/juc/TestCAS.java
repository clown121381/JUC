package com.yang.juc;
/**
 * 模拟CAS算法 测试在TestCAS01
 * 
 * @author 爱不会绝迹
 *
 */
public class TestCAS {
	private int value;

	public synchronized int getValue() {
		return this.value;
	}
	
	public synchronized int compareAndSwap(int exceptValue,int newValue){
		int oldValue = value;
		if(oldValue==exceptValue){
			this.value = newValue;
		}
		return oldValue;
	}
	
	public synchronized boolean compareAndSet(int exceptValue,int newValue){
		return exceptValue == compareAndSwap(exceptValue, newValue);
	}
}