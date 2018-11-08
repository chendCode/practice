package com.chendi.practice.designpattern.abstractFactory;

import org.junit.Test;

/**
 * @Author chendi
 * @Date 2018/8/29.
 * @descript
 */
public class AbstractFacoryTest {

    @Test
    public void test(){

        AbstractFactory humFactory = FactoryProductor.getFactory("hum");
        AbstractFactory sexFactory = FactoryProductor.getFactory("sex");


    }
}
