package com.cx.juclock.sharedlock.cylicbarria;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * 有一个大小为50000的随机数组，用5个线程分别计算10000个元素的和
   然后在将计算结果进行合并，得出最后的结果
 */
public class GroupCal {

    public static void main(String[] args) {
        //数组大小
        int size = 50000;
        //定义数组
        int[] numbers = new int[size];
        //随机初始化数组
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            numbers[i] = random.nextInt(100);
        }

        //单线程计算结果
        System.out.println();
        long start1 = System.currentTimeMillis();
        Long sum = 0L;
        for (int i = 0; i < size; i++) {
            sum += numbers[i];
        }
        System.out.println("单线程计算结果：" + sum);
        System.out.println("单线程耗时毫秒数："+(System.currentTimeMillis() - start1));

        //多线程计算结果
        //定义线程池
        long start2 = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //定义五个Future去保存子数组计算结果
        final int[] results = new int[5];

        //定义一个循环屏障，在屏障线程中进行计算结果合并
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            int sums = 0;
            for (int i = 0; i < 5; i++) {
                sums += results[i];
            }
            System.out.println("多线程计算结果：" + sums);
            System.out.println("多线程耗时毫秒数："+(System.currentTimeMillis() - start2));
        });

        //子数组长度
        int length = 10000;
        //定义五个线程去计算
        for (int i = 0; i < 5; i++) {
            //定义子数组
            int[] subNumbers = Arrays.copyOfRange(numbers, (i * length), ((i + 1) * length));
            //盛放计算结果
            int finalI = i;
            executorService.submit(() -> {
                for (int j = 0; j < subNumbers.length; j++) {
                    results[finalI] += subNumbers[j];
                }
                //等待其他线程进行计算
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        //关闭线程池
        executorService.shutdown();
    }
}
