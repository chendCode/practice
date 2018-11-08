package com.chendi.practice.designpattern.abstractFactory;

import com.chendi.practice.designpattern.abstractFactory.bean.*;

/**
 * @Author chendi
 * @Date 2018/8/29.
 * @descript
 */
public class HumanSexFactory extends  AbstractFactory {

    @Override
    public Sex sex(String type) {
        if("1".equals(type))
            return new Sex1();
        if("2".equals(type))
            return new Sex2();
        return  null;
    }
}
