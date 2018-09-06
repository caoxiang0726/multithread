package com.cx.basic.synchronize.rule3;

class Count {

    public void synMethod() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " synMethod loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }

    public void nonSynMethod() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " nonSynMethod loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}

public class Demo3 {

    public static void main(String[] args) {
        final Count count = new Count();
        // 新建t1, t1会调用“count对象”的synMethod()方法
        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count.synMethod();
                    }
                }, "t1");


        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count.nonSynMethod();
                    }//同一个count对象
                }, "t2");


        t1.start();
        t2.start();
    }
}

/**
 * result:
 *  t1 synMethod loop 0
 t1 synMethod loop 1
 t1 synMethod loop 2
 t1 synMethod loop 3
 t1 synMethod loop 4
 t2 nonSynMethod loop 0
 t2 nonSynMethod loop 1
 t2 nonSynMethod loop 2
 t2 nonSynMethod loop 3
 t2 nonSynMethod loop 4
 */
