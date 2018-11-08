package com.chendi.practice.designpattern.Observer.observer;

/**
 * @Author chendi
 * @Date 2018/8/30.
 * @descript  观察者1
 */
public class Observer_one extends  AbstractObserver {

    @Override
    public void notifyObserver(String msg) {
        System.out.println(Observer_one.class.getName()+msg);
    }
}
