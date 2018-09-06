package com.cx.basic.StartAndRun;

/**
 * start() : 它的作用是启动一个新线程，新线程会执行相应的run()方法。start()不能被重复调用。
 * run()   : run()就和普通的成员方法一样，可以被重复调用。单独调用run()的话，会在当前线程中执行run()，而并不会启动新线程！
 *
 * start()源码：
 *  1 先判断是不是就绪状态，不是抛异常
 *  2 添加到线程组
 *  3 通过start0()启动该线程，再把线程状态标记
 *
 */
class MyThread3 extends Thread {

    public MyThread3(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }
}

public class StartMethod {
    public static void main(String[] args) {
        Thread mythread = new MyThread3("mythread3");

        System.out.println(Thread.currentThread().getName() + "-call mythread.run()");//main-call mythread.run()
        mythread.run();//main is running

        System.out.println(Thread.currentThread().getName() + "-call mythread.start()");//main-call mythread.start()
        mythread.start();//mythread3 is running
    }
}
