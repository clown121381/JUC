package demos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个死锁第二种写法
 */
public class DeadLock1 {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        new Thread(new Resource1(lock1,lock2),"A").start();
        new Thread(new Resource1(lock2,lock1),"B").start();

    }
}
class Resource1 implements Runnable{

    Lock lock1;
    Lock lock2;

    public Resource1(Lock lock1,Lock lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        lock1.lock();
        try{

            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2);
            lock2.lock();
            try{
                System.out.println(Thread.currentThread().getName());
            }finally {
                lock2.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
        }
    }
}