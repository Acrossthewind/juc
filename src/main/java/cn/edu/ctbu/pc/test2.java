package cn.edu.ctbu.pc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周肆淋
 * @version 1.0
 * @description: TODO
 * @date 2023/8/19 16:42
 */

//新版生产者消费者问题
public class test2 {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(()->{
            for (int i = 0; i <10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i <10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i <10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i <10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"D").start();
    }
}
class Data2{
    private int number = 0;
    Lock lock = new ReentrantLock();
    //condition
    Condition condition = lock.newCondition();

    //+1
    public void increment() throws InterruptedException {
        //如果写成if判断，两条以上的线程将会出现问题。
        //出现虚假唤醒，所以为了避免虚假唤醒问题，我们需要使用while
        lock.lock();
        try{
            while(number!=0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"->"+number);
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

    //-1
    public void decrement() throws InterruptedException {
        lock.lock();
        try{
            while(number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"->"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

