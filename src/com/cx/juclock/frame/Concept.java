package com.cx.juclock.frame;

/**
 * 根据锁的添加到Java中的时间，Java中的锁，可以分为"同步锁"和"JUC包中的锁"。
 * JUC包中的锁的功能更加强大，它为锁提供了一个框架，该框架允许更灵活地使用锁，只是它的用法更难罢了。
 *
 * JUC包中的锁，包括：Lock接口，ReadWriteLock接口，LockSupport阻塞原语，Condition条件，
 * AbstractOwnableSynchronizer/AbstractQueuedSynchronizer/AbstractQueuedLongSynchronizer三个抽象类，
 * ReentrantLock独占锁，ReentrantReadWriteLock读写锁。
 *
 * 由于CountDownLatch，CyclicBarrier和Semaphore也是通过AQS来实现的；因此，我也将它们归纳到锁的框架中进行介绍。
 */
public class Concept {

}
