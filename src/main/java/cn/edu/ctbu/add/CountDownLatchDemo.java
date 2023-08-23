package cn.edu.ctbu.add;

import java.util.concurrent.CountDownLatch;

/**
 * @author Acrossthewind
 * @version 1.0
 * @description: TODO
 * @date 2023/8/23 14:43
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //设置总数为6
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"GO OUT");
                //计数器减一
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();//等待计数器归零，才会向下执行
        System.out.println("Close Door");
    }
}
