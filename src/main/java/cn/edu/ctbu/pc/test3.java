package cn.edu.ctbu.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周肆淋
 * @version 1.0
 * @description: TODO
 * @date 2023/8/19 16:58
 */

//新版精准唤醒 生产者消费者问题
public class test3 {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 0; i <10; i++) {
                try {
                    data.print1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i <10; i++) {
                try {
                    data.print2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i <10; i++) {
                try {
                    data.print3();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"C").start();

    }
}
class Data3{
    private int number = 1;
    Lock lock = new ReentrantLock();
    //condition
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    //+1
    public void print1() throws InterruptedException {
        //如果写成if判断，两条以上的线程将会出现问题。
        //出现虚假唤醒，所以为了避免虚假唤醒问题，我们需要使用while
        lock.lock();
        try{
            while(number!=1){
                condition1.await();
            }
            number=2;
            System.out.println(Thread.currentThread().getName()+"->"+"AAAAA");
            //精准唤醒
            condition2.signal();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

    //-1
    public void print2() throws InterruptedException {
        lock.lock();
        try{
            while(number!=2){
                condition2.await();
            }
            number=3;
            System.out.println(Thread.currentThread().getName()+"->"+"BBBBB");
            condition3.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print3() throws InterruptedException {
        lock.lock();
        try{
            while(number!=3){
                condition3.await();
            }
            number=1;
            System.out.println(Thread.currentThread().getName()+"->"+"CCCCC");
            condition1.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
