package com.cx.basic.synchronize.rule1;

class MyRunable implements Runnable {
    
    @Override
    public void run() {
        synchronized(this) {
            try {  
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " loop " + i);  
                }
            } catch (InterruptedException ie) {  
            }
        }  
    }
}

public class Demo1_1 {

    public static void main(String[] args) {  
        Runnable demo = new MyRunable();

        Thread t1 = new Thread(demo, "t1");  // 新建“线程t1”, t1是基于demo这个Runnable对象
        Thread t2 = new Thread(demo, "t2");  // 新建“线程t2”, t2是基于demo这个Runnable对象,同一个对象
        t1.start();
        t2.start();
    } 
}
/**
 * result:
 * t1 loop 0
 t1 loop 1
 t1 loop 2
 t2 loop 0
 t2 loop 1
 t2 loop 2
 */

/**
 * run()方法中存在“synchronized(this)代码块”，而且t1和t2都是基于"demo这个Runnable对象"创建的线程。
 * 这就意味着，我们可以将synchronized(this)中的this看作是“demo这个Runnable对象”；因此，线程t1和t2共享“demo对象的同步锁”。
 * 所以，当一个线程运行的时候，另外一个线程必须等待“运行线程”释放“demo的同步锁”之后才能运行。
 */
