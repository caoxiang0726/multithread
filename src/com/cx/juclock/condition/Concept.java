package com.cx.juclock.condition;

/**
 * Condition的作用是对锁进行更精确的控制。
 * Condition中的await()方法相当于Object的wait()方法，Condition中的signal()方法相当于Object的notify()方法，
 * Condition中的signalAll()相当于Object的notifyAll()方法。
 *
 * 不同的是，Object中的wait(),notify(),notifyAll()方法是和"同步锁"(synchronized关键字)捆绑使用的；
 * 而Condition是需要与"互斥锁"/"共享锁"捆绑使用的。
 *
 *
 * Condition除了支持上面的功能之外，它更强大的地方在于：能够更加精细的控制多线程的休眠与唤醒。
 * 对于同一个锁，我们可以创建多个Condition，在不同的情况下使用不同的Condition。(生产者消费者与仓库的例子)
 */
public class Concept {
}
