package com.cx.threadpool.excutor;

/**
 * ThreadPoolExecutor是线程池类。对于线程池，可以通俗的将它理解为"存放一定数量线程的一个线程集合。
 * 线程池允许若个线程同时允许，允许同时运行的线程数量就是线程池的容量；当添加的到线程池中的线程超过它的容量时，会有一部分线程阻塞等待。
 * 线程池会通过相应的调度策略和拒绝策略，对添加到线程池中的线程进行管理。"
 *
 * 1. workers
 workers是HashSet<Work>类型，即它是一个Worker集合。而一个Worker对应一个线程，也就是说线程池通过workers包含了"一个线程集合"。
 当Worker对应的线程池启动时，它会执行线程池中的任务；当执行完一个任务后，它会从线程池的阻塞队列中取出一个阻塞的任务来继续运行。
 wokers的作用是，线程池通过它实现了"允许多个线程同时运行"。

 2. workQueue
 workQueue是BlockingQueue类型，即它是一个阻塞队列。当线程池中的线程数超过它的容量的时候，线程会进入阻塞队列进行阻塞等待。
 通过workQueue，线程池实现了阻塞功能。

 3. mainLock
 mainLock是互斥锁，通过mainLock实现了对线程池的互斥访问。

 4. corePoolSize和maximumPoolSize
 corePoolSize是"核心池大小"，maximumPoolSize是"最大池大小"。它们的作用是调整"线程池中实际运行的线程的数量"。
 例如，当新任务提交给线程池时(通过execute方法)。
 -- 如果此时，线程池中运行的线程数量< corePoolSize，则创建新线程来处理请求。
 -- 如果此时，线程池中运行的线程数量> corePoolSize，但是却< maximumPoolSize；则仅当阻塞队列满时才创建新线程。
 如果设置的 corePoolSize 和 maximumPoolSize 相同，则创建了固定大小的线程池。
 如果将 maximumPoolSize 设置为基本的无界值（如 Integer.MAX_VALUE），则允许池适应任意数量的并发任务。
 在大多数情况下，核心池大小和最大池大小的值是在创建线程池设置的；
 但是，也可以使用 setCorePoolSize(int) 和 setMaximumPoolSize(int) 进行动态更改。

 5. poolSize
 poolSize是当前线程池的实际大小，即线程池中任务的数量。

 6. allowCoreThreadTimeOut和keepAliveTime
 allowCoreThreadTimeOut表示是否允许"线程在空闲状态时，仍然能够存活"；
 而keepAliveTime是当线程池处于空闲状态的时候，超过keepAliveTime时间之后，空闲的线程会被终止。

 7. threadFactory
 threadFactory是ThreadFactory对象。它是一个线程工厂类，"线程池通过ThreadFactory创建线程"。

 8. handler
 handler是RejectedExecutionHandler类型。它是"线程池拒绝策略"的句柄，也就是说"当某任务添加到线程池中，
 而线程池拒绝该任务时，线程池会通过handler进行相应的处理"。


 综上所说，线程池通过workers来管理"线程集合"，每个线程在启动后，会执行线程池中的任务；当
 一个任务执行完后，它会从线程池的阻塞队列中取出任务来继续运行。阻塞队列是管理线程池任务的队列，当
 添加到线程池中的任务超过线程池的容量时，该任务就会进入阻塞队列进行等待。
 */

/**
 * (一) 创建“线程池”
 下面以newFixedThreadPool()介绍线程池的创建过程。
 1. newFixedThreadPool()



 public static ExecutorService newFixedThreadPool(int nThreads) {
 return new ThreadPoolExecutor(nThreads, //corePoolSize 调整线程池实际线程数量
 nThreads,//maximumPoolSize
 0L,//keepAliveTime 空闲的线程会把离开终止
 TimeUnit.MILLISECONDS,
 new LinkedBlockingQueue<Runnable>());//是通过该阻塞队列来实现"当线程池中任务数量超过允许的任务数量时，部分任务会阻塞等待"。
 }

 最终调用ThreadPoolExecutor的一个构造方法，
 在ThreadPoolExecutor()的构造函数中，进行的是初始化工作。
 corePoolSize, maximumPoolSize, unit, keepAliveTime和workQueue这些变量的值是已知的，
 它们都是通过newFixedThreadPool()传递而来。
 this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
 Executors.defaultThreadFactory(), defaultHandler);
 下面看看threadFactory和handler对象。
 namePrefix = "pool-" +poolNumber.getAndIncrement() +"-thread-"; 线程的命名

 DefaultThreadFactory类中提返回了线程工厂。
 DefaultThreadFactory 的 newThread(Runnable r)方法在
 // 将线程放入池中进行执行
 pool.execute(ta);时被调用。

 */


/**
 * (二) 添加任务到“线程池”
    1. execute()
     execute()定义在ThreadPoolExecutor.java中

 说明：execute()的作用是将任务添加到线程池中执行。它会分为3种情况进行处理：
 情况1 -- 如果"线程池中任务数量" < "核心池大小"时，即线程池中少于corePoolSize个任务；此
         时就新建一个线程，并将该任务添加到线程中进行执行。
 情况2 -- 如果"线程池中任务数量" >= "核心池大小"，并且"线程池是允许状态"；此时，则将任务添加到阻塞队列中阻塞等待。
        在该情况下，会再次确认"线程池的状态"，如果"第2次读到的线程池状态"和"第1次读到的线程池状态"不同，则从阻塞队列中删除该任务。
 情况3 -- 非以上两种情况。在这种情况下，尝试新建一个线程，并将该任务添加到线程中进行执行。如果执行失败，则通过reject()拒绝该任务

 */


/**
 *
 */
public class Concept {
}
