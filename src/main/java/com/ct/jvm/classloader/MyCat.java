package com.ct.jvm.classloader;

/**
 * @author changtong
 * @since 2020/4/8
 */
public class MyCat {
    public MyCat() {
        System.out.println("MyCat by load " + MyCat.class.getClassLoader());
    }
}
