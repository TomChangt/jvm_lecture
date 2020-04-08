package com.ct.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author changtong
 * @since 2020/4/7
 */
public class CustomClassLoader2 extends ClassLoader {

    private String classLoaderName;

    private static final String filePost = ".class";

    public CustomClassLoader2(String classLoaderName) {
        super();//将系统类加载器当作该类的父类加载器
        this.classLoaderName = classLoaderName;
    }

    public CustomClassLoader2(ClassLoader parent, String classLoaderName) {
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
        try(InputStream is = new FileInputStream(new File(name + filePost));
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
        CustomClassLoader2 classLoader = new CustomClassLoader2("myCustomClassLoader");
        test(classLoader);
        //输出
        //com.ct.jvm.classloader.ClassLoad1@2503dbd3
        //原因，因为自定义的CustomClassLoader，父类依旧是系统（应用）加载器，且这个类在classpath下，所以依旧是系统（应用）加载器加载
        //
    }

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.ct.jvm.classloader.ClassLoad1");
        Object instance = clazz.newInstance();
        System.out.println(instance);
    }


}
