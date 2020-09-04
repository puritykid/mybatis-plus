package com.example.mybatisplus.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.locks.LockSupport;

@RunWith(SpringJUnit4ClassRunner.class)
public class Demo2 {

    public static Object baozidian = null;

    @Test
    public void waitNotifyTest() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            if (baozidian == null) {
                try {
                    Thread.sleep(2000L);
                    System.out.println("1、进入等待");
                    LockSupport.park();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("买到包子回家");

        });
        thread1.start();

        Thread.sleep(3000L);
        baozidian = new Object();
        // 给指定的线程发送通知
        LockSupport.unpark(thread1);
        System.out.println("通知消费者");
        Thread.sleep(10000L);
    }
}
