package com.chendi.practice.designpattern.proxyPattern;

import org.junit.Test;

import java.util.TreeMap;

/**
 * @Author chendi
 * @Date 2018/9/3.
 * @descript
 */
public class proxyPatternDamon {


    @Test
    public  void ProxyTest(){
        ProxyMan man = new ProxyMan();
        man.say();
    }
}
