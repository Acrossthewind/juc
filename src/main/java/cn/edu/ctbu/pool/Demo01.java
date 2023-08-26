package cn.edu.ctbu.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Acrossthewind
 * @version 1.0
 * @description: TODO
 * @date 2023/8/24 17:37
 */

//Executors 工具类、三大方法
//使用了线程池之后，使用线程池来创建线程
public class Demo01 {
    public static void main(String[] args) {
        //三大方法 底层源码都是使用的 new ThreadPoolExecutor()
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();//单个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定的线程池的大小
        ExecutorService threadPool2 = Executors.newCachedThreadPool();//可伸缩的线程池
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }

    }
}
