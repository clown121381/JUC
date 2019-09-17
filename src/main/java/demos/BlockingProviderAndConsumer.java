package demos;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列版的生产者消费者
 */
public class BlockingProviderAndConsumer {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(5);

        new Thread(new Provider(blockingQueue)).start();
        new Thread(new Consumer(blockingQueue)).start();
    }
}
class Provider implements Runnable{

    private BlockingQueue<String> blockingQueue;

    public Provider(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                blockingQueue.offer("AAAAAAA",2L,TimeUnit.SECONDS);
                System.out.println("生产了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer implements Runnable{

    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                String b = blockingQueue.poll(2L,TimeUnit.SECONDS);
                if(b == null){
                    return;
                }else{
                    System.out.println("消费了"+b);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}