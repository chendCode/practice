package com.chendi.practice.designpattern.proxyPattern;

import com.chendi.practice.designpattern.proxyPattern.interfacePk.Human;

/**
 * @Author chendi
 * @Date 2018/9/3.
 * @descript
 */
public class ProxyMan implements Human {

    private ReallyMan reallyMan;



    @Override
    public void say() {
        if (reallyMan==null)
            reallyMan = new ReallyMan();
        reallyMan.say();
    }
}
