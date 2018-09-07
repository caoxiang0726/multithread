package com.cx.basic.waitandnotify;

/**
 *
 */
public class JoinDemo {
    private static boolean isFinish;//表示图片是否下载完毕

    public static void main(String[] args) {

        final Thread download = new Thread() {
            public void run() {
                System.out.println("download1:开始下载图片");
                for (int i = 1; i <= 100; i++) {
                    System.out.println("已下载:" + i + "%");
                    sleep50ms();
                }
                System.out.println("download:图片下载完毕!");
                isFinish = true;
            }
        };


        //显示图片的线程 应当等待download将图片下载完毕。
        Thread show = new Thread() {
            public void run() {
                System.out.println("show1:开始显示图片");
                try {
                    download.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!isFinish) {
                    throw new RuntimeException("图片没有下载完毕!");
                }
                System.out.println("show:显示图片完毕!");
            }
        };

        show.start();
        download.start();
    }

    public static void sleep50ms() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
