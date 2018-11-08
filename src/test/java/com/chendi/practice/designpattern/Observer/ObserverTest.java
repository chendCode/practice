package com.chendi.practice.designpattern.Observer;

import com.chendi.practice.designpattern.Observer.observer.AbstractObserver;
import com.chendi.practice.designpattern.Observer.observer.Observer_one;
import com.chendi.practice.designpattern.Observer.observer.Observer_three;
import com.chendi.practice.designpattern.Observer.observer.Observer_two;
import com.chendi.practice.designpattern.Observer.subject.Subject;
import org.junit.Test;

/**
 * @Author chendi
 * @Date 2018/8/30.
 * @descript 观察者模式 订阅/发布
 */
public class ObserverTest {



    //被观察者
    Subject sub = new Subject();

    AbstractObserver one = new Observer_one();
    AbstractObserver tow = new Observer_two();
    AbstractObserver three = new Observer_three();

    @Test
    public  void  test(){
        sub.addObserver(one);
        sub.addObserver(tow);
        sub.addObserver(three);

        sub.setMsg("牛逼啊");
    }


}
