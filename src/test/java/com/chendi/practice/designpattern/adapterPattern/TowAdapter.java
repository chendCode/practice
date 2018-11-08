package com.chendi.practice.designpattern.adapterPattern;

import com.chendi.practice.designpattern.adapterPattern.interfacepk.TowInterface;

/**
 * @Author chendi
 * @Date 2018/8/29.
 * @descript
 */
public class TowAdapter implements TowInterface {

    private ThreeService three;


    @Override
    public void say() {
            System.out.println("我是二");
    }

    public void call(){
        if (three ==null){
            three = new ThreeService();
        }

        three.call();
    }
}
