package com.cx.juclock.sharedlock.countdownlatch;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//这里演示了一个人通知多个人；也可以多个人同事搞定才通知一个人
public class CountdownLatchTest {
    static ConcurrentHashMap<String,Boolean> concurrentHashMap = new ConcurrentHashMap();
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        final CountDownLatch mainLatch = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);

         for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() +
                                "-running-step0");
                        //
                        concurrentHashMap.put(Thread.currentThread().getName(),Math.random()>0.5);
                        mainLatch.await();
                        System.out.println( Thread.currentThread().getName() +
                                "-running-step2");
                        /**
                         * 去卡主线程
                         */
                        /*Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "回应命令处理结果");
                        cdAnswer.countDown();//3个线程分别减一*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            pool.execute(runnable);
        }

        try {
            /**
             * 还必须要有 这个等待时间。
             */
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println(Thread.currentThread().getName() +
                    "-running-step1");
            Collection<Boolean> booleans = concurrentHashMap.values();
            if (booleans.contains(false)) {
                System.out.println("有事务未提交");
            }
            mainLatch.countDown();

            /*System.out.println("线程" + Thread.currentThread().getName() +
                    "已发送命令，正在等待结果");
            cdAnswer.await();//3个运动员都到了，裁判员公布成绩
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已收到所有响应结果");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
