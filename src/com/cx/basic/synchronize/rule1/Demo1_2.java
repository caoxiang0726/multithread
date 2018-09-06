package com.cx.basic.synchronize.rule1;

class MyThread extends Thread {
    
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized(this) {
            try {  
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " loop " + i);  
                }
            } catch (InterruptedException ie) {  
            }
        }  
    }
}

public class Demo1_2 {

    public static void main(String[] args) {  
        Thread t1 = new MyThread("t1");
        Thread t2 = new MyThread("t2");
        t1.start();
        t2.start();
    } 
}
/**
 * result:
 * t1 loop 0
 t2 loop 0
 t2 loop 1
 t1 loop 1
 t2 loop 2
 t1 loop 2
 t1 loop 3
 t2 loop 3
 t1 loop 4
 t2 loop 4
 */

/**
 * synchronized(this)中的this是指“当前的类对象”，即synchronized(this)所在的类对应的当前对象。它的作用是获取“当前对象的同步锁”。
 对于Demo1_2中，synchronized(this)中的this代表的是MyThread对象，而t1和t2是两个不同的MyThread对象，
    因此t1和t2在执行synchronized(this)时，获取的是不同对象的同步锁。
 对于Demo1_1对而言，synchronized(this)中的this代表的是MyRunable对象；
     t1和t2共同一个MyRunable对象，因此，一个线程获取了对象的同步锁，会造成另外一个线程等待。
 */
