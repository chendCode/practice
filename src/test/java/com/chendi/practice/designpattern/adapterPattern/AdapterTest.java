package com.chendi.practice.designpattern.adapterPattern;

import org.junit.Test;

/**
 * @Author chendi
 * @Date 2018/8/29.
 * @descript
 */
public class AdapterTest {


    /**
     *
     */
    @Test
    public void test(){
        TowAdapter towAdapter = new TowAdapter();

        towAdapter.say();
        towAdapter.call();
    }
}
