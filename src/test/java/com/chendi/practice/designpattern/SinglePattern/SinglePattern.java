package com.chendi.practice.designpattern.SinglePattern;

/**
 * @Author chendi
 * @Date 2018/8/29.
 * @descript
 */
public class SinglePattern {

    private SinglePattern singlePattern;

    private SinglePattern() {
    }


    /**
     * 懒汉式 同步模式
     *
     * @return
     */
    public synchronized SinglePattern getSinglePattern() {
        if (singlePattern == null) {
            return new SinglePattern();
        } else {
            return singlePattern;
        }
    }
    /**
     *
     * 饿汉模式
     private SinglePattern singlePattern = new SinglePattern();
     public synchronized SinglePattern getSinglePattern() {
        return singlePattern;
     }
     *
     */

    /**
     * 枚举类型实现单列
     *它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化
     *
     *
     public enum Singleton {
     INSTANCE;
     public void whateverMethod() {
     }
     }
     */
}
