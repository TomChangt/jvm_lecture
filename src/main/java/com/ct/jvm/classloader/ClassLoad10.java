package com.ct.jvm.classloader;

/**
 * 调用类的loadClass并不是主使实用类，不会导致类的初始化
 */
public class ClassLoad10 {


    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> loadClass = classLoader.loadClass("com.ct.jvm.classloader.MyClass");
        System.out.println("-------------------------------");
        Class<?> clazz = Class.forName("com.ct.jvm.classloader.MyClass");//反射会导致一个类的初始化
        System.out.println(clazz);
        //输出结果：
        //-------------------------------
        //MyClass static block
        //class com.ct.jvm.classloader.MyClass
    }


}


class MyClass {
    static {
        System.out.println("MyClass static block");
    }
}