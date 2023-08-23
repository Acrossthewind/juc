package cn.edu.ctbu.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Acrossthewind
 * @version 1.0
 * @description: TODO
 * @date 2023/8/23 15:00
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //线程数量：停车位 限流
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                //acquire 得到
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    //release 释放
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
