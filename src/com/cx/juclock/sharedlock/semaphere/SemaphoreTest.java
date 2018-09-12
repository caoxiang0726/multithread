package com.cx.juclock.sharedlock.semaphere;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore sp = new Semaphore(3);//就3个坑位
        for (int i = 0; i < 5; i++) {//有10个人，10个线程
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        sp.acquire();//
                        System.out.println(Thread.currentThread().getName() +
                                "进入，当前已有" + (3 - sp.availablePermits()) + "剩余");
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println(Thread.currentThread().getName() +
                                "即将离开");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } finally {
                        sp.release();//释放
                    }

                }
            };
            service.execute(runnable);
        }
        service.shutdown();//结束程序
    }

}
