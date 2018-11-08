package com.chendi.practice.juc.lock;



/**
 * @Author chendi
 * @Date 2018/8/14.
 * @descript
 */
public class LockDemon implements Runnable {
    //初始化一千张票
     Ticket ticket ;

    public LockDemon(Ticket ticket) {
        this.ticket = ticket;
    }

    public LockDemon() {
    }

    @Override
    public void run() {
        while (true){
            if(ticket.getTotalNo()>0){
                ticket.sale();
            }
        }
    }


    /**
     * 多窗口买票线程问题 未加锁
     */
    public static void main(String[] args) {
        Ticket ticket = new Ticket(1000);
        LockDemon sale1 = new LockDemon(ticket);
        LockDemon sale2 = new LockDemon(ticket);
        TickAddThread addThread = new TickAddThread(ticket);

        Thread t1 = new Thread(sale1, "sale1-Thread");
        Thread t2 = new Thread(sale2, "sale2-Thread");
        Thread t3 = new Thread(addThread, "add-Thread");

        t1.start();
        t2.start();
        t3.start();

    }


}
