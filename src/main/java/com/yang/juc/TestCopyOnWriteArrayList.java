package com.yang.juc;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发容器
 * 
 * @author 爱不会绝迹
 *
 */
public class TestCopyOnWriteArrayList {
	public static void main(String[] args) {
		HelloThread ht = new HelloThread();
		Thread t = new Thread(ht);
		t.start();
	}
}
class HelloThread implements Runnable{

	
	//当写入数据时在底层复制一份在添加，（效率低）所以添加操作多时不适合使用该容器，适合并发的迭代
	private static List<String> list = new CopyOnWriteArrayList<String>();
	/*
	 * Exception in thread "Thread-0" 
	java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
	at java.util.ArrayList$Itr.next(ArrayList.java:859)
	at com.yang.juc.HelloThread.run(TestCopyOnWriteArrayList.java:38)
	at java.lang.Thread.run(Thread.java:748)
	会发生并发修改异常
	 */
//	private static List<String> list = Collections.synchronizedList(new ArrayList<>());
	
	static{
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
	}
	
	@Override
	public void run() {
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
			list.add("A");
		}
	}
	
}