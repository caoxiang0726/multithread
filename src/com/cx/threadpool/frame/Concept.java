package com.cx.threadpool.frame;

/**
 * 1. Executor

 它是"执行者"接口，它是来执行任务的。准确的说，Executor提供了execute()接口来执行已提交的 Runnable 任务的对象。
 Executor存在的目的是提供一种将"任务提交"与"任务如何运行"分离开来的机制。
 它只包含一个函数接口：
 void execute(Runnable command)

 2. ExecutorService
 ExecutorService继承于Executor。它是"执行者服务"接口，它是为"执行者接口Executor"服务而存在的；
 准确的话，ExecutorService提供了"将任务提交给执行者的接口(submit方法)"，
 "让执行者执行任务(invokeAll, invokeAny方法)"的接口等等。

 3. AbstractExecutorService
 AbstractExecutorService是一个抽象类，它实现了ExecutorService接口。
 AbstractExecutorService存在的目的是为ExecutorService中的函数接口提供了默认实现。
k
 4. ThreadPoolExecutor
 ThreadPoolExecutor就是大名鼎鼎的"线程池"。它继承于AbstractExecutorService抽象类。

 5. ScheduledExecutorService
 ScheduledExecutorService是一个接口，它继承于于ExecutorService。它相当于提供了"延时"和"周期执行"功能的ExecutorService。
 ScheduledExecutorService提供了相应的函数接口，可以安排任务在给定的延迟后执行，也可以让任务周期的执行。

 6. ScheduledThreadPoolExecutor
 ScheduledThreadPoolExecutor继承于ThreadPoolExecutor，并且实现了ScheduledExecutorService接口。
 它相当于提供了"延时"和"周期执行"功能的ScheduledExecutorService。
 ScheduledThreadPoolExecutor类似于Timer，但是在高并发程序中，ScheduledThreadPoolExecutor的性能要优于Timer。

 7. Executors

 Executors是个静态工厂类。
 它通过静态工厂方法返回ExecutorService、ScheduledExecutorService、ThreadFactory 和 Callable 等类的对象。


 */
public class Concept {
}
