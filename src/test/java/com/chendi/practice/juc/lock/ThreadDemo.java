package com.chendi.practice.juc.lock;

import org.junit.Test;

/**
 * @Author chendi
 * @Date 2018/10/31.
 * @descript
 */
public class ThreadDemo {




    public static void main (String [] args){
        ThreadDemo.DemoThread A = new ThreadDemo().new DemoThread(1000L);
        ThreadDemo.DemoThread B = new ThreadDemo().new DemoThread(1L);
        Thread a = new Thread(A,"线程---A");
        Thread b = new Thread(B,"线程---B");
        try{
            a.start();
            b.start();
            b.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public  class DemoThread  implements  Runnable{

        private Long timeOut;

        public DemoThread(Long timeOut) {
            this.timeOut = timeOut;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeOut);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

}
