package com.chendi.practice.designpattern.abstractFactory;

import com.chendi.practice.designpattern.abstractFactory.bean.Huam;
import com.chendi.practice.designpattern.abstractFactory.bean.Sex;

/**
 * @Author chendi
 * @Date 2018/8/29.
 * @descript 抽象工厂 一个大工厂 里边包含各种各样的工具 如果一个小工厂需要使用到这里的工具 则可以继承次工厂实现相应的工具
 */
public abstract class AbstractFactory {

    public Huam say(String type) {
        return null;
    }


    public Sex sex(String type) {
        return null;
    }

}