package com.cx.juclock.sharedlock.countdownlatch;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程插入数据库，支持回滚
 */
public class SqlDemo {

    private static int LATCH_SIZE = 5;
    private static CountDownLatch doneSignal;
    static ConcurrentHashMap<String, Boolean> concurrentHashMap = new ConcurrentHashMap();

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();

        try {
            doneSignal = new CountDownLatch(LATCH_SIZE);

            // 新建5个任务
            for (int i = 0; i < LATCH_SIZE; i++) {
                /*Runnable runnable = new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(50);
                            System.out.println(Thread.currentThread().getName() +
                                    "-running-step0");
                            concurrentHashMap.put(Thread.currentThread().getName(), Math.random() > 0.3);
                        } catch (Exception e) {

                        }
                    }
                };
                pool.execute(runnable);
                doneSignal.countDown();*/

                new SqlInnerThread().start();
            }

            System.out.println("main await begin.");
            // "主线程"等待线程池中5个任务的完成
            doneSignal.await();

            System.out.println("main await finished.");
            Collection<Boolean> booleans = concurrentHashMap.values();
            if (booleans.contains(false)) {
                System.out.println("有事务未提交");
            }else {
                System.out.println("全部提交提交");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class SqlInnerThread extends Thread{
        public void run() {
            try {
                Thread.sleep(10);//模拟线程工作

                System.out.println(Thread.currentThread().getName() +
                        "-running-step0");
                concurrentHashMap.put(Thread.currentThread().getName(), Math.random() > 0.1);
                // 将CountDownLatch的数值减1
                doneSignal.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
