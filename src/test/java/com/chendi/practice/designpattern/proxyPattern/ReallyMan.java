package com.chendi.practice.designpattern.proxyPattern;

import com.chendi.practice.designpattern.proxyPattern.interfacePk.Human;

import java.util.Collections;


/**
 * @Author chendi
 * @Date 2018/9/3.
 * @descript
 */
public class ReallyMan implements Human {


    @Override
    public void say() {
        System.out.println(this.getClass().getName()+"    真实实体");
    }
}
