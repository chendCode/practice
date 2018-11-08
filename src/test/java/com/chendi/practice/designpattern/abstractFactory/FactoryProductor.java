package com.chendi.practice.designpattern.abstractFactory;

/**
 * @Author chendi
 * @Date 2018/8/29.
 * @descript 工厂生产者
 */
public class FactoryProductor {


    public static AbstractFactory getFactory(String factoryType) {
        if ("hum".equals(factoryType))
            return new HumanTypeFactory();
        if ("sex".equals(factoryType))
            return new HumanSexFactory();
        return null;
    }
}