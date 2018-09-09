package com.cx.threadpool.refusepolicy;

/**
 * 线程池的拒绝策略，是指当任务添加到线程池中被拒绝，而采取的处理措施。
 当任务添加到线程池中之所以被拒绝，可能是由于：第一，线程池异常关闭。第二，任务数量超过线程池的最大限制。

 线程池共包括4种拒绝策略，它们分别是：AbortPolicy, CallerRunsPolicy, DiscardOldestPolicy和DiscardPolicy。
 AbortPolicy         -- 当任务添加到线程池中被拒绝时，它将抛出 RejectedExecutionException 异常。
 CallerRunsPolicy    -- 当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务。
 DiscardOldestPolicy -- 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中。
 DiscardPolicy       -- 当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务。

 线程池默认的处理策略是AbortPolicy！
 */
public class Concept {

}
