package cn.edu.ctbu.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周肆淋
 * @version 1.0
 * @description: TODO
 * @date 2023/8/19 15:46
 */
public class test2 {
    public static void main(String[] args) {
        TicketDemo2 ticketDemo = new TicketDemo2();

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
class TicketDemo2{
    private int number = 30;
    public synchronized void sale(){
        if(number > 0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票,"+"剩余"+number+"张票");
        }
    }
}