package com.cx.basic.interrupt;

/**
 *
 * interrupt()的作用是中断本线程。
 * Thread中的stop()和suspend()方法，由于固有的不安全性，已经建议不再使用！
 *
 * 1 终止处于“阻塞状态”的线程
 *     --通常，我们通过“中断”方式终止处于“阻塞状态”的线程。
 *       线程由于被调用了sleep(), wait(), join()等方法而进入阻塞状态；若此时调用线程的interrupt()将线程的中断标记设为true。
 *       由于处于阻塞状态，中断标记会被清除，同时产生一个InterruptedException异常。
 *       将InterruptedException放在适当的为止就能终止线程
 *
 *
 *
 * 2 终止处于“运行状态”的线程
 *   --通常，我们通过“标记”方式终止处于“运行状态”的线程。其中，包括“中断标记”和“额外添加标记”。
 *
 *   说明：isInterrupted()是判断线程的中断标记是不是为true。当线程处于运行状态，并且我们需要终止它时；
 *        可以调用线程的interrupt()方法，使用线程的中断标记为true，即isInterrupted()会返回true。此时，就会退出while循环。
     注意：interrupt()并不会终止处于“运行状态”的线程！它会将线程的中断标记设为true。
 */
public class InterruputConcept {
}
