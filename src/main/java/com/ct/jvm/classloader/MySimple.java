package com.ct.jvm.classloader;

/**
 * @author changtong
 * @since 2020/4/8
 */
public class MySimple {

    public MySimple() {
        System.out.println("MySimple by Load " + MySimple.class.getClassLoader());
        new MyCat();
    }
}
