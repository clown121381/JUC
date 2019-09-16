package com.yang.juc;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    public static void main(String[] args) {
        //类似信号灯
        Semaphore semaphore = new Semaphore(3);

        for(int i = 0;i < 10; i ++){
            new Thread(()->{
                try{
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"强盗车位");
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            },String.valueOf(i)+"线程").start();
        }
    }
}
