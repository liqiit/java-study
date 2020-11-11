package com.netty.future;

public class JoinDemo {
    public static final int SLEEP_GAP = 500;

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterThread extends Thread {
        public HotWaterThread() {
            super("烧水--Thread");
        }

        @Override
        public void run() {
            try {
                System.out.println("洗水壶");
                System.out.println("洗水壶运行结束");

                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class WashThread extends Thread {
        public WashThread() {
            super("清洗线程");
        }

        @Override
        public void run() {
            try {
                System.out.println("洗茶叶");
                System.out.println("洗茶叶运行结束");

                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread hotThread = new HotWaterThread();
        Thread washThread = new WashThread();
        hotThread.start();
        washThread.start();
        try {
            hotThread.join();
            washThread.join();
            Thread.currentThread().setName("主线程");
            System.out.println("全部结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
