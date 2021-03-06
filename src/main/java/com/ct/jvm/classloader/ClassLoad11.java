package com.ct.jvm.classloader;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author changtong
 * @since 2020/4/7
 */
public class ClassLoad11 {

    public static void main(String[] args) throws Exception {
//        CustomClassLoader classLoader = new CustomClassLoader("myCustomClassLoader");
//        classLoader.setPath("/Users/changtong/Downloads/testclassload/");
//        Class<?> clazz = classLoader.loadClass("com.ct.jvm.classloader.MySimple");
//        System.out.println(clazz.hashCode());
//        //如果注释掉该行，就并不会实例化MySimple对象，不会加载MyCat（可能预先加载）
//        Object instance = clazz.newInstance();//实列化Simple和MyCat
//        //MyCat是由加载MySample的加载器去加载的：
//        //如果只删除classpath下的MyCat，则会报错，NoClassDefFoundError；
//        //如果只删除classpath下的MySample，则由自定义加载器加载桌面上的MySample，由系统应用加载器加载MyCat。

        long time = System.currentTimeMillis();
        System.out.println(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long time1 = sdf.parse(sdf.format(time)).getTime();
        System.out.println(time1);

    }


}
