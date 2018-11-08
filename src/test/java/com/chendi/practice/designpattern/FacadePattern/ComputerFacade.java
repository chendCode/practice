package com.chendi.practice.designpattern.FacadePattern;

import com.chendi.practice.designpattern.FacadePattern.bean.Cpu;
import com.chendi.practice.designpattern.FacadePattern.bean.Fan;

/**
 * @Author chendi
 * @Date 2018/8/30.
 * @descript 电脑对外提供的接口
 */
public class ComputerFacade  {

    private Cpu cpu;
    private Fan fan;

    public ComputerFacade() {
        cpu = new Cpu();
        fan = new Fan();
    }


    public void cpmputerOn(){
        cpu.turnOn();
        fan.turnOn();
    }

    public void cpmputerOff(){
        cpu.turnOn();
        fan.turnOff();
    }

}
