package com.cx.basic.ThreadAndRunnable;

class MyThread2 implements Runnable {
    private volatile int ticket = 10;//保证卖出10张票
    private int count = 0;

    public synchronized void run() {
        for (int i = 0; i < 20; i++) {
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖票：ticket" + this.ticket--);
                count++;
            }
        }
        System.out.println(Thread.currentThread().getName() +"卖票总量:"+count);
    }

}

public class RunnableTest {
    public static void main(String[] args) {
        MyThread2 mt = new MyThread2();
        // 启动3个线程t1,t2,t3(它们共用一个Runnable对象)，这3个线程一共卖10张票！(有可能多卖出几张)
        Thread t1 = new Thread(mt);
        Thread t2 = new Thread(mt);
        Thread t3 = new Thread(mt);
        t1.start();
        t2.start();
        t3.start();
    }
}

