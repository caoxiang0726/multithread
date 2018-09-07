package com.cx.basic.jion;

/**
 *  让“主线程”等待“子线程”结束之后才能继续运行。(类似LINUX父子线程)
 */

/**
 * 因为Son是在Father中创建并启动的，所以，Father是主线程类，Son是子线程类。
 在Father主线程中，通过new Son()新建“子线程s”。接着通过s.start()启动“子线程s”，并且调用s.join()。
 在调用s.join()之后，Father主线程会一直等待，直到“子线程s”运行完毕；在“子线程s”运行完毕之后，Father主线程才能接着运行。 这
 也就是我们所说的“join()的作用，是让主线程会等待子线程结束之后才能继续运行”！
 */

/**
 * 源码分析：join()
 *
 *  当millis==0时，会进入while(isAlive())循环；即只要子线程是活的，主线程就不停的等待。
 *  wait(0);停止的是当前线程，也就是父线程
 *

 }
 */
public class Father extends Thread {
    public void run() {
        Son s = new Son();
        s.start();
        try {
            s.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("father");
    }

    public static void main(String[] args) {
        Thread t1 = new Father();
        t1.start();
    }
}

 class Son extends Thread {
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("son");
    }
}