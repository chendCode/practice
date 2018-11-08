package com.chendi.practice.juc.lock;

/**
 * @Author chendi
 * @Date 2018/8/24.
 * @descript
 */
public class TickAddThread implements  Runnable {

    private Ticket ticket;

    public TickAddThread(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (true){
            if (ticket!=null){
                if(ticket.getTotalNo()<100){
                    ticket.add(10);
                }
            }
        }
    }
}
