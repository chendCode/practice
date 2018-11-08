package com.chendi.practice.designpattern.Observer.subject;

import com.chendi.practice.designpattern.Observer.observer.AbstractObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author chendi
 * @Date 2018/8/30.
 * @descript 被观察者
 */
public class Subject {
    private List<AbstractObserver> observers = new ArrayList<>();    //状态改变
    public void setMsg(String msg) {
        notifyAll(msg);
    }
    //订阅
    public void addObserver(AbstractObserver observer) {
        observers.add(observer);
    }
    //通知所有订阅的观察者
    private void notifyAll(String msg) {
        for (AbstractObserver observer : observers) {
            observer.notifyObserver(msg);
        }
    }
}
