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
       System.out.println(MyChild8.a);


        //输出
        // ClassLoad8 static block
        // MyParent8 static block
        // 1
    }



}

class MyParent8{

    static int a = 1;

    static {
        System.out.println("MyParent8 static block");
    }

}

class MyChild8 extends MyParent8 {
    static int b = 1;

    static {
        System.out.println("MyChild8 static block");
    }
}