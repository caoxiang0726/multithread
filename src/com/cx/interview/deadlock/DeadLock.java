package com.cx.interview.deadlock;

/**
 * linux ps -ef | grep java
 * windows tasklist /m java*  获取pid
 *
 * 然后 jstack pid
 *
 * "Thread-1":
 at com.cx.interview.deadlock.Lock2.run(DeadLock.java:43)
 - waiting to lock <0x000000076b50a828> (a java.lang.String)
 - locked <0x000000076b50a858> (a java.lang.String)
 at java.lang.Thread.run(Thread.java:748)

 "Thread-0":
 at com.cx.interview.deadlock.Lock1.run(DeadLock.java:24)
 - waiting to lock <0x000000076b50a858> (a java.lang.String)
 - locked <0x000000076b50a828> (a java.lang.String)
 at java.lang.Thread.run(Thread.java:748)

 Found 1 deadlock.
 */
public class DeadLock {
    public static String obj1 = "obj1";
    public static String obj2 = "obj2";
    public static void main(String[] args){
        Thread a = new Thread(new Lock1());
        a.start();

        Thread b = new Thread(new Lock2());
        b.start();
    }
}
class Lock1 implements Runnable{
    @Override
    public void run(){
        try{
            System.out.println("Lock1 running");
            while(true){
                synchronized(DeadLock.obj1){
                    System.out.println(Thread.currentThread().getName()+":lock obj1");
                    Thread.sleep(1000);
                    synchronized(DeadLock.obj2){
                        System.out.println(Thread.currentThread().getName()+":lock obj2");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
class Lock2 implements Runnable{
    @Override
    public void run(){
        try{
            System.out.println("Lock2 running");
            while(true){
                synchronized(DeadLock.obj2){
                    System.out.println(Thread.currentThread().getName()+":lock obj2");
                    Thread.sleep(1000);
                    synchronized(DeadLock.obj1){
                        System.out.println(Thread.currentThread().getName()+":lock obj1");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}