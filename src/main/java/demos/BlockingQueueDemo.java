package demos;

import java.util.concurrent.*;

/**
 * 阻塞队列
 *
 * 三组api
 *
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //底层链表封装的默认最大值Integer.MAX_VALUE
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);
        //底层数组创建时必须指定长度
        BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(10);
        //长度为1的阻塞队列
        BlockingQueue<String> blockingQueue2 = new SynchronousQueue<>();

        /*
          第一组抛出异常
         */
        blockingQueue.add("a");
        blockingQueue.remove();

        /*
          第二组true/false
         */
        blockingQueue.offer("a");
        blockingQueue.poll();
        /*
          第三组等待阻塞
         */
        blockingQueue.put("a");
        blockingQueue.take();

        /*
          第四组设置超时时间，时间内阻塞
         */
        blockingQueue.offer("a",1L, TimeUnit.SECONDS);
        blockingQueue.poll(2L,TimeUnit.SECONDS);

        /**********************************************************************/
        /*
         线程池七大参数，生产中不用jdk自带的Executors工具类生成线程池
         以为默认的阻塞队列（等候区）的值为默认的Integer.MAX_VALUE
         高并发时值过大会发生oom异常场，通常会自己组装线程池
         */
        ThreadPoolExecutor te = null;
        try{
            te = new ThreadPoolExecutor(
                    3, //1、线程池的常驻线程数
                    5, //线程池的最大线程数
                    2L, //线程池中非常驻空闲线程的最大存活时间
                    TimeUnit.SECONDS, //存活时间的单位
                    new LinkedBlockingDeque<>(3), //等待队列是一个阻塞队列
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

        //详情见源码
    }
}
