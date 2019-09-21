package demos;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * StackOverFlowError
 * OutOfMemoryError: Java Heap Space
 *
 * OutOfMemoryError: GC overhead limit exceeded
 *  利用-Xmx10m -Xms10m -XX:MaxDirectMemorySize=5m模拟
 *  GC回收时间过长：超过98%的时间用来做垃圾回收但是却回收了
 *  不到2%的堆内存连续多次回收都发生了此类情况cpu100%而gc却没有任何的成果
 *
 * OutOfMemoryError: Direct Buffer Memory
 *  jvm参数 -XX:MaxDirectMemorySize=5m
 *  nio  netty
 *  写nio程序时经常使用ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）与缓冲区（Buffer）的IO技术
 *  它可以使用native函数库直接分配堆外内存，然后通过一个存储在java堆里面的DirectByteBuffer对象作为这块内存的
 *  引用进行操作，这养可以使一些场景中显著的提高性能，因为避免了在java堆中和native堆中进行来回的复制数据
 *
 *  ByteBuffer.allocate（capability）第一种方式分配JVM内存，属于GC管辖的范围，需要拷贝所以速度较慢
 *  ByteBuffer. allocateDirect(capability) 第二种方式分配本地os内存不属于gc的管线范围，由于不需要拷贝数以速度较快
 *
 *  但是如果不断地分配本地内存的话，堆内存很少使用，那么jvm虚拟机就不需要执行本地gc，DirectByteBuffer对象们就不会被回收
 *  这是堆内存充足但是本地内存可能一斤使用光了，在尝试分配本地内存的话就可能出现oom异常
 * OutOfMemoryError: Unable to create new native thread
 *  不能再创建新的本地线程
 *  高并发请求服务器是，经常出现该异常
 *  准确地讲该异常于对应的平有关
 *  导致原因：
 *  1 应用创建了太多的线程，一个应用进程创建多个线程以后，超过系统的承载极限
 *  2 服务器斌不准许你的应用程序创建这么多的线程，linux系统默认准许单个进程可以创建的线程数是1024
 *     你的应用超过这个数量就会报该异常
 *  解决办法：
 *      1 想办法降低应用创建线程的数量
 *      2 修改服务器的配置
 *
 * OutOfMemoryError: MetaSpace
 *  -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *  元空间大小
 *
 */
public class OOMDemo {
    public static void main(String[] args) {

        //java.lang.StackOverflowError   -->错误
        //main(new String[]{"asd", "asd"});

        //java.lang.OutOfMemoryError: Java heap space
        // byte[] b = new byte[80*1024*1024];


        //java.lang.OutOfMemoryError: GC overhead limit exceeded
//        int i = 0;
//        List<String> list = new ArrayList<>();
//        try{
//            while (true){
//                list.add(String.valueOf(++i).intern());
//            }
//        }catch (Exception e){
//            System.out.println("**************"+i);
//            e.printStackTrace();
//            throw e;
//        }


        //java.lang.OutOfMemoryError: Direct buffer memory
        //1799.5MB 大约四分之一
//        System.out.println("配置的maxDirectMemory："+(sun.misc.VM.maxDirectMemory() /(double)1024/1024)+"MB");
//        ByteBuffer buffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);


        //java.lang.OutOfMemoryError: unable to create new native thread
//        while (true){
//            Thread t1 = new Thread(()->{
//                try {
//                    Thread.sleep(Integer.MAX_VALUE);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            t1.start();
//        }
        // t1.start(); 二次启动会报异常 java.lang.IllegalThreadStateException
    }
}