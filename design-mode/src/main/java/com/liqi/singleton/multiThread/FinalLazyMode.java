package com.liqi.singleton.multiThread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class FinalLazyMode {
    private FinalLazyMode() {//必须私有化构造方法，防止外部实例化
    }

    //对象的赋值过程 可能指令重排序
    private volatile static FinalLazyMode instance = null;


    public static FinalLazyMode getInstance() {
        if (instance == null) {//可以省略，但是提高效率，减少拿锁的过程
            synchronized (FinalLazyMode.class) {
                if (instance == null) {//双重检查，方式多线程
                    instance = new FinalLazyMode();
                }
            }

        }

        return instance;
    }

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
            synchronized (args) {
                try {
                    Thread.sleep(1000);
                    args.wait();
                    args.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        System.out.println(thread.getState());
        thread.start();
        Thread.sleep(1000);
        System.out.println(thread.getState());
        Thread.sleep(1000);
        System.out.println(thread.getState());
        synchronized (args) {
            args.notifyAll();
        }

        AtomicInteger integer = new AtomicInteger();
        integer.get();
        AtomicStampedReference reference = new AtomicStampedReference(integer, 3);
    }


}
