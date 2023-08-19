package cn.edu.ctbu.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author 周肆淋
 * @version 1.0
 * @description: TODO
 * @date 2023/8/19 18:02
 */

/**ConcurrentModificationException 并发修改问题 ArrayList 并发条件下线程不安全
 * 解决方法
 * 1.使用Collection转换   Collections.synchronizedSet(new ArrayList<>());
 * 2.JUC类                new CopyOnWriteArraySet<>();
 */

//HashSet的底层是通过HashMap实现的 map存key,value为固定值.
public class SetTest {
    public static void main(String[] args) {
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
