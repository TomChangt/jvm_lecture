package com.ct.jvm.classloader;

/**
 * @author changtong
 * @since 2020/4/8
 */
public class ClassLoad12 {

    public static void main(String[] args) throws Exception {
        CustomClassLoader classLoader = new CustomClassLoader("customClassLoader");
        classLoader.setPath("/Users/changtong/Downloads/testclassload/");
        Class<?> clazz = classLoader.loadClass("com.ct.jvm.classloader.MySimple2");
        System.out.println(clazz.hashCode());
        //如果注释掉该行，就并不会实例化MySample对象，不会加载MyCat（可能预先加载）
        Object instance = clazz.newInstance();//实列化Simple和MyCat
        //修改MyCat2后，仍然删除classpath下的Simple2，留下MyCat2，程序报错
        //因为命名空间，父加载器找不到子加载器所加载的类，因此MyCat2找不到
    }
}


class MyCat2 {
    public MyCat2() {
        System.out.println("MyCat2 by load " + MyCat.class.getClassLoader());
        System.out.println(MySimple.class);
    }
}

class MySimple2 {
    public MySimple2() {
        System.out.println("MySimple2 by Load " + MySimple2.class.getClassLoader());
        new MyCat2();
        System.out.println(MyCat.class);
    }
}
