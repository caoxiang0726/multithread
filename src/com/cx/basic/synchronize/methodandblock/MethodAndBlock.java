package com.cx.basic.synchronize.methodandblock;

/**
 * “synchronized方法”是用synchronized修饰方法，而 “synchronized代码块”则是用synchronized修饰代码块。
 * 1 synchronized代码块中的this是指当前对象。
 * 也可以将this替换成其他对象，例如将this替换成obj，则foo2()在执行synchronized(obj)时就获取的是obj的同步锁。
 * 2 synchronized代码块可以更精确的控制冲突限制访问区域，有时候表现更高效率。
 */
public class MethodAndBlock {


    public synchronized void synMethod() {
        for (int i = 0; i < 1000000; i++)
            ;
    }

    public void synBlock() {
        synchronized (this) {
            for (int i = 0; i < 1000000; i++)
                ;
        }
    }

    public static void main(String[] args) {
        MethodAndBlock demo = new MethodAndBlock();

        long start, diff;
        start = System.currentTimeMillis();
        demo.synMethod();
        diff = System.currentTimeMillis() - start;
        System.out.println("synMethod() : " + diff);

        start = System.currentTimeMillis();
        demo.synBlock();
        diff = System.currentTimeMillis() - start;
        System.out.println("synBlock()  : " + diff);//本机测试看不出效率高低。
    }
}

