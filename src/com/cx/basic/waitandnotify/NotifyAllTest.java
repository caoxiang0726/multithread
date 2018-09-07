package com.cx.basic.waitandnotify;

public class NotifyAllTest {

    private static Object obj = new Object();
    public static void main(String[] args) {

        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        ThreadA t3 = new ThreadA("t3");
        t1.start();
        t2.start();
        t3.start();

        try {
            System.out.println(Thread.currentThread().getName()+" sleep(3000)");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized(obj) {
            // 主线程等待唤醒。
            System.out.println(Thread.currentThread().getName()+" notifyAll()");
            obj.notifyAll();
            /**
             *   在执行了notify方法之后，当前线程不会马上释放该对象锁，呈wait状态的线程也不能马上获得该对象锁，
             要等到执行notify方法的线程将程序执行完 ，也就是退出sychronized代码块后，当前线程才会释放锁
             */
        }
    }

    static class ThreadA extends Thread{

        public ThreadA(String name){
            super(name);
        }

        public void run() {
            synchronized (obj) {//获得了锁
                try {
                    // 打印输出结果
                    System.out.println(Thread.currentThread().getName() + " wait");

                    // 等待被唤醒 释放了锁
                    obj.wait();

                    // 打印输出结果
                    System.out.println(Thread.currentThread().getName() + " continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
/**
 * 4个线程都运行后，main通过sleep阻塞，其余3个是wait阻塞（释放了锁）。
 * 3秒之后，main获得了锁，然后notifyall。
 *
 */
