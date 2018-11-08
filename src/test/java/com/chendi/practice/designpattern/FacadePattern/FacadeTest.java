package com.chendi.practice.designpattern.FacadePattern;

import com.chendi.practice.designpattern.FacadePattern.bean.Computer;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author chendi
 * @Date 2018/8/30.
 * @descript 面板模式或门面模式
 */
public class FacadeTest {



    @Test
    public void facadeTest(){

        ComputerFacade facade = new ComputerFacade();
        facade.cpmputerOn();
        facade.cpmputerOff();


    }
}
