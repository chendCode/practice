package com.chendi.practice.designpattern.FacadePattern.bean;

/**
 * @Author chendi
 * @Date 2018/8/30.
 * @descript
 */
public class Cpu  implements  Computer{

    @Override
    public  void turnOn(){
        System.out.println("CPU 启动.....");
    }


    @Override
    public void turnOff() {
        System.out.println("CPU 关闭.....");
    }
}
