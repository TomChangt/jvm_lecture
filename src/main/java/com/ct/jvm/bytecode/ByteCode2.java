package com.ct.jvm.bytecode;

/**
 * @author changtong
 * @since 2020/4/10
 */
public class ByteCode2 {
    public void test(Grandpa grandpa) {
        System.out.println("Grandpa");
    }

    public void test(Father father) {
        System.out.println("father");
    }

    public void test(Son son) {
        System.out.println("Son");
    }

    public static void main(String[] args) {
        ByteCode2 code = new ByteCode2();
        Grandpa g1 = new Father();
        Grandpa g2 = new Son();
//        code.test(g1);
//        code.test((Son) g2);
        g1.method();
        g2.method();
    }
}


class Grandpa {
    public void method(){
        System.out.println("Grandpa method");
    }
}

class Father extends Grandpa {
    @Override
    public void method(){
        System.out.println("Father method");
    }
}

class Son extends Father {
    @Override
    public void method(){
        System.out.println("Son method");
    }

}