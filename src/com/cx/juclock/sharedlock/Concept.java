package com.cx.juclock.sharedlock;

/**
 * JUC中的共享锁有CountDownLatch, CyclicBarrier, Semaphore, ReentrantReadWriteLock等；
 * 本章会以ReentrantReadWriteLock为蓝本对共享锁进行说明
 */

/**
 * CountDownLatch是一个同步的辅助类，允许一个或多个线程，等待其他一组线程完成操作，再继续执行。(倒计时锁)
 * 场景1：我们在玩LOL英雄联盟时会出现十个人不同加载状态，但是最后一个人由于各种原因始终加载不了100%，
 * 于是游戏系统自动等待所有玩家的状态都准备好，才展现游戏画面。
 * 场景1：
 * 有的学生提前交了试卷，并约起打球了，等到最后一个学生交卷了，老师开始整理试卷，贴封条，下班，陪老婆孩子去了。
 *
 CyclicBarrier是一个同步的辅助类，允许一组线程相互之间等待，达到一个共同点，再继续执行。
 他们都是:Synchronization  aid，我把它翻译成同步辅助器，既然是辅助工具，怎么使用啊？哪些场景
 所谓Cyclic即 循环 的意思，所谓Barrier即 屏障 的意思。使用啊？

 个人理解：CyclicBarrier:可看成是个障碍，所有的线程必须到齐后才能一起通过这个障碍
 场景还原：以前公司组织户外拓展活动，帮助团队建设，其中最重要一个项目就是全体员工（包括女同事，BOSS）在完成其他项目时，
 到达一个高达四米的高墙没有任何抓点，要求所有人，一个不能少的越过高墙，才能继续进行其他项目。

 长途汽车站提供长途客运服务。
 当等待坐车的乘客到达20人时，汽车站就会发出一辆长途汽车，让这20个乘客上车走人。
 等到下次等待的乘客又到达20人是，汽车站就会又发出一辆长途汽车

 CyclicBarrier常用于多线程分组计算。
 */
public class Concept {
}
