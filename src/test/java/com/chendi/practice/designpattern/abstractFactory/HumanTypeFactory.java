package com.chendi.practice.designpattern.abstractFactory;

import com.chendi.practice.designpattern.abstractFactory.bean.Huam;
import com.chendi.practice.designpattern.abstractFactory.bean.Human1;
import com.chendi.practice.designpattern.abstractFactory.bean.Human2;

/**
 * @Author chendi
 * @Date 2018/8/29.
 * @descript
 */
public class HumanTypeFactory extends AbstractFactory {

    @Override
    public Huam say(String type) {
        if("1".equals(type))
            return new Human1();
        if("2".equals(type))
            return new Human2();
        return null;
    }
}
