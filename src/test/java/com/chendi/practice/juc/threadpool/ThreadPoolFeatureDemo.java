package com.chendi.practice.juc.threadpool;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author chendi
 * @Date 2018/10/19.
 * @descript 线程池执行featureTask
 */
public class ThreadPoolFeatureDemo {

    ExecutorService threadPool = null;

    @Before
    public void before() {
        threadPool = Executors.newSingleThreadExecutor();
    }

    @Test
    public void Test() {
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 100; i <= 1000; i += 100) {
            Future future = threadPool.submit(new CalcTask(i));
            futureList.add(future);
        }

        if (futureList.isEmpty())
            return;
        futureList.forEach((it) -> {
            try {
              System.out.println(it.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    class CalcTask implements Callable {
        private Integer num;

        public CalcTask(Integer num) {
            this.num = num;
        }

        @Override
        public Object call() throws Exception {
            return num;
        }
    }


}
