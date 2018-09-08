package com.cx.juclock.unfairlock;

/**
 * 非公平锁和公平锁在获取锁的方法上，流程是一样的；它们的区别主要表现在“尝试获取锁的机制不同”。
 * 简单点说，“公平锁”在每次尝试获取锁时，都是采用公平策略(根据等待队列依次排序等待)；
 * 而“非公平锁”在每次尝试获取锁时，都是采用的非公平策略(无视等待队列，直接尝试获取锁，如果锁是空闲的，即可获取状态，则获取锁)。
 */
public class Concept {
}