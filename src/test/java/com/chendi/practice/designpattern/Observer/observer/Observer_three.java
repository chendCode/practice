package com.chendi.practice.designpattern.Observer.observer;

/**
 * @Author chendi
 * @Date 2018/8/30.
 * @descript
 */
public class Observer_three extends  AbstractObserver {

    @Override
    public void notifyObserver(String msg) {
        System.out.println(Observer_three.class.getName()+msg);
    }
}
