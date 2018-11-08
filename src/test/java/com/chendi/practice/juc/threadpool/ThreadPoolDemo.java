package com.chendi.practice.juc.threadpool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author chendi
 * @Date 2018/8/24.
 * @descript
 */
public class ThreadPoolDemo {


    private final static int coreSize = 2;

    private final static int maxSize = 5;

    private final static long timeOut = 6000l;

    private BlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>(1024);

    @Test
    public void TestThreadPool(){

        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize,maxSize,timeOut, TimeUnit.MICROSECONDS,blockingDeque);

        System.out.println(Integer.toBinaryString(3));
    }




}
