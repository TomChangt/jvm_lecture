package com.ct.jvm.classloader;


/**
 * @author changtong
 * @since 2020/4/3
 */
public class ClassLoad8 {

    static {
        System.out.println("ClassLoad8 static block");
    }

    public static void main(String[] args) {
       System.out.println(Child1.a);


        //输出
        // ClassLoad8 static block
        // Parent1 static block
        // 1
    }



}

class Parent1{

    static int a = 1;

    static {
        System.out.println("Parent1 static block");
    }

}

class Child1 extends Parent1{
    static int b = 1;

    static {
        System.out.println("Child1 static block");
    }
}