package com.cx.juclock.sharedlock.cylicbarria;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest3 {
    public static void main(String[] args) {
        int cbSize = 3;
        ExecutorService service = Executors.newCachedThreadPool();
        final CyclicBarrier cb = new CyclicBarrier(cbSize);//3个人去旅游
        for (int i = 0; i < cbSize; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));//来的时间不一样
                        System.out.println(Thread.currentThread().getName() +
                                "即将到达集合地点1，当前已有" + cb.getNumberWaiting() + "个已经到达，正在等候");
                        cb.await();//3个人全部到达，才跑到第二个集合点

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(Thread.currentThread().getName() +
                                "即将到达集合地点2，当前已有" + cb.getNumberWaiting() + "个已经到达，正在等候");
                        cb.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(Thread.currentThread().getName() +
                                "即将到达集合地点3，当前已有" + cb.getNumberWaiting() + "个已经到达，正在等候");
                        cb.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}
