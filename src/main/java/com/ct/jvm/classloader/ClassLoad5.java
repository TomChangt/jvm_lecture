package com.ct.jvm.classloader;

import java.time.LocalDateTime;

/**
 * 当一个接口在初始化时，并不要求其父接口都完成初始化
 * 只有在真正使用到父接口的时候（如引用接口中所定义的常量时），才会初始化
 */
public class ClassLoad5 {
    public static void main(String[] args) {
        System.out.println(MyChild5.a);
    }
}

interface MyParent5 {

    int a = 9; //前面省了public static final

    Thread thread = new Thread() {
        {
            System.out.println("thread 初始化了");//如果父接口初始化了这句应该输出
        }
    };
}

class MyChild5 implements MyParent5 {     //接口属性默认是 public static final
    String b = LocalDateTime.now().toString();
}