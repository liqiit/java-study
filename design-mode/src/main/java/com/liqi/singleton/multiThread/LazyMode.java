package com.liqi.singleton.multiThread;

import java.util.concurrent.TimeUnit;

public class LazyMode {
    private LazyMode() {//必须私有化构造方法，防止外部实例化
    }

    private static LazyMode instance = null;

    public synchronized static LazyMode getInstance() {//锁太粗，不需要在方法直接锁
        if (instance == null) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new LazyMode();
        }
        return instance;
    }


    public static void main(String[] args) {
//        for (int i=0;i<100;i++) {
        new Thread(() -> {
            System.out.println(LazyMode.getInstance());
        }).start();
        new Thread(() -> {
            System.out.println(LazyMode.getInstance());
        }).start();
        //}

    }
}
