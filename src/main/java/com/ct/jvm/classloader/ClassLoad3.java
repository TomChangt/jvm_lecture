package com.ct.jvm.classloader;

import java.util.UUID;

/**
 * 当一个常量的值并非编译期可以确定的，那么其值就不会被放到调用该常量的类的常量池中
 * 这时候在程序运行时，会导致主动使用这个常量所在的类，显然会导致这个类被初始化
 *
 */
public class ClassLoad3 {
    public static void main(String[] args) {
        System.out.println(MyParent3.str);
    }
}

class MyParent3{

    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("MyParent3 static block");
    }

}