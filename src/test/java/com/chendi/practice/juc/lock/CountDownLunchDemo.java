package com.chendi.practice.juc.lock;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Author chendi
 * @Date 2018/8/24.
 * @descript
 */
public class CountDownLunchDemo {

    public class ThreadDemo implements Runnable {

        private CountDownLatch latch;

        private long sleepTime;

        public ThreadDemo(CountDownLatch latch, long sleepTime) {
            this.latch = latch;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime);
                latch.countDown();
                System.out.println(Thread.currentThread().getName() + "已执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void CountDownLatchDemo() {
        CountDownLatch latch = new CountDownLatch(3);
        try {
            ThreadDemo threadDemo = new ThreadDemo(latch, 1000L);
            ThreadDemo threadDemo2 = new ThreadDemo(latch, 2000L);
            ThreadDemo threadDemo3 = new ThreadDemo(latch, 3000L);

            Thread t1 = new Thread(threadDemo, "demo-1");
            Thread t2 = new Thread(threadDemo2, "demo-2");
            Thread t3 = new Thread(threadDemo3, "demo-3");

            t1.start();
            t2.start();
            t3.start();

            latch.await();
            System.out.println("测试闭环");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
