package cn.edu.ctbu.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 周肆淋
 * @version 1.0
 * @description: TODO
 * @date 2023/8/19 18:07
 */

/**ConcurrentModificationException 并发修改问题 HashMap 并发条件下线程不安全
 * 解决方法
 * 1.使用Collection转换   Collections.synchronizedMap(new HashMap<>());
 * 2.JUC类                new ConcurrentHashMap<>();;
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
