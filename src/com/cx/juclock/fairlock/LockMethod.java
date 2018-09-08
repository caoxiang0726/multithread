package com.cx.juclock.fairlock;

/**
 * 1. lock()

 lock()在ReentrantLock.java的FairSync类中实现，它的源码如下：

 final void lock() {
    acquire(1);
 }

 说明：“当前线程”实际上是通过acquire(1)获取锁的。
 这里说明一下“1”的含义，它是设置“锁的状态”的参数。对于“独占锁”而言，锁处于可获取状态时，它的状态值是0；
 锁被线程初次获取到了，它的状态值就变成了1。
 由于ReentrantLock(公平锁/非公平锁)是可重入锁，所以“独占锁”可以被单个线程多此获取，每获取1次就将锁的状态+1。也
 就是说，初次获取锁时，通过acquire(1)将锁的状态值设为1；再次获取锁时，将锁的状态值设为2；依次类推...
 这就是为什么获取锁时，传入的参数是1的原因了。
 可重入就是指锁可以被单个线程多次获取。
 */

/**
 * 2. acquire()

 acquire()在AQS中实现的，它的源码如下：

 public final void acquire(int arg) {
 if (!tryAcquire(arg) &&
 acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
 selfInterrupt();
 }

 (01) “当前线程”首先通过tryAcquire()尝试获取锁。获取成功的话，直接返回；
     尝试失败的话，进入到等待队列排序等待(前面还有可能有需要线程在等待该锁)。
 (02) “当前线程”尝试失败的情况下，先通过addWaiter(Node.EXCLUSIVE)来将“当前线程”加入到"CLH队列(非阻塞的FIFO队列)"末尾。
     CLH队列就是线程等待队列。
 (03) 再执行完addWaiter(Node.EXCLUSIVE)之后，会调用acquireQueued()来获取锁。由于此时ReentrantLock是公平锁，
      它会根据公平性原则来获取锁。
 (04) “当前线程”在执行acquireQueued()时，会进入到CLH队列中休眠等待，直到获取锁了才返回！如果“当前线程”在休眠等待过程中被中断过，
       acquireQueued会返回true，此时"当前线程"会调用selfInterrupt()来自己给自己产生一个中断。
       至于为什么要自己给自己产生一个中断，后面再介绍。

   小结：
 (01) 先是通过tryAcquire()尝试获取锁。获取成功的话，直接返回；尝试失败的话，再通过acquireQueued()获取锁。
 (02) 尝试失败的情况下，会先通过addWaiter()来将“当前线程”加入到"CLH队列"末尾；然后调用acquireQueued()，
     在CLH队列中排序等待获取锁，在此过程中，线程处于休眠状态。直到获取锁了才返回。
    如果在休眠等待过程中被中断过，则调用selfInterrupt()来自己产生一个中断。

 */
public class LockMethod {
}
