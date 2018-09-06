package com.cx.basic.ThreadAndRunnable;

/**
 * 1 “一个类只能有一个父类，但是却能实现多个接口”，因此Runnable具有更好的扩展性。
 * 2  Runnable还可以用于“资源的共享”。即，多个线程都是基于某一个Runnable对象建立的，它们会共享Runnable对象上的资源?
 */

// ThreadTest.java 源码
class MyThread extends Thread {
    private int ticket = 10;

    public void run() {
        for (int i = 0; i < 20; i++) {
            if (this.ticket > 0) {
                System.out.println(this.getName() + " 卖票：ticket" + this.ticket--);
            }
        }
    }
};

public class ThreadTest {
    public static void main(String[] args) {
        // 启动3个线程t1,t2,t3；每个线程各卖10张票！ 资源不共享
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
    }
}
