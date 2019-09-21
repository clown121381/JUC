package demos;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 强软弱虚四大引用
 */
public class RefenceDemo {
    public static void main(String[] args) {
        //强引用
        Object obj = new Object();

        //软引用只有当内存不够的时候才会被回收
        SoftReference<Object> soft = new SoftReference<>(obj);


        //弱引用一定会被回收不论内存够不够 ，在高速缓存中常用
        WeakReference<Object> weak = new WeakReference<>(obj);
        //System.out.println(weak.get());


        //引用队列
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        //虚引用不存在的引用幽灵引用用来标记对象的回收过程，监控对象的回收状态
        PhantomReference<Object> phantom = new PhantomReference<>(obj,queue);
        //phantom.get() 为null 虚引用不会决定对象的生命周期，回收的时候会被放到引用队列中


        Object o1 = new Object();
        ReferenceQueue<Object> queue1 = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,queue1);
        //在gc的时候会被装入引用队列
        o1 = null;
        System.gc();

        System.out.println(weakReference.get());
        System.out.println(queue1.poll());



    }
}
