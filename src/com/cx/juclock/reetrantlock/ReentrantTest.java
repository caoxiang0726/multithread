package com.cx.juclock.reetrantlock;

/**
 * 广义上的可重入锁指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并
 * 且不发生死锁（前提得是同一个对象或者class），这样的锁就叫做可重入锁。
 * ReentrantLock和synchronized都是可重入锁
 */

/**
 * 在JAVA中，内置锁都是可重入的，也就是说，如果某个线程试图获取一个已经由它自己持有的锁时，
 * 那么这个请求会立刻成功，并且会将这个锁的计数值加1，而当线程退出同步代码块时，计数器将会递减，当计数值等于0时，锁释放。
 */
public class ReentrantTest implements Runnable {

    public synchronized void get() {
        System.out.println(Thread.currentThread().getName());
        set();
    }

    public synchronized void set() {
        System.out.println(Thread.currentThread().getName());
    }

    public void run() {
        get();
    }

    public static void main(String[] args) {
        ReentrantTest rt = new ReentrantTest();
        for (int i = 0; i < 5; i++) {
            new Thread(rt).start();
        }
    }
}