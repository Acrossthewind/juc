package cn.edu.ctbu.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 周肆淋
 * @version 1.0
 * @description: TODO
 * @date 2023/8/19 17:47
 */

/**ConcurrentModificationException 并发修改问题 ArrayList 并发条件下线程不安全
 * 解决方法
 * 1.使用Vector（不推荐，用synchronized实现，性能下降） new Vector<>();
 * 2.使用Collection转换   Collections.synchronizedList(new ArrayList<>());
 * 3.JUC类                new CopyOnWriteArrayList<>();
 *
   */
public class ListTest {
    public static void main(String[] args) {
        //写入时复制
        //在写入时避免覆盖
        List<Integer> list1 = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
