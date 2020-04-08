package com.ct.jvm.classloader;

import java.lang.reflect.Method;

/**
 * 1.每个类加载器都有自己的命名空间，命名空间由该加载器及所有父加载器所加载的类构成；
 * 2.在同一个命名空间中，不会出现类的完整名字（包括类的包名）相同的两个类；
 * 3.在不同的命名空间中，有可能会出现类的完整名字（包括类的包名）相同的两个类；
 * 4.同一命名空间内的类是互相可见的，非同一命名空间内的类是不可见的；
 * 5.子加载器可以见到父加载器加载的类，父加载器也不能见到子加载器加载的类。
 */
public class ClassLoad14 {

    public static void main(String[] args) throws Exception {
        //这里需要做的操作是把target下面的MyPerson.class移动到/Users/changtong/Downloads/testclassload/com/ct/jvm/classloader/下
        CustomClassLoader loader1 = new CustomClassLoader("load1");
        loader1.setPath("/Users/changtong/Downloads/testclassload/");
        CustomClassLoader loader2 = new CustomClassLoader("load2");
        loader2.setPath("/Users/changtong/Downloads/testclassload/");
        Class<?> clazz1 = loader1.loadClass("com.ct.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.ct.jvm.classloader.MyPerson");
        //由于clazz1和clazz2分别有不同的类加载器所加载，所以他们处于不同的名称空间里
        System.out.println(clazz1 == clazz2);//false

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        //此处报错，loader1和loader2所处不用的命名空间
        method.invoke(object1, object2);

       /*
       输出结果：
        findClass,输出这句话说明我们自己的类加载器加载了指定的类
        findClass,输出这句话说明我们自己的类加载器加载了指定的类
        false
       Exception in thread "main" java.lang.reflect.InvocationTargetException
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at com.ct.jvm.classloader.ClassLoad14.main(ClassLoad14.java:30)
        Caused by: java.lang.ClassCastException: com.ct.jvm.classloader.MyPerson cannot be cast to com.ct.jvm.classloader.MyPerson
        at com.ct.jvm.classloader.MyPerson.setMyPerson(MyPerson.java:8)
        ... 5 more
        */
    }
}