package com.ct.jvm.classloader;

import java.lang.reflect.Method;

public class ClassLoad13 {

    public static void main(String[] args) throws Exception {
        CustomClassLoader loader1 = new CustomClassLoader("load1");
        CustomClassLoader loader2 = new CustomClassLoader("load2");
        Class<?> clazz1 = loader1.loadClass("com.ct.jvm.classloader.Person");
        Class<?> clazz2 = loader2.loadClass("com.ct.jvm.classloader.Person");

        //clazz1和clazz均由应用类加载器加载的，第二次不会重新加载，结果为true
        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setPerson", Object.class);
        method.invoke(object1, object2);
    }
}

class Person {

    private Person person;

    public void setPerson(Object object) {
        this.person = (Person) object;
    }
}