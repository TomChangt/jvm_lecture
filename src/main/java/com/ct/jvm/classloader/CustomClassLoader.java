package com.ct.jvm.classloader;

import java.io.*;

/**
 * @author changtong
 * @since 2020/4/7
 */
public class CustomClassLoader extends ClassLoader {

    private String classLoaderName;

    private String path;

    private static final String filePost = ".class";

    public void setPath(String path) {
        this.path = path;
    }

    public CustomClassLoader(String classLoaderName) {
        super();//将系统类加载器当作该类的父类加载器
        this.classLoaderName = classLoaderName;
    }

    public CustomClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent);//显示指定该类的父类加载器
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println(classLoaderName+"的findClass,输出这句话说明我们自己的类加载器加载了指定的类");
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }


    private byte[] loadClassData(String name) {
        name = name.replace(".", File.separator);//File.separator根据操作系统而变化
        try(InputStream is = new FileInputStream(new File(path+name + filePost));
            ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream()
        ) {
            int len;
            while (-1 != (len = is.read())) {
                byteArrayOutputStream.write(len);
            }
            byte[] data  = byteArrayOutputStream.toByteArray();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) throws Exception {
        CustomClassLoader classLoader = new CustomClassLoader("myCustomClassLoader");
        classLoader.setPath("/Users/changtong/Downloads/testclassload/");
        test(classLoader);
        classLoader = null;
        System.gc();
        Thread.sleep(10000); // jvisualvm 查看当前java进程 -XX:+TraceClassUnloading这个用于追踪类卸载的信息
        CustomClassLoader classLoader2 = new CustomClassLoader("myCustomClassLoader2");
        classLoader2.setPath("/Users/changtong/Downloads/testclassload/");
        test(classLoader2);
        /**
         * 执行结果：
         * myCustomClassLoader的findClass,输出这句话说明我们自己的类加载器加载了指定的类
         * com.ct.jvm.classloader.CustomClassLoader@2503dbd3
         * 2125039532
         * -------------------------------------
         * myCustomClassLoader2的findClass,输出这句话说明我们自己的类加载器加载了指定的类
         * com.ct.jvm.classloader.CustomClassLoader@12a3a380
         * 1554874502
         * -------------------------------------
         */

    }

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.ct.jvm.classloader.ClassLoad1");
        Object instance = clazz.newInstance();
        System.out.println(instance.getClass().getClassLoader());
        System.out.println(instance.hashCode());
        System.out.println("-------------------------------------");
    }


}
