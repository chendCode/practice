package com.chendi.practice.algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author chendi
 * @Date 2018/10/17.
 * @descript
 */
public class TestDemo {

    volatile int Flag;

    public static void main(String[] args) {
        NumDemo num = new NumDemo();
        Thread a = new Thread(new ThreadA(num), "A");
        Thread b = new Thread(new ThreadB(num), "B");
        Thread c = new Thread(new ThreadC(num), "C");
        a.start();
        b.start();
        c.start();
    }

    public class Inner{

    }
}

class ThreadA implements Runnable {

    private NumDemo num;

    public ThreadA(NumDemo num) {
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            num.sayA();
        }
    }
}


class ThreadB implements Runnable {

    private NumDemo num;

    public ThreadB(NumDemo num) {
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            num.sayB();
        }
    }
}

class ThreadC implements Runnable {

    private NumDemo num;

    public ThreadC(NumDemo num) {
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            num.sayC();
        }
    }
}

class NumDemo {
    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    private int cycle = 1;
    private static int start = 1;

    public void sayA() {
        lock.lock();
        try {
            if (cycle != 1) {
                condition1.await();
            }
            while (start <= 100&&start%3==1) {
                System.out.println(start +"----------"+ Thread.currentThread().getName());
                ++start ;
                break;
            }
            cycle = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sayB() {
        lock.lock();
        try {
            if (cycle != 2) {
                condition2.await();
            }
            while (start <= 100&&start%3==2) {
                System.out.println(start+ "----------"+Thread.currentThread().getName());
                ++start ;
                break;
            }
            cycle = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sayC() {
        lock.lock();
        try {
            if (cycle != 3) {
                condition3.await();
            }
            while (start <= 100&&start%3==0) {
                System.out.println(start+ "----------"+Thread.currentThread().getName());
                ++start ;
                break;
            }
            cycle = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}