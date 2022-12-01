package com.liqi.automic;

import java.util.ArrayList;
import java.util.List;

public class AutomiVolatile {
    volatile int count = 0;

    public synchronized void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) throws Exception {
//        AutomiVolatile t = new AutomiVolatile();
//        List<Thread> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(new Thread(t::m, "thread-" + i));
//        }
//        list.forEach(Thread::start);
//
//        list.forEach(thread -> {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println(t.count);
        final long ONE_SECONDS = 1000;
        Thread mark = new Thread(() -> { // --> NEW
            synchronized (args) {
                try {
                    blocking(ONE_SECONDS); // --> BLOCKED
                    args.wait(); // --> WAITING
                    args.wait(ONE_SECONDS); // --> TIMED_WAITING
                } catch (InterruptedException e) {
                }
            }
        });
        printThreadState(mark);
        new Thread(() -> { // 执行使 mark 线程启动后进入监视器锁定阻塞的线程（等待获取锁）
            try {
                blocking(ONE_SECONDS + 500);
            } catch (InterruptedException e) {
            }
        }).start();
        Thread.sleep(ONE_SECONDS / 10);
        mark.start(); // 启动 mark 线程 --> RUNNABLE
        printThreadState(mark); // 打印 mark 线程 start() 后的状态
        Thread.sleep(ONE_SECONDS / 10);
        printThreadState(mark); // 打印 mark 线程 等待获取 sync 的状态
        Thread.sleep(ONE_SECONDS * 2 + 500); // 等待 mark 线程 blocking() 执行完成后执行 wait()
        printThreadState(mark); // 打印 mark 线程 wait() 时的状态
        synchronized (args) {
            args.notify(); // 唤醒 mark 线程
        }
        Thread.sleep(ONE_SECONDS / 2); // 等待 mark 线程执行 wait(long timeout)
        printThreadState(mark); // 获取 mark 线程 wait(long timeout) 时的状态
        Thread.sleep(ONE_SECONDS); // 等待 mark 线程执行完毕
        printThreadState(mark); // 打印已执行完成的状态 --> TERMINATED
    }

    static synchronized void blocking(long timeout) throws InterruptedException {
        Thread.sleep(timeout);
    }

    static void printThreadState(Thread stateThread) {
        System.out.println(stateThread.getState());
    }
}

