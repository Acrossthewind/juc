package cn.edu.ctbu.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周肆淋
 * @version 1.0
 * @description: TODO
 * @date 2023/8/19 15:17
 */
public class test1 {
    public static void main(String[] args) {
        TicketDemo ticketDemo = new TicketDemo();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticketDemo.sale();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticketDemo.sale();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticketDemo.sale();
            }
        },"C").start();
    }
}

class TicketDemo{
    Lock lock = new ReentrantLock();
    private int number = 30;
    public  void sale(){
        lock.lock();
        try{
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票,"+"剩余"+number+"张票");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }


    }
}
