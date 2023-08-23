package cn.edu.ctbu.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Acrossthewind
 * @version 1.0
 * @description: TODO
 * @date 2023/8/23 14:52
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /*
         * 收集7颗 龙珠召唤神龙
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功");
        });

        for (int i = 1 ; i <= 7; i++) {
            //线程无法获取到i
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集"+temp+"龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }



}
