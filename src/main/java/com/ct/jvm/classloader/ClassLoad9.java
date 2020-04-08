package com.ct.jvm.classloader;

/**
 * @author changtong
 * @since 2020/4/7
 */
public class ClassLoad9 {

    static {
        System.out.println("ClassLoad9 static block");
    }

    public static void main(String[] args) {
        MyParent9 parent = new MyParent9();
        System.out.println(MyParent9.a);
        System.out.println(MyChild9.b);
        /*执行结果：由于父类已经初始化过了所以MyParent9只输出一次
         * ClassLoad9 static block
         * MyParent9 static block
         * 2
         * MyChild9 static block
         * 3
         */
    }



}

class MyParent9 {
    static int a = 2;

    static {
        System.out.println("MyParent9 static block");
    }
}

class MyChild9 extends MyParent9 {
    static int b = 3;

    static {
        System.out.println("MyChild9 static block");
    }
}