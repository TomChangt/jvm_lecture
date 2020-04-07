package com.ct.jvm.classloader;

/**
 * 常量在编译阶段会存入到调用这个常量的方法所在类的常量池中
 * 本质上，调用类并没有直接引用到定义常量的类，因此并不会触发定义常量的类的初始化
 * 注意：这里指的试讲常量存放到了MyTest2的常量池中，之后MyTest2与MyParent2就没有任何关系了
 * 甚至可以将MyParent2的class文件删除
 *  javap -c MyTest2
 *  助记符：
 *  ldc表示将int，float或String类型的常量值推送至栈顶
 *  bipush便是单字节（-128 - 127）的常量值推送至栈顶
 *
 */
public class ClassLoad2 {
    public static void main(String[] args) {
        System.out.println(MyParent2.str);
    }
}

class MyParent2{

    public static final  String str = "hello world";

    static {
        System.out.println("MyParent2 static block");
    }
}
