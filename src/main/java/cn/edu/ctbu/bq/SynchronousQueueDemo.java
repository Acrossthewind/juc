package cn.edu.ctbu.bq;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Acrossthewind
 * @version 1.0
 * @description: 同步队列 进去一个元素，只有这个元素出来才能再进入
 * @date 2023/8/24 16:59
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"put 2");
                synchronousQueue.put("2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);

                System.out.println(synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);

                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t2").start();
    }
}
