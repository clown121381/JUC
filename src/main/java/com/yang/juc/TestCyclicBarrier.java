package com.yang.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier implements Runnable{
    CyclicBarrier cyclicBarrier;

    public TestCyclicBarrier(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }
    public TestCyclicBarrier(){}
    @Override
    public void run() {

        for(int i = 0;i < 10;i ++){
            new TestCyclicBarrier();
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class TestCyclicBarrierDemo{
    public static void main(String[] args) {

        CyclicBarrier cb = new CyclicBarrier(1000);
        TestCyclicBarrier tb = new TestCyclicBarrier(cb);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            new Thread(tb).start();
        }
        long end = System.currentTimeMillis();

        System.out.println(end-start);
    }
}

