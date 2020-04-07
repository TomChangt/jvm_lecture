package com.ct.jvm.classloader;

/**
 * 对于数组实例来说，其类型是有JVM在运行期动态生成，表示为[Lcom.ct.jvm.classloader.MyParent4
 * 这种形式，动态生成的类型，其父类是Object
 */
public class ClassLoad4 {

    public static void main(String[] args) {

        MyParent4[] myParent4s = new MyParent4[1];
        System.out.println(myParent4s.getClass());



        MyParent4[][] myParent4ss = new MyParent4[1][1];
        System.out.println(myParent4ss.getClass());

    }
}
class MyParent4{
    static {
        System.out.println("MyParent4 static block");
    }
}
