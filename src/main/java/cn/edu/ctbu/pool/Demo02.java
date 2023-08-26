package cn.edu.ctbu.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Acrossthewind
 * @version 1.0
 * @description: TODO
 * @date 2023/8/24 18:08
 */
public class Demo02 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy() //1.抛出策略 2.哪来的去哪里 3.队列满了，丢到任务，不会抛异常 4.队列满了，尝试和最早的竞争，不会抛出异常
                );
        //最大承载：队列 加 max
        //超出最大：RejectedExecutionException
        try {
            for (int i = 0; i < 9; i++) {
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
