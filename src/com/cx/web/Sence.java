package com.cx.web;

/**
 * web应用中哪些需要考虑多线程？
 * 1 异步（创建营销活动）
 * 2 定时任务特别频繁，同时调用某一个方法
 *
 * 你问： java web中线程不是由tomcat这类web容器负责的吗？为什么还要我去控制多线程？

 答：从一个例子开始给你讲起，现在有一个任务，从http接口获取商品信息，完成一些业务逻辑的处理对数据进行修饰，插入到数据库，
 数据量大概是10w级别。这个需求在你看来很简单吧，开发并不难完成。
 初级程序员是怎么做的，每次获取一个商品，然后保存到数据库，同步进行。整个编码不超过30分钟即可完成。
 然后实际操作，发现由于网络io，数据库io大数据量的问题导致了你等待了一天，完成了同步数据的操作。

 有经验的程序员如何分析这个需求呢？
 上述初级程序员最直接的想法导致了数据库io发生了10w次，http请求发生了10w次（因为有10w个商品信息），优化点有三：
 一，查看是否有批量获取商品信息的接口，一次如果能获得100个商品，那么网络io会减少到1000次；
 二，存储到数据库可以使用批处理，一次保存500条，这样数据库io减少到200次；
 三，就是你提到的多线程了，因为每个操作是互相独立的，并且需求中提到了会进行数据的处理，
    而cpu密集型操作，就能体现多线程的优势，上一个商品的信息不会影响到下一个商品，
 所以我们可以很简单的使用一个线程池（实际应用），多线程批量获取数据，批量插入数据库。
 */


/**
 * 先说一下我心目的互联网程序员分级：

 初级—初阶
 掌握java基础，熟悉常用类库。理解javaweb中的servlet，jsp，并了解常用的框架对java web的封装原理，
 能够借助框架完成增删改查功能。理解数据库在web开发中的地位。

 初级—中阶
 理解java中较为高级的特性，如反射，动态代理，JVM，内存模型，多线程等等。
 熟练使用框架，对框架中遇到的bug，能够借助日志和搜索引擎分析出问题的原因。在团队中，能够独立完成普通后台业务功能的开发。
 了解数据库的高级特性，如索引，存储引擎等等。

 初级—高阶
 理解java分布式架构，微服务架构(（如rpc框架dubbo，motan，或springcloud一类）)，
 了解其与集中式架构的区别，并能保证分布式代码质量。
 熟练使用各个中间件如redis，mq，zookeeper等等，并了解其工作原理和使用场景。
 能够在中级或高级程序员的带领之下，完成非核心功能的研发。能够关注开源，并且具有阅读源码的能力。

 中级
 初级高阶已经很厉害了，但是往往缺乏的是一些项目经验，所以在我这里还是初级。
 脱离初级程序员不仅仅需要技术方面的支撑，还需要具备一定的项目开发经验（3年之上一线互联网产品研发经验），
 拥有线上bug的处理能力，JVM调优能力，以及完成核心业务功能的开发。并且带领团队的新人，能够按能力分配任务。

 高级
 团队的核心人物，把控整个项目的质量，包括代码漏洞和规范问题。
 具有5年以上项目开发经验，2年以上架构搭建的经验，能够根据业务选择不同的架构类型；
 根据团队组成，分配不同的任务。具有将自己的知识分享出去的能力，带领初级程序员走向中级，中级程序员走向高级的能力。
 */
public class Sence {
}