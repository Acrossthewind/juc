package cn.edu.ctbu.demo02;

/**
 * @author 周肆淋
 * @version 1.0
 * @description: TODO
 * @date 2023/8/19 16:12
 */

//生产者消费者问题
public class test1 {
    public static void main(String[] args) {
        Data data = new Data();
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
    }
}
class Data{
    private int number = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        if(number!=0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"->"+number);
        this.notify();
    }

    //-1
    public synchronized void decrement() throws InterruptedException {
        if(number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"->"+number);
        this.notify();
    }
}
