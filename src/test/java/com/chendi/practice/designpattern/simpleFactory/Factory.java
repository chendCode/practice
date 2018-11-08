package com.chendi.practice.designpattern.simpleFactory;

import org.junit.Test;

import java.util.Comparator;

/**
 * @Author chendi
 * @Date 2018/8/29.
 * @descript 简单工厂模式
 * 需要显式指定它们的标识 通过工厂进行返回
 */
public class  Factory{

    /**
     * 获取类型工具
     * @param type
     * @return
     */
    public Human getBean(String type){
        if("1".equals(type))
            return new Human1();
        if("2".equals(type))
            return new Human2();
        return  null;
    }



    @Test
    public  void TestFactory(){

        Factory f= new Factory();

        f.getBean("1").say();
        f.getBean("2").say();

    }


}
