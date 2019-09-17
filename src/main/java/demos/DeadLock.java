package demos;

import java.util.concurrent.TimeUnit;

/**
 * 写一个死锁第一种写法
 */
public class DeadLock {
    public static void main(String[] args) {
        String a = "a";
        String b = "b";

        new Thread(new Resource(a,b),"ThreadA").start();
        new Thread(new Resource(b,a),"ThreadB").start();
    }
}

class Resource implements Runnable{

    String a;
    String b;

    public Resource(String a,String b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (a){
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
