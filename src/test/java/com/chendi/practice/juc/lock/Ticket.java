package com.chendi.practice.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author chendi
 * @Date 2018/8/24.
 * @descript 模拟门票售卖
 */
public class Ticket {

    private ReentrantLock lock = new ReentrantLock();


    private int totalNo;

    public Ticket(int totalNo) {
        this.totalNo = totalNo;
    }


    public  int getTotalNo(){
        return  totalNo;
    }


    public void sale() {
            try {
                lock.lock();
                if (totalNo > 0) {
                    totalNo--;
                    System.out.println("剩余门票:" + totalNo + "-----------------" + Thread.currentThread().getName());
                } else {
                    System.out.println("门票已售完:" + totalNo + "-----------------" + Thread.currentThread().getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
    }

    public void add(int num) {
        try {
            lock.lock();
            System.out.println("增加门票" + num);
            totalNo = totalNo + num;
        } finally {
            lock.unlock();
        }
    }
}
