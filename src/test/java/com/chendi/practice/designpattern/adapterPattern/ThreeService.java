package com.chendi.practice.designpattern.adapterPattern;

import com.chendi.practice.designpattern.adapterPattern.interfacepk.ThreeInterface;

/**
 * @Author chendi
 * @Date 2018/8/29.
 * @descript
 */
public class ThreeService implements ThreeInterface {
    @Override
    public void call() {
        System.out.println("我是三");
    }
}
