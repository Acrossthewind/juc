package cn.edu.ctbu.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Acrossthewind
 * @version 1.0
 * @description: Callable
 * @date 2023/8/23 10:11
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread().start();

        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread); //适配类

        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();//结果会被缓存

        Object o = futureTask.get(); //获取Callable中的返回值
        //这个get方法可能会产生阻塞，把他放到最后，或则使用异步通信进行处理
        System.out.println(o);
    }
}
class MyThread implements Callable<Integer>{

    @Override
    public Integer call()  {
        System.out.println("call()"); //会打印几个call
        return 1024;
    }
}
