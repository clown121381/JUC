package com.yang.juc;

import java.util.concurrent.*;

public class TestBlockingQueue {
    static void test1(){

        ThreadPoolExecutor te = null;
        try{
            te = new ThreadPoolExecutor(
                    3, //核心常驻线程数
                    5, //最大线程数
                    2L, //空闲线程的最大存活时间
                    TimeUnit.SECONDS, //存活时间的单位
                    new LinkedBlockingDeque<>(3), //等待队列
                    Executors.defaultThreadFactory(), //线程生产的工厂
                    new ThreadPoolExecutor.AbortPolicy()); //拒绝策略
            for(int i = 0;i < 9;i ++){
                te.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }

        }finally{
            te.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
       // test1();
    }
}
