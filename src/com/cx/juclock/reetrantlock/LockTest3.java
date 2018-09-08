package com.cx.juclock.reetrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * “仓库的容量不可能为负数”以及“仓库的容量是有限制的”。
 解决该问题是通过Condition。Condition是需要和Lock联合使用的：
 通过Condition中的await()方法，能让线程阻塞[类似于wait()]；通过Condition的signal()方法，能让唤醒线程[类似于notify()]。


 */

class Depot3 {
    private int capacity;
    private int size;
    private Lock lock;
    private Condition fullCondtion; // 生产条件
    private Condition emptyCondtion; // 消费条件

    public Depot3(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.lock = new ReentrantLock();
        this.fullCondtion = lock.newCondition();//Condition是需要和Lock联合使用的
        this.emptyCondtion = lock.newCondition();
    }

    public void produce(int val) {
        lock.lock();
        try {
            // left 表示“想要生产的数量”(有可能生产量太多，需多次生产)
            int left = val;
            while (left > 0) {
                // 库存已满时，等待“消费者”消费产品。
                while (size >= capacity)
                    fullCondtion.await();
                // 获取“实际生产的数量”(即库存中新增的数量)
                // 如果“库存”+“想要生产的数量”>“总的容量”，则“实际增量”=“总的容量”-“当前容量”。(此时填满仓库)
                // 否则“实际增量”=“想要生产的数量”
                int inc = (size + left) > capacity ? (capacity - size) : left;
                size += inc;
                left -= inc;
                System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, inc, size);
                // 通知“消费者”可以消费了。
                emptyCondtion.signal();
            }
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        try {
            int left = val;
            while (left > 0) {
                // 库存为0时，等待“生产者”生产产品。
                while (size <= 0)
                    emptyCondtion.await();


                // 获取“实际消费的数量”(即库存中实际减少的数量)
                // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                // 否则，“实际消费量”=“客户要消费的数量”。
                int dec = (size < left) ? size : left;
                size -= dec;
                left -= dec;
                System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, dec, size);
                fullCondtion.signal();
            }
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        return "capacity:" + capacity + ", actual size:" + size;
    }
}


class Producer3 {
    private Depot3 Depot3;

    public Producer3(Depot3 Depot3) {
        this.Depot3 = Depot3;
    }

    // 消费产品：新建一个线程向仓库中生产产品。
    public void produce(final int val) {
        new Thread() {
            public void run() {
                Depot3.produce(val);
            }
        }.start();
    }
}


class Customer3 {
    private Depot3 Depot3;

    public Customer3(Depot3 Depot3) {
        this.Depot3 = Depot3;
    }

    public void consume(final int val) {
        new Thread() {
            public void run() {
                Depot3.consume(val);
            }
        }.start();
    }
}

public class LockTest3 {
    public static void main(String[] args) {
        Depot3 mDepot3 = new Depot3(10);
        Producer3 mPro = new Producer3(mDepot3);
        Customer3 mCus = new Customer3(mDepot3);

        mPro.produce(5);
        mPro.produce(6);
        mCus.consume(3);
    }
}

/**
 * Thread-1 produce(  6) --> left=  0, inc=  6, size=  6
 Thread-0 produce(  5) --> left=  1, inc=  4, size= 10
 Thread-2 consume(  3) <-- left=  0, dec=  3, size=  7
 Thread-0 produce(  5) --> left=  0, inc=  1, size=  8
 */
