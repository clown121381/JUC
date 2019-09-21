package demos;

import java.io.IOException;

public class Hello {
    /**
     * 注释jvm参数及其配置
     *  -XX:+PrintGCDetails   打印垃圾回收的细节信息
     *  -XX:+PrintFlagsInitial  打印vm虚拟机参数初始化
     *  -XX:+PrintFlagsFinal    打印vm虚拟机参数最终
     *  -XX:+PrintCommandLineFlags 打印vm虚拟机参数
     *
     * jps --------------------  ps -ed
     * jstack <pid>
     * jinfo [option](-flag + 参数名 或者-flags) <pid>(jps -l 查询出pid)
     * -Xms                 初始大小堆内存，默认为物理内存的1/64，等价于-XX:InitialHeapSize
     * -Xmx                 最大分配堆内存，默认为物理内存的1/64，等价于-XX：MaxHeapSize
     * -Xss                 设置单个线程栈的大小，一般默认为512K~1024K，等价于-XX:ThreadStacksize
     * -Xmn                 设置新生代大小
     * -XX:MetaspaceSize    设置元空间大小(理论上配置的稍微大一些默认为22m)，直接使用本地物理内存
     * -XX:SurvivoRatio     新生代中伊甸园区和幸存者区的比例8（8：1：1）4（4：1：1）
     * -XX:NewRatio         新生代的大小和老年代大小的比例2
     * -XX:MaxTenuringThreshold  新生代对象存活进入老年代的次数默认15
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        System.out.println("hello world");
      //  System.in.read();
    }
}
